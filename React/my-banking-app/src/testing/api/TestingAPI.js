// // import React, { useState, useEffect } from "react";
// // import APIStored from "../components/API/APIStored";
// // import axios from "axios";
// // import { useParams } from "react-router-dom";

// // function TestingAPI() {
// //   const [data, setData] = useState([]);
// //   const { userId } = useParams();
// //   const [accData, setAccountData] = useState([]);

// //   useEffect(() => {
// //     APIStored()
// //       .then((response) => {
// //         console.log(JSON.stringify(response.data)); // Check the entire response object
// //         setData(response.data);
// //       })
// //       .catch((error) => console.error("Error", error));
// //   }, []);

// //   // console.log(JSON.stringify([...data]));
// //   // // const userAPIRes = data;
// //   // // let MapFunUser = userAPIRes.map((items) => parseInt(items.id));

// //   // data.map((item) => console.log(item.id));

// //   //for account
// //   const accApi = axios.get("http://localhost:8080/api/account");
// //   useEffect(() => {
// //     accApi
// //       .then((response) => {
// //         console.log(JSON.stringify(response.data)); // Check the entire response object
// //         setAccountData(response.data);
// //       })
// //       .catch((error) => console.error("Error", error));
// //   }, []);

// //   // console.log(JSON.stringify(accData));
// //   // // // let MapFunAcc = accData.map((items) => items.id);
// //   // // console.log(MapFunAcc);
// //   // // accData.map((item) => console.log(item.id));

// //   // data.map((item) => console.log(item.id));

// //   // // console.log(parseInt(MapFunAcc), parseInt(MapFunUser));

// //   // // if (parseInt(MapFunAcc) === parseInt(MapFunUser)) {
// //   // // } else {
// //   // //   console.log("BUMMER");
// //   // // }
// //   // console.log(JSON.stringify(data));

// //   // accData.map((item) =>
// //   //   console.log(
// //   //     item.userId,
// //   //     item.accNumber,
// //   //     item.accBalance,
// //   //     item.accCreatedAt,
// //   //     item
// //   //   )
// //   // );
// //   // console.log(parseInt(MapFunAcc));

// //   // const intAPI = axios.get(`http://localhost:8080/api/account/${24}`);

// //   // useEffect(() => {
// //   //   intAPI
// //   //     .then((response) => {
// //   //       console.log(JSON.stringify(response.data)); // Check the entire response object
// //   //       setAccountData(response.data);
// //   //     })
// //   //     .catch((error) => console.error("Error", error));
// //   // }, []);

// //   data.map((item) => {
// //     console.log(item.id);
// //   });
// //   const mapingAcc = data.map((item) => {
// //     console.log(item.id);
// //   });

// //   console.log(mapingAcc);

// //   useEffect(() => {
// //     axios
// //       .get(`http://localhost:8080/api/account/userid/${userId}`) // Now using userId in the URL
// //       .then((response) => {
// //         // setAccount(response.data);
// //         console.log(response.data.userId);
// //       })
// //       .catch((error) => console.log("Error fetching account data:", error));
// //   }, [userId]);
// //   return (
// //     <div>
// //       {/* <table border="1">
// //         <thead>
// //           <tr>
// //             <th>ID</th>
// //             <th>First Name</th>
// //             <th>Last Name</th>
// //             <th>Email</th>
// //             <th>Password</th>
// //             <th>Mobile</th>
// //           </tr>
// //         </thead>

// //         <tbody>
// //           {data.map((item) => (
// //             <tr key={item.id}>
// //               <td>{item.id}</td>
// //               <td>{item.first_Name}</td>
// //               <td>{item.last_Name}</td>
// //               <td>{item.email}</td>
// //               <td>{item.password}</td>
// //               <td>{item.mobile}</td>
// //             </tr>
// //           ))}
// //         </tbody>
// //         <thead>
// //           <tr>
// //             <th>ID</th>
// //             <th>acc Name</th>
// //             <th>acc Name</th>
// //             <th>acccc</th>
// //             <th>accc</th>
// //             <th>accccc</th>
// //           </tr>
// //         </thead>
// //         <tbody>
// //           {accData.map((item) => (
// //             <tr key={item.id}>
// //               <td>{item.id}</td>
// //               <td>{item.userId}</td>
// //               <td>{item.accNumber}</td>
// //               <td>{item.accBalance}</td>
// //               <td>{item.accCreatedAt}</td>
// //               <td>{item.accType}</td>
// //             </tr>
// //           ))}
// //         </tbody>
// //       </table> */}
// //     </div>
// //   );
// // }

