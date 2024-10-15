import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../login-page/login.css";
function ForgotPassword() {
  //   const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState(false);

  async function onResetPassword(event) {
    event.preventDefault();

    if (!email || !password || !confirmPassword) {
      setError("All fields are required.");
      return;
    }

    if (password !== confirmPassword) {
      setError("Passwords do not match.");
      return;
    }
    const passwordValid = /^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\d).{7,}$/.test(
      password
    );
    if (!passwordValid) {
      setError(
        "Password must be at least 7 characters long, contain at least one uppercase letter, one special character, and one number."
      );
      return;
    }

    setError("");
    const userId = localStorage.getItem("userId");
    const token = localStorage.getItem("authToken");

    if (!token || token.split(".").length !== 3) {
      setError("Invalid JWT token.");
      return;
    }

    try {
      const response = await axios.put(
        `http://localhost:8080/api/users/${userId}`,
        { email, password },
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      if (response.status === 200) {
        setSuccess(true);
        console.log("Password updated successfully!");
      } else {
        setError("Failed to update password. Please try again.");
      }
    } catch (err) {
      console.error(
        "Error updating password",
        err.response ? err.response.data : err
      );
      setError(
        "Error updating password: " +
          (err.response ? err.response.data.message : "Unknown error.")
      );
    }
  }

  return (
    <div className="Login-page">
      <form className="Login-form" onSubmit={onResetPassword}>
        <h1 className="Login-text">Reset Password</h1>
        <div className="input-group">
          <label>Email ID</label>
          <input
            type="email"
            autoComplete="off"
            placeholder="Email ID"
            className={`login-userName ${error ? "login-input-error" : ""}`}
            onChange={(e) => setEmail(e.target.value)}
            value={email}
          />
          {error && <span className="error-text">{error}</span>}
        </div>
        <div className="input-group">
          <label>New Password</label>
          <input
            type="password"
            autoComplete="off"
            placeholder="New Password"
            className={`login-userName ${error ? "login-input-error" : ""}`}
            onChange={(e) => setPassword(e.target.value)}
            value={password}
          />
        </div>
        <div className="input-group">
          <label>Confirm Password</label>
          <input
            type="password"
            autoComplete="off"
            placeholder="Confirm Password"
            className={`login-userName ${error ? "login-input-error" : ""}`}
            onChange={(e) => setConfirmPassword(e.target.value)}
            value={confirmPassword}
          />
        </div>
        {success && (
          <span className="success-text">Password updated successfully!</span>
        )}
        <button className="Login-btn" type="submit">
          Reset Password
        </button>
        <a href="/login" className="Forgot-password-link">
          Back to Login
        </a>
      </form>
    </div>
  );
}

export default ForgotPassword;
