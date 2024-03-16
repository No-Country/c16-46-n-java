import React, { useContext } from "react";
import { IoCloseCircleOutline } from "react-icons/io5";
import LoginForm from "./forms/LoginForm";
import { Route, Routes } from "react-router-dom";

import PropTypes from "prop-types";
import Notifications from "./menu/Notifications";
import Messages from "./menu/Messages";
import Settings from "./menu/Settings";
import { HomeContext } from "../../context/HomeContext";
import PropertyForm from "./forms/PropertyForm";
import RegisterForm from "./forms/RegisterForm";
import MyPost from "./menu/MyPost";

const SideBar = () => {
  const { homeHookData } = useContext(HomeContext);
  //Line 21: change from h-screen to min-h-screen in order to avoid overflow in Y
  return (
    <aside
      className={`fixed bg-blue-300 rounded z-50 bg-white right-0 z-40 md:w-2/5 lg:w-1/4 w-10/12 h-full shadow-lg duration-500 p-4 transition-transform ${
        homeHookData.sidebarVisible ? "" : "translate-x-full"
      }`}
    >
      {/* CLOSE BUTTON */}
      <button
        className="bg-blue-300 absolute top-2 rounded-full bg-gray-200 z-10"
        onClick={homeHookData.handlerCloseSideBar}
      >
        <IoCloseCircleOutline style={{ fontSize: "1.5rem", zIndex: "0" }} />
      </button>

      <div className="w-full h-full mt-4 p-2 overflow-y-auto overflow-x-hidden">
        {/* ADD CONTENT / FORM HERE */}
        <Routes>
          <Route path="/login" element={<LoginForm />} />
          <Route path="/register" element={<RegisterForm />} />
          <Route path="/post" element={<MyPost />} />
          <Route path="/notifications" element={<Notifications />} />
          <Route path="/messages" element={<Messages />} />
          <Route path="/settings" element={<Settings />} />
          <Route path="/publish" element={<PropertyForm />} />
        </Routes>
      </div>
    </aside>
  );
};

SideBar.propTypes = {
  isOpen: PropTypes.any,
};

export default SideBar;
