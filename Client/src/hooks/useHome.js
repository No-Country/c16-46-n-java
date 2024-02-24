import { useReducer, useState } from "react";
import { usersReducer } from "../reducers/usersReducer";
import { login, save } from "../services/userService";

const initialPost = []

const initialUserData = {
  id: 0,
  username: "",
  email: "",
  password: ""
}

const initialError = {}

export const useHome = () => {

  const [user, dispatch] = useReducer(usersReducer, initialUserData);

  const [isRegisterOpen, setRegisterOpen] = useState(false)
  const [sidebarVisible, setSidebarVisible] = useState(false);

  // FORMULARIO VISIBLE
  const handlerOpenSidebar = () => {
    setSidebarVisible(true);
  };

  const handlerCloseSideBar = () => {
    setSidebarVisible(false);
    setRegisterOpen(false)
  };

  const handlerRegisterOpen = () => {
    setRegisterOpen(true)
  }

  const handlerRegisterClose = () => {
    setRegisterOpen(false)
  }


  const handlerRegisterUser = async (user) => {
    let response;
    try{
      response = await save(user)

      console.log("Register Status!!!", response.status)
    }catch(error){
      if(error.response){
        console.log("Register error!!!  ", error.response.data)
        console.log("Register error!!!  ", error)
      }
    }
  }

  const handlerLoginUser = async (user) => {
    // must return the response to notify users the request status
    let response

    try{
      response = await login(user)

      console.log("Login User!!!", response.status)
    }catch(error){
      console.log("Login error!!!  ", error.response.data)
        console.log("Login error!!!  ", error)
    }
  }



  return {
    sidebarVisible,
    isRegisterOpen,
    handlerOpenSidebar,
    handlerCloseSideBar,
    handlerRegisterOpen,
    handlerRegisterClose,
    handlerRegisterUser,
    handlerLoginUser
  };
};
