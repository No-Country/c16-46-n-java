//import React from "react";
/* LIKE */
import { BiLike } from "react-icons/bi";
//import { BiSolidLike } from "react-icons/bi";
/* STARS */
import { CiStar } from "react-icons/ci";
//import { FaStar } from "react-icons/fa";

const CardItem = ({ post, onClickView }) => {
  return (

    <div 
    onClick={() => onClickView(post)}
    className="bg-white relative rounded hover:shadow-lg
    overflow-hidden m-2 border border-gray-300 flex flex-col">
      
        <div className="h-1/3 border-b-4 border-sky-700">
          {/* IMAGE RESOURCE */}
          <img
            className="w-full h-full"
            src={post.images[0] && post.images[0].imageUrl}
            alt="post image"
          />
          {/* LIKE BUTTON */}
          <BiLike
            onClick={() => console.log("Property liked!")}
            style={{ color: "#ffffff", fontSize: "1.5rem" }}
            className="absolute top-0 right-0 m-2"
          />
          {/* STARS */}
          <CiStar
            onClick={() => console.log("Property stars!")}
            style={{ color: "#ffffff", fontSize: "1.5rem" }}
            className="absolute top-0 left-0 m-2"
          />
        </div>

        {/* CARD BODY */}
        <div className="px-4 flex flex-col py-4 space-y-2 justify-between h-full grow">
          <h4 className="font-bold text-md">{post.name}</h4>

          <div>
            <h5>Precio de propiedad</h5>
            <p className="font-bold">${post.price}</p>
          </div>

          {/* AREA AND ROOMS DATA */}
          <ul className="md:grid md:grid-cols-3">
            <li className="flex md:flex-col">
              <span>Area(m²)</span>
              <p className="font-bold w-full text-right md:text-left">{post.area}</p>
            </li>

            <li className="flex md:flex-col">
              <span>Hab.</span>
              <p className="font-bold w-full text-right md:text-left">{post.bedrooms}</p>
            </li>

            <li className="flex md:flex-col">
              <span>Baños</span>
              <p className="font-bold w-full text-right md:text-left">{post.bathrooms}</p>
            </li>
          </ul>

          {/* CONTACT */}
          <button
            className="inline-block border border-cyan-700 rounded-lg
        px-1 py-1 text-sm font-semibold text-cyan-700 text-center"
          >
            Contactar
          </button>
        </div>
      
    </div>
  );
};


export default CardItem;
