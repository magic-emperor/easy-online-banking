import React, { useContext, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import axios from "axios";
import { AccountContext } from "../Context/AccountProvider";
import "../Dashboard/loan.css";

function Loan({ setLoan }) {
  const portalRoot = document.getElementById("portalRoot");
  const { account } = useContext(AccountContext);
  const [loanType, setLoanType] = useState("Home Loan");
  const [loanData, setLoanData] = useState({
    userId: sessionStorage.getItem("userId"),
    userName: "",
    userAddress: "",
    loanType: "Home Loan",
    amountRequired: 0,
    accountNumber: account ? account.accNumber : "",
    mobileNumber: "",
    loanStatus: "Pending",
  });

  useEffect(() => {
    const token = localStorage.getItem("authToken");
    const userId = sessionStorage.getItem("userId");

    if (userId && token) {
      axios
        .get(`http://localhost:8080/api/account/userid/${userId}`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((response) => {
          setLoanData((prev) => ({
            ...prev,
            accountNumber: response.data.accNumber,
          }));
        })
        .catch((error) => {
          console.error("Error fetching account data:", error);
        });
    }
  }, []);

  if (!portalRoot) {
    console.error("Portal root not found");
    return null;
  }

  function handleInputChange(e) {
    const { name, value } = e.target;
    setLoanData((prev) => ({
      ...prev,
      [name]: value,
    }));
  }

  function handleLoanTypeChange(e) {
    setLoanType(e.target.value);
    setLoanData((prev) => ({
      ...prev,
      loanType: e.target.value,
    }));
  }

  function handleLoanSubmission(e) {
    e.preventDefault();
    const userId = sessionStorage.getItem("userId");
    const token = localStorage.getItem("authToken");

    const newLoan = {
      ...loanData,
      userId: userId,
      loanStatus: "Pending",
    };

    console.log("Updated loan data before POST:", newLoan);

    axios
      .post("http://localhost:8080/api/loan", newLoan, {
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`,
        },
      })
      .then((res) => {
        console.log("Loan application successfully submitted:", res.data);
        setLoan(false);
        // You might want to show a success message to the user here
      })
      .catch((error) => {
        console.error("Error submitting loan application:", error);
        // You might want to show an error message to the user here
      });
  }

  return ReactDOM.createPortal(
    <div className="loan-overlay" onClick={() => setLoan(false)}>
      <div className="loan-modal" onClick={(e) => e.stopPropagation()}>
        <div className="loan-inner">
          <h1>Apply for a Loan</h1>
          <form onSubmit={handleLoanSubmission}>
            <label className="input-label">Full Name:</label>
            <input
              type="text"
              name="userName"
              className="input-field"
              autoComplete="off"
              value={loanData.userName}
              placeholder="Enter your full name"
              required
              onChange={handleInputChange}
            />

            <label className="input-label">Address:</label>
            <input
              type="text"
              name="userAddress"
              className="input-field"
              autoComplete="off"
              value={loanData.userAddress}
              placeholder="Enter your address"
              required
              onChange={handleInputChange}
            />

            <label className="input-label">Mobile Number:</label>
            <input
              type="text"
              name="mobileNumber"
              className="input-field"
              autoComplete="off"
              value={loanData.mobileNumber}
              placeholder="Enter your mobile number"
              required
              onChange={handleInputChange}
            />

            <label className="input-label">Loan Type:</label>
            <select
              name="loanType"
              value={loanType}
              onChange={handleLoanTypeChange}
              required
            >
              <option value="Home Loan">Home Loan</option>
              <option value="Gold Loan">Gold Loan</option>
              <option value="Personal Loan">Personal Loan</option>
              <option value="Business Loan">Business Loan</option>
              <option value="Vehicle Loan">Vehicle Loan</option>
            </select>

            <label className="input-label">Amount Required:</label>
            <input
              type="number"
              name="amountRequired"
              className="input-field"
              value={loanData.amountRequired}
              placeholder="Enter Loan Amount"
              autoComplete="off"
              required
              onChange={handleInputChange}
            />

            <div className="loan-submit-btn">
              <button type="submit" className="loan-btn">
                Apply for Loan
              </button>
            </div>
          </form>
          <div className="loan-close-btn">
            <button className="close-button" onClick={() => setLoan(false)}>
              Close
            </button>
          </div>
        </div>
      </div>
    </div>,
    portalRoot
  );
}

export default Loan;
