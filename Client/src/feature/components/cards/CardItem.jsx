//import React from "react";
/* LIKE */
import { BiLike } from "react-icons/bi";
//import { BiSolidLike } from "react-icons/bi";
/* STARS */
import { CiStar } from "react-icons/ci";
//import { FaStar } from "react-icons/fa";

const CardItem = () => {
  return (
    <div className="bg-blue-700 lg:w-1/5 relative w-1/2 md:w-1/3 rounded overflow-hidden m-2">
     <a href="./id" >
      <div >
        {/* IMAGE RESOURCE */}
        <img
          className="w-full"
          src="https://umbral.co/wp-content/uploads/2021/11/DSC9262-P2.png"
          alt="Sunset in the mountains"
        />
        {/* LIKE BUTTON */}
        <BiLike
          style={{ color: "#ffffff", fontSize: "1.5rem" }}
          className="absolute top-0 right-0 m-2"
        />

        {/* STARS */}
        <CiStar
          style={{ color: "#ffffff", fontSize: "1.5rem" }}
          className="absolute top-0 left-0 m-2"
        />
      </div>

      <div className="px-4 flex flex-col py-4 space-y-2">
        <h4 className="font-bold text-md">Nombre de la propiedad</h4>
        <p className="text-gray-700 text-base">Dir 34 #12-54</p>

        {/* MOST COMMON POST */}
        <span
          className="inline-block bg-gray-200 rounded-full 
        px-1 py-1 text-sm font-semibold text-gray-700 text-center"
        >
          Publicaciones más vistas.
        </span>
      </div>
      </a>
    </div>
  );
};

export default CardItem;
