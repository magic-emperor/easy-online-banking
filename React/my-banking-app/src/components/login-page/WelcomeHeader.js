import React from "react";
import img from "./test-logo.avif";
import { useNavigate } from "react-router-dom";

function WelcomeHeader() {
  const navigate = useNavigate();
  return (
    <div className="welcome-text">
      <div className="head-container">
        <div>
          <img src={img} alt="logo" className="logo"></img>
        </div>
        <div className="center-container ">
          <button className="welcome-button">Banking Web Application</button>
        </div>
        <div className="register-container">
          <button className="login-button"> Login</button>
          <button className="register-button">Register</button>
          <button onClick={() => navigate(-1)}>Home</button>
        </div>
      </div>
      {/* <button className="Main-Register--button"> Register</button> */}
    </div>
  );
}
export default WelcomeHeader;
