import React from "react";
import "./transaction.css";

const Transaction = ({ transaction, index }) => {
  console.log("Rendering Transaction:", transaction);
  return (
    <tr key={transaction.transactionID} className="transaction-head">
      <td className="index">{index + 1}</td>
      <td className="transaction-details">{transaction.transferDetails}</td>
      <td className="amount-transfered">
        {transaction.amountTransfered} <span className="credited">cr</span>
      </td>
      <td className="acc-balance">{transaction.balance}</td>
      <td className="date">{transaction.transferDate}</td>
    </tr>
  );
};

export default Transaction;
