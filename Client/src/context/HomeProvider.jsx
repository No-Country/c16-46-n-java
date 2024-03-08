import React from "react";
import { useHome } from "../hooks/useHome";
import { HomeContext } from "./HomeContext";
import { useUser } from "../hooks/useUser";
import usePost from "../hooks/usePost";
import useComments from "../hooks/useComments";

const HomeProvider = ({ children }) => {

  // this contains all functions of each hooks
  const homeHookData = useHome()
  const userHookData = useUser()
  const postHookData = usePost()
  const commentHookData = useComments()


  const contextValue = {
    homeHookData,
    userHookData,
    postHookData,
    commentHookData
  }

  return (
    <HomeContext.Provider
      value={contextValue}
    >
      {children}
    </HomeContext.Provider>
  );
};

export default HomeProvider;
