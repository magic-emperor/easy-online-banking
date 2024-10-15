import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import "../AdminDashboard/admin.css";

const Admin = () => {
  const [accountData, setAccountData] = useState(null);
  const [userDetails, setUserDetails] = useState(null);
  const [loanDetails, setLoanDetails] = useState(null);
  const [transactions, setTransactions] = useState([]);
  const [accountNumber, setAccountNumber] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();
  console.log("out of the userEffect");

  useEffect(() => {
    console.log("inside 1 of the userEffect");
    const { adminJWT } = location.state || {};
    if (adminJWT) {
      localStorage.setItem("authToken", adminJWT);
    }

    const token = localStorage.getItem("authToken");
    if (!token) {
      navigate("/login");
    }
  }, [location, navigate]);

  const getAuthHeader = () => {
    const token = localStorage.getItem("authToken");
    console.log("Retrieved token:", token); // For debugging
    console.log(token);
    if (!token) {
      console.error("No token found in localStorage");
      return {};
    }

    // Check if the token is a valid JWT (contains two periods)
    // if ((token.match(/\./g) || []).length !== 3) {
    //   console.error("Invalid token format");
    //   return {};
    // }

    return { Authorization: `Bearer ${token}` };
  };
  // console.log(token);
  const fetchAllData = async () => {
    setLoading(true);
    setError("");
    try {
      const headers = getAuthHeader();
      console.log("Request headers:", headers);
      if (!headers.Authorization) {
        throw new Error("No valid authorization token found");
      }
      // Fetch account data
      const accountResponse = await axios.get(
        `http://localhost:8080/api/account/${parseInt(accountNumber)}`,
        { headers }
      );
      // const userId = localStorage.setItem("userId", userId);
      setAccountData(accountResponse.data);
      console.log(accountNumber);
      console.log(parseInt(accountNumber));
      console.log("Account data:", accountResponse.data);
      console.log("inside 2 of the userEffect");
      // Use the userId from the account data to fetch user details
      // const userId = localStorage.setItem("userId", userId);

      const userId = accountResponse.data.userId;
      localStorage.setItem("userId", userId);

      const userResponse = await axios.get(
        `http://localhost:8080/api/users/${userId}`,
        { headers: getAuthHeader() }
      );
      setUserDetails(userResponse.data);

      // const userId = localStorage.setItem("userId", userId);
      // Fetch loan details
      const loanResponse = await axios.get(`http://localhost:8080/api/loan`, {
        headers: getAuthHeader(),
      });
      const userLoan = loanResponse.data.find((loan) => loan.userId === userId);
      setLoanDetails(userLoan);
      console.log("inside 3 of the userEffect");

      // Fetch transactions
      const transactionsResponse = await axios.get(
        `http://localhost:8080/api/transaction/${userId}`,
        { headers: getAuthHeader() }
      );
      setTransactions(transactionsResponse.data);
      console.log("inside 4 of the userEffect");
      // Simulate a 2-second loading time

      await new Promise((resolve) => setTimeout(resolve, 2000));
    } catch (error) {
      console.error("Error fetching data:", error);
      if (error.response) {
        console.error("Response data:", error.response.data);
        console.error("Response status:", error.response.status);
        console.error("Response headers:", error.response.headers);
      } else if (error.request) {
        console.error("No response received:", error.request);
      } else {
        console.error("Error setting up request:", error.message);
      }

      if (error.response && error.response.status === 403) {
        setError("Access denied. Please log in again.");
        handleLogout();
      } else {
        setError(`Failed to fetch data: ${error.message}`);
      }
    } finally {
      setLoading(false);
    }
  };
  console.log("out 2 of the userEffect");
  const userId = localStorage.getItem("authToken");
  const localstoredUserId = localStorage.getItem("authToken", userId);
  const handleDeleteUser = async () => {
    try {
      await axios.delete(
        `http://localhost:8080/api/user/${localstoredUserId}`,
        {
          headers: getAuthHeader(),
        }
      );
      alert("User deleted successfully");
      setAccountData(null);
      setUserDetails(null);
      setLoanDetails(null);
      setTransactions([]);
      setAccountNumber("");
    } catch (error) {
      console.error("Error deleting user:", error);
      setError("Failed to delete user. Please try again.");
    }
  };
  console.log("out 3 of the userEffect");
  // const userId = localStorage.setItem("userId", userId);
  const handleLoanAction = async (action) => {
    try {
      await axios.post(
        `http://localhost:8080/api/loan/${localstoredUserId}`,
        { action },
        { headers: getAuthHeader() }
      );
      fetchAllData();
    } catch (error) {
      console.error(`Error ${action} loan:`, error);
      setError(`Failed to ${action} loan. Please try again.`);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    localStorage.removeItem("userId");
    localStorage.removeItem("authToken");
    localStorage.removeItem("applied");
    navigate("/");
  };
  console.log("before return JSX");
  return (
    <div className="admin-dashboard">
      <div className="admin-dashboard-container">
        <header className="admin-dashboard-header">
          <span className="admin-dashboard-logo">Easy Banking</span>
          <h1 className="admin-dashboard-title">Admin Dashboard</h1>
          <div className="admin-user-info">
            <span className="admin-username">Admin</span>
            <span className="admin-user-avatar">👤</span>
          </div>
        </header>

        <div className="admin-dashboard-content">
          <nav className="admin-dashboard-nav">
            <button onClick={() => navigate("/admin-dashboard")}>
              Dashboard
            </button>
            <button onClick={() => navigate("/admin-users")}>
              Manage Users
            </button>
            <button onClick={() => navigate("/admin-loans")}>
              Manage Loans
            </button>
            <button onClick={() => navigate("/admin-reports")}>Reports</button>
            <button onClick={handleLogout}>Log out</button>
          </nav>

          <main className="admin-dashboard-main">
            <section className="admin-user-search">
              <h2>User Search</h2>
              <div className="admin-search-input">
                <input
                  type="text"
                  value={accountNumber}
                  onChange={(e) => setAccountNumber(e.target.value)}
                  placeholder="Enter Account Number"
                />
                <button onClick={fetchAllData} disabled={loading}>
                  {loading ? "Searching..." : "Search"}
                </button>
              </div>
              {error && <p className="error-message">{error}</p>}
            </section>

            {loading && <div className="loading">Loading...</div>}

            {!loading && accountData && userDetails && (
              <section className="admin-user-details">
                <h2>User Details</h2>
                <div className="user-info">
                  <p>
                    <strong>Name:</strong> {userDetails.first_Name}{" "}
                    {userDetails.last_Name}
                  </p>
                  <p>
                    <strong>Account Number:</strong> {accountData.accNumber}
                  </p>
                  <p>
                    <strong>Account Balance: </strong> {accountData.accBalance}
                  </p>
                  <p>
                    <strong>Email:</strong> {userDetails.email}
                  </p>
                  <p>
                    <strong>Account Type:</strong> {accountData.accType}
                  </p>
                  <p>
                    <strong>Account Created:</strong> {accountData.accCreatedAt}
                  </p>
                </div>

                <div className="admin-actions">
                  <button
                    onClick={handleDeleteUser}
                    className="delete-user-btn"
                  >
                    Delete User
                  </button>
                </div>

                {loanDetails && (
                  <div className="loan-details">
                    <h3>Loan Details</h3>
                    <p>
                      <strong>Loan Type:</strong> {loanDetails.loanType}
                    </p>
                    <p>
                      <strong>Amount Required:</strong>
                      {loanDetails.amountRequired}
                    </p>
                    <p>
                      <strong>Loan Status:</strong> {loanDetails.loanStatus}
                    </p>
                    {loanDetails.loanStatus === "Pending" && (
                      <div className="loan-actions">
                        <button onClick={() => handleLoanAction("approve")}>
                          Approve Loan
                        </button>
                        <button
                          onClick={() => handleLoanAction("reject")}
                          style={{ backgroundColor: "#c0392b" }}
                        >
                          Reject Loan
                        </button>
                      </div>
                    )}
                  </div>
                )}

                <div className="transaction-history">
                  <h3>Transaction History</h3>
                  <table>
                    <thead>
                      <tr>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Balance</th>
                      </tr>
                    </thead>
                    <tbody>
                      {transactions.length > 0 ? (
                        transactions.map((transaction, index) => (
                          <tr key={index}>
                            <td>{transaction.transferDate}</td>
                            <td>{transaction.transferDetails}</td>
                            <td>{transaction.amountTransfered}</td>
                            <td>{transaction.balance}</td>
                          </tr>
                        ))
                      ) : (
                        <tr>
                          <td colSpan="4">No transactions available</td>
                        </tr>
                      )}
                    </tbody>
                  </table>
                </div>
              </section>
            )}
          </main>
        </div>
      </div>
    </div>
  );
};

export default Admin;
