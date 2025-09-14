import axios from "axios";

export const API_BASE_URL = "http://localhost:8080";

export const loginToken = (userName, password) => axios.post(`${API_BASE_URL}/auth/login`, { userName, password });
export const logoutToken = (token) => axios.post(`${API_BASE_URL}/auth/logout`, { token });
export const hasPermission = (requiredPermission) => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        if (user.roles) {
            const roles = user.roles;
            for (let role of roles) {
                if (role.permissions.some(permission => permission.name === requiredPermission)) {
                    return true;
                }
            }

            return false;
        }
    }
};
