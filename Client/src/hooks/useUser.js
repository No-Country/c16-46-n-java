import {  useReducer, useState } from "react";
import { login, save } from "../services/userService";
import { userReducer } from "../reducers/userReducer";
import { toast } from 'react-toastify';


// search a login
const initialLogin = JSON.parse(sessionStorage.getItem("login")) ?? {
  isAuth: false,
  user: {
    administrator: {id: 0}
  },
};

export const useUser = () => {
  // login status
  const [loginStatus, dispatch] = useReducer(userReducer, initialLogin);

 
  const [isAdmin, setAdmin] = useState(initialLogin.user.administrator.id ? true : false)

  const handlerRegisterUser = async (user) => {
    let response;
    try {
      response = await save(user);

      toast.success("Registro exitoso!");
      console.log(response);
     } catch (error) {
      if (error.response) {
        toast.error("Error al registrar usuario: " + error.response.data.message);
      }
    }
  };

  const handlerLoginUser = async (user) => {
    // must return the response to notify users the request status
    let response;
    try {
      response = await login(user);

      toast.success("Inicio de sesión exitoso!");
    } catch (error) {
      toast.error("Error al iniciar sesión: " + error.message);
    }

    // login success
    if (response.status === 200) {
      // user from db
      let { name, email, id, country, administrator, avatar } = response.data;

      // check for admin data
      if(administrator !== null) setAdmin(true)

      dispatch({
        type: "login",
        payload: {
          name,
          email,
          id,
          country,
          administrator,
          avatar,
        },
      });

      // saving data in session
      sessionStorage.setItem(
        "login",
        JSON.stringify({
          isAuth: true,
          user: {
            id,
            email,
            name,
            country,
            administrator,
          },
        })
      );
    }
  };


  const handlerLogout = () => {
    // change the isAuth to false
    dispatch({
      type: "logout",
    })
    // delete the local data user
    sessionStorage.removeItem("login")
  }

  return {
    loginStatus,
    isAdmin,
    handlerLoginUser,
    handlerRegisterUser,
    handlerLogout
  };
};
