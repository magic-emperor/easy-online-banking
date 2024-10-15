// import React, { Component } from "react";
// import ReactDOM from "react-dom/client";
// components created
// import NewBank from "./components/login-page/NewBank";
// import TestBankApp from "./components/welcomePage/TestBankApp";
// import WelcomePage from "./components/welcomePage/WelcomePage";

// import App from "./App";
// const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(<App />);

// const portalRoot = document.createElement("div");
// portalRoot.id = "portalRoot";
// document.body.appendChild(portalRoot);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
// reportWebVitals();

// import TestingAPI from "./testing/TestingAPI";
// const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(<TestingAPI />);

// import AccountTestApi from "./testing/api/AccountTestApi";
// const root = ReactDOM.createRoot(document.getElementById("root"));
// root.render(<AccountTestApi />);

import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// Create portal root
const portalRoot = document.createElement("div");
portalRoot.id = "portalRoot";
document.body.appendChild(portalRoot);
