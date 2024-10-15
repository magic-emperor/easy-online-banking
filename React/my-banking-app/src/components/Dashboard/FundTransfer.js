import ReactDOM from "react-dom";
import "./deposit.css";
import React, { useContext, useState, useEffect } from "react";
import axios from "axios";
import { AccountContext } from "../Context/AccountProvider";

function FundTransfer({ setFundTransfer }) {
  const portalRoot = document.getElementById("portalRoot");
  const { account, setAccount } = useContext(AccountContext);
  const [receiverAccNumber, setReceiverAccNumber] = useState("");
  const [inputAmount, setInputAmount] = useState("");
  const [userId, setUserId] = useState(sessionStorage.getItem("userId"));

  useEffect(() => {
    console.log("GET USER ID:", userId);
    const token = localStorage.getItem("authToken");
    if (userId && token) {
      axios
        .get(`http://localhost:8080/api/account/userid/${userId}`, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((response) => {
          setAccount(response.data);
        })
        .catch((error) => {
          console.error("Error fetching account data:", error);
        });
    }
  }, [userId, setAccount]);

  if (!portalRoot) {
    console.error("Portal root not found");
    return null;
  }

  function handleInputChange(e) {
    setInputAmount(e.target.value);
  }

  function handleReceiverAccChange(e) {
    setReceiverAccNumber(e.target.value);
  }

  function handleDeposit(e) {
    e.preventDefault();
    const token = localStorage.getItem("authToken");

    if (account.accBalance < inputAmount || account.accBalance === 0) {
      alert("Insufficient balance");
      return;
    } else if (account.accNumber === parseInt(receiverAccNumber)) {
      alert("Account number cannot be the same");
    } else if (inputAmount < 0) {
      alert("Please insert a valid amount");
      return;
    }

    const senderAccountBody = {
      accNumber: account.accNumber,
      accBalance: account.accBalance - Number(inputAmount),
      accCreatedAt: account.accCreatedAt,
      accType: account.accType,
      userId: account.userId,
    };

    axios
      .put(
        `http://localhost:8080/api/account/acc/${account.accNumber}`,
        senderAccountBody,
        {
          headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`,
          },
        }
      )
      .then((response) => {
        console.log("Sender account updated:", response.data);
        setAccount({ ...account, accBalance: senderAccountBody.accBalance });

        axios
          .get(`http://localhost:8080/api/account/${receiverAccNumber}`, {
            headers: { Authorization: `Bearer ${token}` },
          })
          .then((receiverResponse) => {
            const receiverAccount = receiverResponse.data;

            const receiverAccountBody = {
              accId: receiverAccount.accId,
              userId: receiverAccount.userId,
              accNumber: receiverAccNumber,
              accBalance: receiverAccount.accBalance + Number(inputAmount),
              accCreatedAt: receiverAccount.accCreatedAt,
              accType: receiverAccount.accType,
            };

            return axios.put(
              `http://localhost:8080/api/account/acc/${receiverAccNumber}`,
              receiverAccountBody,
              {
                headers: {
                  "Content-Type": "application/json",
                  " Authorization": `Bearer ${token}`,
                },
              }
            );
          })
          .then((receiverUpdateResponse) => {
            console.log(
              "Receiver account updated:",
              receiverUpdateResponse.data
            );
            setFundTransfer(false);
          })
          .catch((error) => {
            console.error("Error updating receiver's account:", error);
          });
      })
      .catch((error) => {
        console.error("Error updating sender's account:", error);
      });
  }

  return ReactDOM.createPortal(
    <div className="deposit-overlay" onClick={() => setFundTransfer(false)}>
      <div className="deposit-modal" onClick={(e) => e.stopPropagation()}>
        <div className="deposit-inner">
          <h1>Fund Transfer</h1>
          <form onSubmit={handleDeposit}>
            <label className="fund-amount-details">Amount:</label>
            <input
              type="number"
              className="fund-amount number-input"
              name="accBalance"
              value={inputAmount}
              placeholder="Enter Amount Here"
              autoComplete="off"
              required
              onChange={handleInputChange}
            />
            <label className="acc-number-lable">Account Number</label>
            <input
              type="number"
              className="account-no number-input"
              placeholder="Enter Receiver Account Number"
              autoComplete="off"
              name="accountNo"
              required
              onChange={handleReceiverAccChange}
            />
            <nav className="deposit-btn-div">
              <button type="submit" className="fund-btn">
                Fund Transfer
              </button>
            </nav>
          </form>
          <button
            className="close-button"
            onClick={() => setFundTransfer(false)}
          >
            Close
          </button>
        </div>
      </div>
    </div>,
    portalRoot
  );
}

export default FundTransfer;
