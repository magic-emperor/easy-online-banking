import React, { useEffect, useId } from "react";
import "./card.css";
import card from "../../image/cards.jpg";
import { useState, useContext } from "react";
import axios from "axios";
import { AccountContext } from "../Context/AccountProvider";
import cardBackgroungImage from "../../image/card-sec-img.jpg";
import { FaArrowCircleLeft } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
function Card() {
  // const [account, setAccount] = useState({});
  const { account, setAccount } = useContext(AccountContext);
  const [applied, setApplied] = useState(false);
  const [localAccount, setLocalAccount] = useState({});
  // console.log(setAccount, localAccount);

  useEffect(() => {
    const userId = sessionStorage.getItem("userId");
    const token = localStorage.getItem("authToken");
    axios
      .get(`http://localhost:8080/api/account/userid/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((response) => {
        console.log("Account data received:", response.data);
      })
      .catch((error) => {
        console.error("Error fetching account data:", error);
        // setError("Failed to fetch account data");
      });
  }, []);

  const userId = sessionStorage.getItem("userId");

  const navigate = useNavigate();
  function goBack() {
    navigate(`/dashboard/${userId}`);
  }

  const handleApply = (e) => {
    e.preventDefault();
    console.log("handleApply called");
    sessionStorage.setItem("applied", true);
    const applied = sessionStorage.getItem("applied") === "true";
    setApplied(applied);
    console.log(applied);

    setTimeout(() => {
      goBack();
    }, 2000);
  };

  useEffect(() => {
    const applied = sessionStorage.getItem("applied") === "true";
    setApplied(applied);
  }, []);
  console.log("If changed;" + applied);

  console.log(account.accBalance);
  return (
    <div>
      <button className="go-back" onClick={goBack}>
        <FaArrowCircleLeft className="size" />
      </button>
      <div className="card-page">
        <div className="card">
          <img src={cardBackgroungImage} alt="cardBackground" />
          <h1 className="heading">Easy Online Banking</h1>
          <div className="card-body">
            <section className="debit-card">
              <img src={card} className="debit-card-img" alt="debitCardImage" />
              <div className="crad-details">
                <div className="card-bank-name">Easy Banking</div>
                <div className="card-type">Credit Card</div>
                <div className="card-number"></div>
                <div className="card-expiry"></div>
              </div>
              <div className="cr-card-status">
                {applied ? "Applied For Credit Card" : "Apply For Credit Card"}
              </div>
              {!applied && (
                <button
                  className={`cr-carc-btn ${applied ? "applied" : ""}`}
                  onClick={handleApply}
                >
                  {applied ? "Applied For Credit Card" : "Apply"}
                </button>
              )}
            </section>
            <div className="card-hr-line"></div>
            <section className="caredit-card">
              <img src={card} className="card-img" alt="CreditCardImage" />
              <div className="cr-card-details">
                <div className="cr-bank-name">Easy Banking</div>
                <div className="cr-card-type">Debit Card</div>
                <div className="cr-card-number">1234 5678 9012 3456</div>
                <div className="cr-card-expiry">12/25</div>
              </div>
              <div className="card-bal">
                <span className="bal">Balance:{account.accBalance}</span>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  );
}
export default Card;
