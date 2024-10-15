import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Register from "./components/Register/Register";
import WelcomePage from "./components/welcomePage/WelcomePage";
import Login from "./components/login-page/Login";
import Dashboard from "./components/Dashboard/Dashboard";
import { AccountProvider } from "./components/Context/AccountProvider";
import Card from "./components/Cards/Card";
import ForgotPassword from "./components/forgotPassword/ForgotPassword";
import Admin from "./components/AdminDashboard/Admin";
function App() {
  return (
    <>
      <AccountProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/register" element={<Register />} />
            <Route path="/" element={<WelcomePage />} />
            <Route path="/login" element={<Login />} />
            {/* Dynamic route to capture the user ID */}
            <Route path="/dashboard/:id" element={<Dashboard />} />
            <Route path="/card" element={<Card />}></Route>
            <Route path="/forgot-password" element={<ForgotPassword />}></Route>
            <Route path="/admin" element={<Admin />}></Route>
          </Routes>
        </BrowserRouter>
      </AccountProvider>
    </>
  );
}

export default App;
