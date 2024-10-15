import React, { useEffect, useState, useContext, useCallback } from "react";
import "../Dashboard/dashboard.css";
import axios from "axios";
import Deposit from "./Deposit";
import FundTransfer from "./FundTransfer";
import { useNavigate, useLocation } from "react-router-dom";
import { AccountContext } from "../Context/AccountProvider";
import "../Dashboard/Transaction/transaction.css";
import Loan from "./Loan";
import Transaction from "./Transaction/Transaction";

function Dashboard() {
  const { account, setAccount } = useContext(AccountContext);
  const [loading, setLoading] = useState(true);
  const [userDetail, setUserDetails] = useState({});
  const [depositPopUp, setDepositPopUp] = useState(false);
  const [fundTransferPopUp, setFundTransferPopUp] = useState(false);
  const [transactions, setTransactions] = useState([]);
  const [transactionsLoading, setTransactionsLoading] = useState(true);
  const [refreshTrigger, setRefreshTrigger] = useState(0);
  const [loanPopUp, setLoanPopUp] = useState(false);
  const [error, setError] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  const fetchTransactions = useCallback((userId, token) => {
    console.log("Fetching transactions for userId:", userId);
    setTransactionsLoading(true);
    axios
      .get(`http://localhost:8080/api/transaction/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        console.log("Transactions received:", response.data);
        setTransactions(Array.isArray(response.data) ? response.data : []);
      })
      .catch((error) => {
        console.error("Error fetching transactions:", error);
        if (error.response && error.response.status === 404) {
          console.log("No transactions found for this user");
          setTransactions([]);
        } else {
          setError("Failed to fetch transactions");
        }
      })
      .finally(() => {
        setTransactionsLoading(false);
      });
  }, []);

  useEffect(() => {
    console.log("Dashboard component mounted");
    const { JWTaccount, SetJWTuser } = location.state || {};
    console.log("JWTaccount:", JWTaccount, "SetJWTuser:", SetJWTuser);

    let userId;
    if (SetJWTuser && SetJWTuser.userId) {
      userId = SetJWTuser.userId;
      console.log("Setting userId in sessionStorage:", userId);
      sessionStorage.setItem("userId", userId);
    } else {
      userId = sessionStorage.getItem("userId");
      console.log("Retrieved userId from sessionStorage:", userId);
    }

    const token = localStorage.getItem("authToken");
    console.log("Retrieved token from localStorage:", token);

    if (JWTaccount) {
      console.log("Using JWTaccount data");
      setAccount(JWTaccount);
      setUserDetails(SetJWTuser);
      setLoading(false);
    } else {
      if (userId && token) {
        console.log("Calling fetchAccountData and fetchUserDetails");
        fetchAccountData(userId, token);
        fetchUserDetails(userId, token);
      } else {
        console.error("No userId or token found");
        setError("Account Not Found Please Re-Login");
        setLoading(false);
      }
    }

    if (userId && token) {
      fetchTransactions(userId, token);
    }
  }, [fetchTransactions, refreshTrigger]);

  const refreshDashboard = () => {
    setRefreshTrigger((prev) => prev + 1);
  };

  const fetchAccountData = (userId, token) => {
    console.log("Fetching account data");
    axios
      .get(`http://localhost:8080/api/account/userid/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        console.log("Account data received:", response.data);
        setAccount(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching account data:", error);
        setError("Failed to fetch account data");
        setLoading(false);
      });
  };

  const fetchUserDetails = (userId, token) => {
    console.log("Fetching user details");
    axios
      .get(`http://localhost:8080/api/users/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        console.log("User details received:", response.data);
        setUserDetails(response.data);
      })
      .catch((error) => {
        console.error("Error fetching user details:", error);
        setError("Failed to fetch user details");
      });
  };

  const handleLogout = () => {
    // Clear localStorage and sessionStorage
    localStorage.removeItem("authToken");
    sessionStorage.removeItem("userId");
    setAccount(null);

    navigate("/");
  };
  console.log("Rendering Dashboard component, transactions:", transactions);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  if (!account || Object.keys(account).length === 0) {
    return <div>No account data available.</div>;
  }

  console.log("Rendering Dashboard component, transactions:", transactions);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!account || Object.keys(account).length === 0) {
    return <div>No account data available.</div>;
  }

  const userId = localStorage.getItem("userId");

  return (
    <div
      className={`dashboard ${
        depositPopUp || fundTransferPopUp || loanPopUp ? "dashboard-blur" : ""
      }`}
    >
      {loading && <div>Loading...</div>}
      {error && <div className="error-message">{error}</div>}

      <div className="dashboard-container">
        <header className="dashboard-header">
          <span className="dashboard-logo ">Easy Banking</span>
          <h1 className="dashboard-bank-name">Easy Online Banking</h1>
          <div className="dashboard-user-info">
            <span className="dashboard-username">
              Hi, {userDetail.first_Name}
            </span>
            <span className="dashboard-user-avatar">👤</span>
          </div>
        </header>

        <div className="dashboard-content">
          <nav className="dashboard-nav">
            <button onClick={() => navigate(`/dashboard/${userId}`)}>
              Dashboard
            </button>
            <button onClick={() => setLoanPopUp(true)}>Loan</button>
            <button onClick={() => navigate("/card")}>Cards</button>
            <button onClick={() => setDepositPopUp(true)}>Deposit</button>
            <button onClick={() => setFundTransferPopUp(true)}>
              Fund Transfer
            </button>
            <button onClick={handleLogout}>Log out</button>
          </nav>

          <main className="dashboard-main">
            <section className="account-summary">
              <div className="account-balance">
                <h2>Account Balance</h2>
                <p className="amount">{account.accBalance}</p>
              </div>
              <div className="account-details">
                <p className="account-detail-number">
                  Account Number: {account.accNumber}
                </p>
                <p className="account-detail-number">
                  Account Created: {account.accCreatedAt}
                </p>
              </div>
            </section>
            <section className="transaction-history">
              <h2>Transaction History</h2>
              <div className="transaction-table-container">
                <table className="transaction-table">
                  <thead className="transaction-head-dashboard">
                    <tr>
                      <th>Sl No.</th>
                      <th>Transaction Details</th>
                      <th>Amount</th>
                      <th>Balance</th>
                      <th>Date</th>
                    </tr>
                  </thead>
                  <tbody>
                    {transactions.length > 0 ? (
                      transactions.map((transaction, index) => (
                        <Transaction
                          key={transaction.transactionID}
                          transaction={transaction}
                          index={index}
                        />
                      ))
                    ) : (
                      <tr>
                        <td colSpan="5" className="no-transaction">
                          No Transactions Available
                        </td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            </section>
          </main>
        </div>
      </div>
      {depositPopUp && (
        <Deposit setDeposit={setDepositPopUp} onSuccess={refreshDashboard} />
      )}
      {fundTransferPopUp && (
        <FundTransfer
          setFundTransfer={setFundTransferPopUp}
          onSuccess={refreshDashboard}
        />
      )}
      {loanPopUp && (
        <Loan setLoan={setLoanPopUp} onSuccess={refreshDashboard} />
      )}
    </div>
  );
}

export default Dashboard;
