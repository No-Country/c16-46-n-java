import React, { useContext } from "react";
import { IoCloseCircleOutline } from "react-icons/io5";
import LoginForm from "./forms/LoginForm";
import { Route, Routes } from "react-router-dom"

import PropTypes from "prop-types"
import Calendar from "./menu/Calendar";
import Notifications from "./menu/Notifications";
import Messages from "./menu/Messages";
import Settings from "./menu/Settings";
import { HomeContext } from "../../context/HomeContext";
import RegisterForm from "./forms/RegisterForm";

const SideBar = () => {
  const {sidebarVisible, handlerCloseSideBar} = useContext(HomeContext);

  return (
    <aside
      className={`fixed bg-blue-300 rounded z-10 bg-white top-[10vh] "translate-x-full" right-0 z-40 md:w-2/5 lg:w-1/4 w-10/12 h-screen shadow-lg duration-500 p-4 transition-transform ${
        sidebarVisible ? "" : "translate-x-full"
      }`}
    >
      {/* CLOSE BUTTON */}
      <button
        className="bg-blue-300 absolute top-2 rounded-full bg-gray-200 z-10"
        onClick={handlerCloseSideBar}
      >
        <IoCloseCircleOutline style={{fontSize:'1.5rem', zIndex:'0'}}/>
      </button>

      

      <div className="w-full h-full mt-10 p-2">
        {/* ADD CONTENT / FORM HERE */}
        <Routes>
           <Route path="/login" element={<LoginForm />} />
           <Route path="/register" element={<RegisterForm />}/>
           <Route path="/calendar" element={<Calendar />} />
           <Route path="/notifications" element={<Notifications />} />
           <Route path="/messages" element={<Messages />} />
           <Route path="/settings" element={<Settings />}/>
        </Routes>
      </div>
    </aside>
  );
};

SideBar.propTypes = {
  isOpen: PropTypes.any,
}

export default SideBar;
