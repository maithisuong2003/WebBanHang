import {createContext, useContext, useEffect, useState} from "react";
import axios from "axios";
import {API_BASE_URL, loginToken} from "../service/AuthService";
const AuthContext = createContext();

export function AuthProvider({children}) {
    const [token, setToken] = useState(null);
    const [user, setUser] = useState(null);

    useEffect(() => {
        if (typeof window !== 'undefined') {
            const tokenStorage = localStorage.getItem('token');
            if (tokenStorage) {
                setToken(tokenStorage);
            }
        }
    }, []);
    useEffect(() => {
        const checkTokenValadity = async () => {
            if (token) {
                try {
                    axios.get(`${API_BASE_URL}/users/myInfo`, {headers: {"Authorization": `Bearer ${token}`}})
                        .then(response => {
                            setUser(response.data.result);
                            localStorage.setItem('user', JSON.stringify(response.data.result));
                        })
                        .catch(error => {
                            console.error("There was an error with the Axios operation:", error);
                        });
                } catch (error) {
                    console.error("Loi kiem tra token", error);
                }
            }
        }
        checkTokenValadity();
    }, [token])

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');

        if (token) {
            localStorage.setItem('token', token);
            setToken(token);
        }
    }, []);
    //kiem tra token dinh ky

    useEffect(() => {
        const interval = setInterval(() => {
            const tokenFromStorage = localStorage.getItem('token');
            if (tokenFromStorage) {
                const tokenWillExpireSoon = willTokenExpireSoon(tokenFromStorage);
                console.log('Token will expire soon:', tokenWillExpireSoon);
                if (tokenWillExpireSoon) {
                    refreshAuthToken(tokenFromStorage)
                }
            }
        }, 2 * 60 * 1000); // 15 minutes

        return () => clearInterval(interval);
    }, []);

    const login = async (accountName, password) => {
        try {
            const response = await loginToken(accountName, password);
            if (response.status === 200) {
                const newToken = response.data.result.token;
                localStorage.setItem('token', newToken);
                setToken(newToken);

                axios.get(`${API_BASE_URL}/account/myInfo`, {
                    headers: {
                        "Authorization": `Bearer ${newToken}`
                    }
                })
                    .then(response => {
                        setUser(response.data.result);
                        localStorage.setItem('user', JSON.stringify(response.data.result));
                    })
                    .catch(error => {
                        console.error("There was an error with the Axios operation:", error);
                    });
                return true;
            } else {
                console.error('Login failed: Unexpected response:', response);
                return false;
            }
        } catch (error) {
            console.error('Login failed:', error.response.data.message);
            return false;
        }
    };
    const willTokenExpireSoon = (token) => {
        const decodedToken = parseJwt(token);
        const currentTime = Date.now() / 1000;
        const tokenExpiryBuffer = 2 * 60;
        return decodedToken.exp < currentTime + tokenExpiryBuffer;
    };
    const refreshAuthToken = async (currentToken) => {
        try {
            const response = await axios.post(`${API_BASE_URL}/auth/refresh`, {token: currentToken});
            if (response.data && response.data.result) {
                const newToken = response.data.result.token;
                localStorage.setItem('token', newToken);
                setToken(newToken);
                console.log('Token refreshed successfully');
            }
        } catch (error) {
            console.error('Error refreshing token:', error);
        }
    };

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        setToken(null);
        setUser(null);
    };

    const parseJwt = (token) => {
        try {
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            return JSON.parse(jsonPayload);
        } catch (e) {
            return null;
        }
    };

    return (
        <AuthContext.Provider value={{token, user, login, logout}}>
            {children}
        </AuthContext.Provider>
    );
}
    export function useAuth() {
        return useContext(AuthContext);
}