// // export default TestingAPI;

// // // const APIwithoutID = axios
// // //   .get(`http://localhost:8080/api/account`)
// // //   .then((response) => {
// // //     console.log(
// // //       "this is response.data without ID " + JSON.stringify(response.data)
// // //     );
// // //     console.log(JSON.stringify("this is wthout id" + JSON.stringify(data)));
// // //   })
// // //   .catch((error) => console.log("Error fetching account data:", error));

// // // console.log(JSON.stringify(data));

// // // data.map((item) => {
// // //   console.log("Items with data using MAP" + JSON.stringify(item.id));
// // // });

// // // useEffect(() => {
// // //   axios
// // //     .get(`http://localhost:8080/api/account`)
// // //     .then((response) => {
// // //       // console.log(
// // //       //   "this is response.data with ID" + JSON.stringify(response.data)
// // //       // );
// // //       console.log(
// // //         JSON.stringify(
// // //           "this is data wth id" + JSON.stringify(response.data.accId)
// // //         )
// // //       ); //as na URL
// // //     })
// // //     .catch((error) => console.log("Error fetching account data:", error));
// // // });

// import React, { useState, useEffect } from "react";
// import APIStored from "../../components/API/APIStored";
// import axios from "axios";

// function TestingAPI() {
//   const [data, setData] = useState([]);
//   const [selectedUserId, setSelectedUserId] = useState(null); // Store selected user ID
//   const [accData, setAccountData] = useState([]);

//   // Fetch user data using APIStored
//   useEffect(() => {
//     APIStored()
//       .then((response) => {
//         console.log("User Data:", JSON.stringify(response.data)); // Logging user data for checking
//         setData(response.data);
//       })
//       .catch((error) => console.error("Error fetching user data", error));
//   }, []);

//   // Fetch account data based on selectedUserId
//   useEffect(() => {
//     if (selectedUserId) {
//       axios
//         .get(`http://localhost:8080/api/account/id/${selectedUserId}`) // Fetching account using ID from user data
//         .then((response) => {
//           console.log("Account Data:", JSON.stringify(response.data));
//           setAccountData(response.data); // Setting account data from the response
//         })
//         .catch((error) =>
//           console.error("Error fetching account data using ID:", error)
//         );
//     }
//   }, [selectedUserId]); // API call runs when selectedUserId changes

//   const handleUserClick = (id) => {
//     setSelectedUserId(id); // Set selected user ID when a user is clicked
//   };

//   return (
//     <div>
//       <h3>User Details</h3>
//       <table border="1">
//         <thead>
//           <tr>
//             <th>ID</th>
//             <th>First Name</th>
//             <th>Last Name</th>
//             <th>Email</th>
//             <th>Mobile</th>
//             <th>Action</th>
//           </tr>
//         </thead>
//         <tbody>
//           {data.map((item) => (
//             <tr key={item.id}>
//               <td>{item.id}</td>
//               <td>{item.first_Name}</td>
//               <td>{item.last_Name}</td>
//               <td>{item.email}</td>
//               <td>{item.mobile}</td>
//               <td>
//                 <button onClick={() => handleUserClick(item.id)}>
//                   Get Account
//                 </button>
//               </td>
//             </tr>
//           ))}
//         </tbody>
//       </table>

//       <h3>Account Details</h3>
//       <table border="1">
//         <thead>
//           <tr>
//             <th>ID</th>
//             <th>User ID</th>
//             <th>Account Number</th>
//             <th>Account Balance</th>
//             <th>Created Date</th>
//             <th>Account Type</th>
//           </tr>
//         </thead>
//         <tbody>
//           {accData ? (
//             <tr key={accData.accId}>
//               <td>{accData.accId}</td>
//               <td>{accData.userId}</td>
//               <td>{accData.accNumber}</td>
//               <td>{accData.accBalance}</td>
//               <td>{accData.accCreatedAt}</td>
//               <td>{accData.accType}</td>
//             </tr>
//           ) : (
//             <tr>
//               <td colSpan="6">No account data available</td>
//             </tr>
//           )}
//         </tbody>
//       </table>
//     </div>
//   );
// }

// export default TestingAPI;
function TestingAPI() {
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
}

export default TestingAPI;
