import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";
import "../assets/login.css";
import {API_BASE_URL} from "../service/AuthService";

const Login = () => {
  const [showPass, setShowPass] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { login } = useAuth();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    const success = await login(username, password);
    if (success) {
      window.location.href = "/";
    } else {
      setError("Sai tài khoản hoặc mật khẩu!");
    }
  };
  const handleGoogleLogin = () => {
    window.location.href = `${API_BASE_URL}/oauth2/authorization/google`;
  };
  return (
      <div className="login-bg">
        <div className="login-container">
          <h2 className="login-title">Welcome Back</h2>
          <p className="login-subtitle">Sign in to your account</p>
          <form onSubmit={handleLogin}>
            <div className="login-field">
              <input
                  type="text"
                  placeholder="Username"
                  className="login-input"
                  value={username}
                  onChange={e => setUsername(e.target.value)}
                  required
              />
            </div>
            <div className="login-field">
              <input
                  type={showPass ? "text" : "password"}
                  placeholder="Password"
                  className="login-input"
                  value={password}
                  onChange={e => setPassword(e.target.value)}
                  required
              />
              <span
                  className="login-eye"
                  onClick={() => setShowPass((v) => !v)}
                  title={showPass ? "Hide password" : "Show password"}
              >
              {showPass ? (
                  <svg height="20" viewBox="0 0 24 24" width="20" fill="#bcbcbc">
                    <path
                        d="M12 6c-5 0-9 6-9 6s4 6 9 6 9-6 9-6-4-6-9-6zm0 10c-2.21 0-4-1.79-4-4s1.79-4 4-4 4 1.79 4 4-1.79 4-4 4zm0-6.5a2.5 2.5 0 100 5 2.5 2.5 0 000-5z"/>
                  </svg>
              ) : (
                  <svg height="20" viewBox="0 0 24 24" width="20" fill="#bcbcbc">
                    <path
                        d="M12 6c-5 0-9 6-9 6a17.75 17.75 0 003.51 4.38L2 19.89l1.41 1.41 19.09-19.09L20.09 2l-2.04 2.04A9.978 9.978 0 0012 6zm0 12c-2.96 0-5.65-1.44-7.6-3.76l1.45-1.45A7.968 7.968 0 0012 18c1.5 0 2.92-.39 4.15-1.08l1.47 1.47A9.92 9.92 0 0112 18zm8.69-3.11l-1.48-1.48A7.95 7.95 0 0020 12c0-1.17-.23-2.28-.6-3.31l1.48-1.48C21.33 8.92 22 10.4 22 12c0 1.6-.67 3.08-1.31 4.11z"/>
                  </svg>
              )}
            </span>
            </div>
            {error && <div style={{color: "red", textAlign: "center", marginBottom: 8}}>{error}</div>}
            <div className="login-row">
              <label className="login-remember">
                <input type="checkbox"/> Remember me
              </label>
              <a href="#" className="login-forgot">
                Forgot password?
              </a>
            </div>
            <button className="login-btn" type="submit">
              Sign In
            </button>
            <div className="login-divider">or continue with</div>
            <div className="login-socials">
              <button type="button" className="social-btn" onClick={handleGoogleLogin}>
                <img src="https://img.icons8.com/color/24/000000/google-logo.png" alt="Google"/>
                Google
              </button>
              <button type="button" className="social-btn">
                <svg width="20" height="20" fill="#333" viewBox="0 0 24 24">
                  <path
                      d="M12 .297c-6.63 0-12 5.373-12 12 0 5.303 3.438 9.8 8.205 11.387.6.113.82-.258.82-.577v-2.234c-3.338.724-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.084-.729.084-.729 1.205.084 1.84 1.237 1.84 1.237 1.07 1.834 2.809 1.304 3.495.997.108-.775.418-1.305.762-1.605-2.665-.306-5.466-1.332-5.466-5.93 0-1.312.469-2.381 1.236-3.221-.123-.303-.536-1.527.117-3.176 0 0 1.008-.322 3.301 1.23a11.52 11.52 0 013.003-.404c1.018.005 2.045.138 3.003.404 2.291-1.553 3.297-1.23 3.297-1.23.655 1.649.242 2.873.12 3.176.77.84 1.235 1.909 1.235 3.221 0 4.609-2.803 5.62-5.475 5.921.43.372.823 1.102.823 2.222v3.293c0 .322.218.694.825.577C20.565 22.092 24 17.592 24 12.297c0-6.627-5.373-12-12-12"/>
                </svg>
                GitHub
              </button>
            </div>
            <div className="login-bottom">
              Don't have an account?
              <a href="register" className="login-signup"> Sign up</a>
            </div>
          </form>
        </div>
      </div>
  );
};

export default Login;