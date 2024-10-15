import React from "react";
// import image from "../../image/Welcome-image.png";
import "../BankingWelcomePage.css";
import welcomeimg from "../../image/welomeImage for bank.png";

function TestBankApp() {
  return (
    <div>
      <div className="Name-lable">
        <h1> Easy Online Banking</h1>
      </div>
      <button className="Welcome-Login">
        <h2>Login</h2>
      </button>
      <p className="details">
        Hit Reagister to Get Started with digital Banking to make life Easy
      </p>
      <button className="Welcome-Register">
        <h2>Register →</h2>
      </button>
      <div className="image-sizing">
        <img
          src={welcomeimg}
          alt="welcome-image"
          className="Welcome-Page-image"
        />
      </div>
    </div>
  );
}

export default TestBankApp;
