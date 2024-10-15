// AccountContext.js
import React, { createContext, useState } from "react";

// Create the AccountContext
export const AccountContext = createContext();

// Create a provider component
export const AccountProvider = ({ children }) => {
  const [account, setAccount] = useState(null);

  return (
    <AccountContext.Provider value={{ account, setAccount }}>
      {children}
    </AccountContext.Provider>
  );
};
