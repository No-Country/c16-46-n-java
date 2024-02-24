

// initial user login value
/* const initialLogin = JSON.parse(sessionStorage.getItem("login") ?? {
    isAuth: false,
    user: undefined,
}) */

import { useState } from "react"

export const useAuth = () => {

    const handlerLogin = ({email, password}) => {
        console.log("PENDIENTE LOGIN DEL USER!!!")

    }

    const handlerLogout = () => {
        console.log("PENDIENTE LOGOUT DEL USUARIO!!!")
    }

    return {
        handlerLogin,
        handlerLogout,
    }
}