import React, { useState } from "react";
import axios from "axios";
import "../assets/login.css";
import {useNavigate} from "react-router-dom";
import {API_BASE_URL} from "../service/AuthService";

const Register = () => {
  const [showPass, setShowPass] = useState(false);
  const [showConfirmPass, setShowConfirmPass] = useState(false);
  const [form, setForm] = useState({
    email: "",
    userName: "",
    fullName: "",
    password: "",
    confirmPassword: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError("");
    setSuccess("");
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    if (form.password !== form.confirmPassword) {
      setError("Mật khẩu xác nhận không khớp!");
      return;
    }
    if (!form.email || !form.userName || !form.password || !form.fullName) {
      setError("Vui lòng điền đầy đủ thông tin!");
      return;
    }
    const isValidEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    if (!isValidEmail(form.email)) {
      setError("Email không hợp lệ!");
      return;
    }
    try {
      const res = await axios.post(`${API_BASE_URL}/users/register`, {
        email: form.email,
        userName: form.userName,
        fullName: form.fullName,
        password: form.password,
      });
      if (res.data && (res.data.success === true || res.data.code === 200)) {
        navigate("/login");
      } else {
        setError(res.data.message || "Đăng ký thất bại!");
      }
    } catch (err) {
      setError(
          (err.response && err.response.data && err.response.data.message) ||
          "Lỗi đăng ký! Vui lòng thử lại."
      );
    }
  };
  return (
    <div className="login-bg">
      <div className="login-container">
        <h2 className="login-title">Sign Up</h2>
        <p className="login-subtitle">Create a new account</p>
        <form onSubmit={handleRegister}>
          <div className="login-field">
            <input
                type="text"
                name="userName"
                placeholder="Username"
                className="login-input"
                value={form.userName}
                onChange={handleChange}
                required
            />
          </div>
          <div className="login-field">
            <input
                type="email"
                name="email"
                placeholder="Email"
                className="login-input"
                value={form.email}
                onChange={handleChange}
                required
            />
          </div>

          <div className="login-field">
            <input
                type="text"
                name="fullName"
                placeholder="Full Name"
                className="login-input"
                value={form.fullName}
                onChange={handleChange}
                required
            />
          </div>
          <div className="login-field">
            <input
                type={showPass ? "text" : "password"}
                name="password"
                placeholder="Password"
                className="login-input"
                value={form.password}
                onChange={handleChange}
                required
                autoComplete="new-password"
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
          <div className="login-field">
            <input
                type={showConfirmPass ? "text" : "password"}
                name="confirmPassword"
                placeholder="Confirm Password"
                className="login-input"
                value={form.confirmPassword}
                onChange={handleChange}
                required
                autoComplete="new-password"
            />
            <span
                className="login-eye"
                onClick={() => setShowConfirmPass((v) => !v)}
                title={showConfirmPass ? "Hide password" : "Show password"}
            >
              {showConfirmPass ? (
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
          {error && (
              <div style={{color: "red", textAlign: "center", marginBottom: 8}}>
                {error}
              </div>
          )}
          {success && (
              <div style={{color: "green", textAlign: "center", marginBottom: 8}}>
                {success}
              </div>
          )}
          <button className="login-btn" type="submit">
            Sign Up
          </button>
          <div className="login-bottom">
            Already have an account?
            <a href="/login" className="login-signup">
              {" "}
              Log in
            </a>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Register;