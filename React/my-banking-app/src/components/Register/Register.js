import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import axios from "axios";
import "../Register/register.css";
import image from "../../image/login-backgroundImage.jpg";

function Register() {
  const navigate = useNavigate();
  const API_BASE_URL = "http://localhost:8080/api";

  const [form, setForm] = useState({
    first_Name: "",
    last_Name: "",
    mobile: "",
    email: "",
    password: "",
    confirmPass: "",
    terms: false,
  });

  const [errors, setErrors] = useState({
    first_Name: false,
    mobile: false,
    email: false,
    password: false,
    confirmPass: false,
    passwordMismatch: false,
    terms: false,
  });

  async function onSubmit(event) {
    event.preventDefault();
    const mobileValid = /^\d{10}$/.test(form.mobile);
    const passwordValid = /^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\d).{7,}$/.test(
      form.password
    );

    const newErrors = {
      first_Name: !form.first_Name,
      last_Name: !form.last_Name,
      mobile: !form.mobile || !mobileValid,
      email: !form.email,
      password: !form.password || !passwordValid,
      confirmPass: !form.confirmPass,
      terms: !form.terms,
      passwordMismatch: form.password !== form.confirmPass,
    };

    setErrors(newErrors);

    if (Object.values(newErrors).some((error) => error)) {
      console.log("Please fill all the details correctly.");
      return;
    }
    try {
      // Register the user
      const userResponse = await axios.post(`${API_BASE_URL}/users`, form, {
        headers: {
          "Content-Type": "application/json",
        },
      });
      const userId = userResponse.data.id;
      console.log("User created with ID:", userId);

      // Wait for the userResponse to be resolved before proceeding
      await new Promise((resolve) => setTimeout(resolve, 1000)); // add a small delay

      // Authenticate to get JWT token
      const authResponse = await axios.post(`${API_BASE_URL}/authenticate`, {
        username: form.email,
        password: form.password,
      });
      const token = authResponse.data.jwt;
      localStorage.setItem("authToken", token);

      //Create account with JWT
      const randomPart = Math.floor(Math.random() * 10000);
      const randomNumber = "7000" + randomPart;
      const currentDate = new Date().toISOString().split("T")[0];
      const account = {
        userId: userId, // Use the obtained user ID
        accNumber: randomNumber,
        accBalance: 0,
        accCreatedAt: currentDate,
        accType: "Savings", // or any default type
      };
      console.log("userId:", userId);
      console.log("randomNumber:", randomNumber);
      console.log("currentDate:", currentDate);
      console.log("userResponse:", userResponse);
      console.log("account:", account);
      const accountResponse = await axios.post(
        `${API_BASE_URL}/account`,
        account,
        {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`,
          },
        }
      );

      console.log("Account created:", accountResponse.data);

      // Reset form and navigate to login
      setForm({
        first_Name: "",
        last_Name: "",
        mobile: "",
        email: "",
        password: "",
        confirmPass: "",
        terms: false,
      });

      navigate("/login", { state: { registrationSuccess: true } });
    } catch (error) {
      console.error("Registration error:", error);
    }
  }

  function handleCreateAccount(event) {
    const { name, value, type, checked } = event.target;
    setForm((preFormData) => ({
      ...preFormData,
      [name]: type === "checkbox" ? checked : value,
    }));

    setErrors((prevErrors) => {
      const updatedErrors = {
        ...prevErrors,
        [name]: false,
        passwordMismatch:
          name === "password" || name === "confirmPass"
            ? false
            : prevErrors.passwordMismatch,
        mobile: name === "mobile" ? !/^\d{10}$/.test(value) : prevErrors.mobile,
        password:
          name === "password"
            ? !/^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\d).{7,}$/.test(value)
            : prevErrors.password,
      };
      console.log(updatedErrors); // Debugging line
      return updatedErrors;
    });
  }

  return (
    <div className="register-bg">
      <div className="Register-page">
        <form className="Register-form" onSubmit={onSubmit}>
          <h1 className="register-text">Registration Form</h1>
          {errors.general && (
            <div className="error-message">{errors.general}</div>
          )}
          <div className="name-row">
            <div className="input-group">
              <label>First Name</label>
              <input
                className={`firstName ${
                  errors.first_Name ? "input-error" : ""
                }`}
                placeholder="First Name"
                type="text"
                name="first_Name"
                autoComplete="off"
                value={form.first_Name}
                onChange={handleCreateAccount}
              />
              {errors.first_Name && (
                <span className="error-message">First Name is required.</span>
              )}
            </div>
            <div className="input-group">
              <label>Last Name</label>
              <input
                className={`last_Name ${errors.last_Name ? "input-error" : ""}`}
                placeholder="Last Name"
                type="text"
                name="last_Name"
                autoComplete="off"
                value={form.last_Name}
                onChange={handleCreateAccount}
              />
            </div>
          </div>
          <div className="name-row">
            <div className="input-group">
              <label>Email</label>
              <input
                className={`reg-email ${errors.email ? "input-error" : ""}`}
                placeholder="Email"
                type="email"
                name="email"
                autoComplete="off"
                value={form.email}
                onChange={handleCreateAccount}
              />
              {errors.email && (
                <span className="error-message">Email is required.</span>
              )}
            </div>
            <div className="input-group">
              <label>Mobile</label>
              <input
                className={`mobile ${errors.mobile ? "input-error" : ""}`}
                placeholder="Mobile"
                type="tel"
                pattern="[0-9]{10}"
                autoComplete="off"
                name="mobile"
                value={form.mobile}
                onChange={handleCreateAccount}
              />
              {errors.mobile && (
                <span className="error-message">Mobile is required.</span>
              )}
            </div>
          </div>
          <div className="name-row">
            <div className="input-group">
              <label>Password</label>
              <input
                className={`register-password ${
                  errors.password ? "input-error" : ""
                }`}
                placeholder="Password"
                type="password"
                name="password"
                autoComplete="off"
                value={form.password}
                onChange={handleCreateAccount}
              />
              {errors.password && (
                <span className="error-message">Password is required.</span>
              )}
            </div>
            <div className="input-group">
              <label>Confirm Password</label>
              <input
                className={`register-confirm-pass ${
                  errors.confirmPass || errors.passwordMismatch
                    ? "input-error"
                    : ""
                }`}
                placeholder="Confirm Password"
                type="password"
                name="confirmPass"
                autoComplete="off"
                value={form.confirmPass}
                onChange={handleCreateAccount}
              />
              {errors.confirmPass && (
                <span className="error-message">
                  Confirm Password is required.
                </span>
              )}
              {errors.passwordMismatch && (
                <span className="error-message">Passwords do not match.</span>
              )}
            </div>
          </div>
          <div className="terms-row">
            <input
              type="checkbox"
              id="terms"
              name="terms"
              checked={form.terms}
              onChange={handleCreateAccount}
            />
            <span className="terms-label">
              <Link to="/terms">I agree to the Terms & Conditions</Link>
            </span>
          </div>
          {errors.terms && (
            <div className="error-message">
              You must agree to the terms & Conditions.
            </div>
          )}
          <button type="submit" className="Create-acc-btn">
            Sign Up
          </button>
          <div className="reg-btn">
            <Link to="/login" className="register-login-btn">
              Already have an Account →
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register;
