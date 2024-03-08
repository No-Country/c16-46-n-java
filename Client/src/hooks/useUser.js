import { useEffect, useReducer, useState } from "react";
import { login, save } from "../services/userService";
import { userReducer } from "../reducers/userReducer";

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

 
  const [isAdmin, setAdmin] = useState(initialLogin.user.administrator ? true : false)

  const handlerRegisterUser = async (user) => {
    let response;
    try {
      response = await save(user);

      console.log("Register Status!!!", response.data);
    } catch (error) {
      if (error.response) {
        console.log("Register error!!!  ", error.response.data);
        console.log("Register error!!!  ", error);
      }
    }
  };

  const handlerLoginUser = async (user) => {
    // must return the response to notify users the request status
    let response;

    try {
      response = await login(user);

      console.log("Login User!!!", response);
    } catch (error) {
      console.log("Login error!!!  ", error);
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
