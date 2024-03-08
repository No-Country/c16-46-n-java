import React, { useContext } from "react";
import { HomeContext } from "../../context/HomeContext";
import { IoCloseCircleOutline } from "react-icons/io5";
import Chat from "./Chat";

const ModalChat = () => {
  const { homeHookData } = useContext(HomeContext);

  return (
    <div
      className={`w-full h-full flex flex-col items-center justify-center fixed top-0 left-0 z-50 ${
        !homeHookData.isModalOpen && "collapse"
      }`}
    >
      <div className="w-5/6 h-full md:w-3/5 flex flex-col p-4">
        {/* CLOSE BUTTON */}
        <button
          className="bg-blue-300 absolute rounded-full bg-gray-200 z-50"
          onClick={homeHookData.handlerModalClose}
        >
          <IoCloseCircleOutline style={{ fontSize: "1.5rem", zIndex: "0" }} />
        </button>

        <Chat />
      </div>
    </div>
  );
};

export default ModalChat;
