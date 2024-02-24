import React from "react";
import { useHome } from "../hooks/useHome";
import { HomeContext } from "./HomeContext";

const HomeProvider = ({ children }) => {
  const {
    sidebarVisible,
    isRegisterOpen,
    handlerOpenSidebar,
    handlerCloseSideBar,
    handlerRegisterOpen,
    handlerRegisterClose,
    handlerRegisterUser,
    handlerLoginUser
  } = useHome();

  return (
    <HomeContext.Provider
      value={{
        sidebarVisible,
        isRegisterOpen,
        handlerOpenSidebar,
        handlerCloseSideBar,
        handlerRegisterOpen,
        handlerRegisterClose,
        handlerRegisterUser,
        handlerLoginUser
      }}
    >
      {children}
    </HomeContext.Provider>
  );
};

export default HomeProvider;
