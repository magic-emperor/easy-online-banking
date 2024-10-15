import ReactDOM from "react-dom";
import "./deposit.css";
import React, { useContext, useState, useEffect } from "react";
import axios from "axios";
import { AccountContext } from "../Context/AccountProvider";

function Deposit({ setDeposit, onSuccess }) {
  const portalRoot = document.getElementById("portalRoot");
  const { account, setAccount } = useContext(AccountContext);
  const [inputAmount, setInputAmount] = useState("");
  const [transaction, setTransaction] = useState({
    TransactionID: null,
    userId: sessionStorage.getItem("userId"),
    transferDate: "",
    transferDetails: "",
    amountTransfered: 0,
    balance: account ? account.accBalance : 0,
  });

  useEffect(() => {
    const userId = sessionStorage.getItem("userId");
    const token = localStorage.getItem("authToken");
    if (userId && token) {
      axios
        .get(`http://localhost:8080/api/account/userid/${userId}`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((response) => {
          setAccount(response.data);
          setTransaction((prev) => ({
            ...prev,
            balance: response.data.accBalance,
          }));
        })
        .catch((error) => {
          console.error("Error fetching account data:", error);
        });
    }
  }, [setAccount]);

  if (!portalRoot) {
    console.error("Portal root not found");
    return null;
  }

  function handleInputChange(e) {
    setInputAmount(e.target.value);
  }

  function handleDeposit(e) {
    e.preventDefault();

    const userId = sessionStorage.getItem("userId");
    const token = localStorage.getItem("authToken");

    // Check if userId is null or undefined
    if (!userId) {
      console.error("Error: userId is null or undefined");
      return;
    }

    const currentDate = new Date().toISOString().split("T")[0];

    const updatedAccount = {
      ...account,
      accBalance: account.accBalance + Number(inputAmount),
    };

    console.log("Updated account data before PUT:", updatedAccount);

    //check if token is available before making API call
    if (token) {
      axios
        .put(
          `http://localhost:8080/api/account/user/${userId}`,
          updatedAccount,
          {
            headers: {
              "Content-Type": "application/json",
              "Authorization": `Bearer ${token}`,
            },
          }
        )
        .then((response) => {
          console.log("Response from PUT request:", response.data);
          setAccount(response.data);
          setDeposit(false);
          onSuccess();
        })
        .catch((error) => {
          console.log("Error updating account balance:", error);
        });

      const updatedTransaction = {
        TransactionID: null,
        userId: userId,
        transferDate: currentDate,
        transferDetails: `Credited to account ${account.accNumber}`,
        amountTransfered: Number(inputAmount),
        balance: updatedAccount.accBalance,
      };

      // POST request for transaction
      axios
        .post("http://localhost:8080/api/transaction", updatedTransaction, {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`,
          },
        })
        .then((res) => {
          console.log("Transaction successfully recorded:", res.data);
        })
        .catch((error) => console.log("Error recording transaction:", error));
    } else {
      console.error("Error: Token is missing.");
    }
  }

  return ReactDOM.createPortal(
    <div className="deposit-overlay" onClick={() => setDeposit(false)}>
      <div className="deposit-modal" onClick={(e) => e.stopPropagation()}>
        <div className="deposit-inner">
          <h1>Deposit</h1>
          <form onSubmit={handleDeposit}>
            <label className="deposit-amount-details">Amount:</label>
            <input
              type="number"
              className="deposit-amount"
              name="accBalance"
              value={inputAmount}
              placeholder="Enter Amount Here"
              autoComplete="off"
              required
              onChange={handleInputChange}
            />
            <nav>
              <button type="submit" className="deposit-btn">
                Deposit
              </button>
            </nav>
          </form>
          <button className="close-button" onClick={() => setDeposit(false)}>
            Close
          </button>
        </div>
      </div>
    </div>,
    portalRoot
  );
}

export default Deposit;
