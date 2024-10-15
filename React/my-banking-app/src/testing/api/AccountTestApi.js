// import axios from "axios";
// import { useParams } from "react-router-dom";

// function AccountTestApi() {
//   //   const accId = 1;
//   const { id } = useParams();

//   const API = (id) => axios.get("http://localhost:8080/api/account/1");

//   API(id).then((resp) => console.log(resp.data));
// }

// export default AccountTestApi;
// import React from "react";
import { createContext } from "react";
function AccountTestApi() {
  const AccountContext = createContext();
  console.log(AccountContext);
}

export default AccountTestApi;
