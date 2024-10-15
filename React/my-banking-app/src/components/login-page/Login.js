import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../login-page/login.css";
import axios from "axios";

// Admin login credentials
// admin@easybanking.com
// Admin@easy123

function Login() {
  const navigate = useNavigate();
  const [login, setLogin] = useState({
    userName: "",
    password: "",
  });

  const [error, setError] = useState({
    userName: false,
    password: false,
  });

  async function onLogin(event) {
    event.preventDefault();

    const newError = {
      userName: !login.userName,
      password: !login.password,
    };
    setError(newError);

    if (Object.values(newError).some((fieldError) => fieldError)) {
      console.log("Mandatory details are not filled");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/authenticate",
        {
          username: login.userName,
          password: login.password,
        }
      );

      console.log("Full Response:", response);

      if (response.status === 200) {
        const token = response.data.jwt;
        console.log("Received Token:", token);

        const tokenData = await parseJwt(token);
        console.log("Token Data:", tokenData);

        localStorage.setItem("authToken", token);
        const userId = tokenData.id;
        console.log("User ID:", userId);

        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        localStorage.setItem("userId", userId);
        sessionStorage.setItem("userId", userId);

        if (tokenData.id === 18) {
          navigate("/admin", { state: { adminJWT: token } });
        } else {
          console.log("Fetching user data...");
          const accountResponse = await axios.get(
            `http://localhost:8080/api/account/userid/${userId}`
          );
          console.log("Account Response:", accountResponse.data);

          const userResponse = await axios.get(
            `http://localhost:8080/api/users/${userId}`
          );
          console.log("User Response:", userResponse.data);

          navigate(`/dashboard/${userId}`, {
            state: {
              JWTaccount: accountResponse.data,
              SetJWTuser: userResponse.data,
            },
          });
        }
      } else {
        console.log("Unexpected response status:", response.status);
        setError({
          userName: true,
          password: true,
        });
        console.log("Invalid username or password");
      }
    } catch (error) {
      setError({
        userName: true,
        password: true,
      });
      console.error("Authentication failed", error);
      if (error.response) {
        console.error("Response data:", error.response.data);
        console.error("Response status:", error.response.status);
        console.error("Response headers:", error.response.headers);
      } else if (error.request) {
        console.error("No response received:", error.request);
      } else {
        console.error("Error setting up request:", error.message);
      }
    }
  }

  function onForgotPassword() {
    navigate("/forgot-password");
  }

  function parseJwt(token) {
    if (!token) {
      console.error("Token is undefined or null");
      return {};
    }
    try {
      const base64Url = token.split(".")[1];
      const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      const payload = JSON.parse(window.atob(base64));
      console.log("Full JWT payload:", payload);
      return {
        id: payload.userId,
        email: payload.sub,
        role: payload.role, // Make sure your server includes the role in the JWT
      };
    } catch (error) {
      console.error("Error parsing JWT token:", error);
      return {};
    }
  }

  function onLoginChange(event) {
    const { name, value } = event.target;
    setLogin((prevLoginData) => ({
      ...prevLoginData,
      [name]: value,
    }));

    setError((prevError) => ({
      ...prevError,
      [name]: !value,
    }));
  }

  return (
    <div className="Login-page">
      <form className="Login-form" onSubmit={onLogin}>
        <h1 className="Login-text">Login</h1>
        <div className="input-group">
          <label>Email ID</label>
          <input
            type="text"
            name="userName"
            autoComplete="off"
            placeholder="Email ID"
            className={`login-userName ${
              error.userName ? "login-input-error" : ""
            }`}
            onChange={onLoginChange}
            value={login.userName}
          />
          {error.userName && (
            <span className="error-text">Please Enter User ID</span>
          )}
        </div>
        <div className="input-group">
          <label>Password</label>
          <input
            type="password"
            name="password"
            autoComplete="off"
            placeholder="Password"
            className={`login-password ${
              error.password ? "login-input-error" : ""
            }`}
            onChange={onLoginChange}
            value={login.password}
          />
          {error.password && (
            <span className="error-text">Please Enter Password</span>
          )}
        </div>
        <button className="Login-btn" type="submit">
          Login
        </button>
        <p className="Forgot-password-link" onClick={onForgotPassword}>
          Forgot Password?
        </p>
      </form>
    </div>
  );
}

export default Login;
