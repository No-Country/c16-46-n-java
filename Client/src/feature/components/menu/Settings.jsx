import { useContext, useEffect, useState } from "react";
import { HomeContext } from "../../../context/HomeContext";
import { MdLogout } from "react-icons/md";

const Settings = () => {
  const { userHookData, homeHookData } = useContext(HomeContext);

  const handleLogout = () => {
    userHookData.handlerLogout()
    homeHookData.handlerCloseSideBar()
  }

  return (
    <div className="w-full h-full flex flex-col items-center ">

      {/* LOGOUT BUTTON */}
      {userHookData.loginStatus.isAuth && (
        <button 
        onClick={handleLogout}
        className="absolute p-2 text-white bg-sky-900 rounded top-0 right-0 m-8">
          <MdLogout />
        </button>
      )}

      <h3 className="absolute w-full text-center top-3 text-gray-700 font-bold">
        Configuracion
      </h3>

      <div className="min-w-4/5 p-8 flex flex-col justify-center space-y-8 mt-4">
        {/* PROFILE IMAGE */}
        <picture className="w-full flex justify-center">
          <img
            src="https://images.vexels.com/media/users/3/140752/isolated/preview/17e31e592ab23bb0e8b2c0e76c0a4361-male-profile-avatar-5.png"
            alt="image profile"
            className="w-1/2 border-2 rounded-full p-4"
          />
        </picture>

        {/* USER DATA */}
        {userHookData.loginStatus.isAuth && (
          <table className="w-full">
            <tbody>
              <tr>
                <th className="text-left">Nombre:</th>
                <td className="text-right">
                  {userHookData.loginStatus.user.name}
                </td>
              </tr>

              <tr>
                <th className="text-left">Correo:</th>
                <td className="text-right">
                  {userHookData.loginStatus.user.email}
                </td>
              </tr>

              <tr>
                <th className="text-left">Pais:</th>
                <td className="text-right">
                  {userHookData.loginStatus.user.country}
                </td>
              </tr>
            </tbody>
          </table>
        )}

        {/* ACTIONS */}
        <div className="w-full flex flex-col space-y-2">
          <button className="w-full p-2 text-white bg-violet-900 rounded">
            Editar
          </button>
          {!userHookData.isAdmin && (
            <button className="w-full p-2 text-white bg-emerald-900 rounded">
              Vendedor
            </button>
          )}
        </div>
      </div>
    </div>
  );
};
export default Settings;
