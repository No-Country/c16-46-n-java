import React from "react";
import { AiOutlineWechat } from "react-icons/ai";

const CardMyPost = ({ post }) => {
  return (
    <>

      {/* BODY CONTENT */}
      <div className="w-full mt-4 flex text-left border shadow-lg rounded bg-white">
        <img
          className="w-1/3 object-cover"
          src={post.image[0] && post.image[0].imageUrl}
          alt=""
        />

        {/* DATA CONTENT */}
        <section className="grow relative ml-2 mr-2">
          <h5 className="w-full text-center font-bold">{post.name}</h5>

          <table className="w-full">
            <tbody>
              <tr>
                <td className="font-bold">Precio</td>
                <td className="text-right">{post.price}</td>
              </tr>

              <tr>
                <td className="font-bold">Estado</td>
                <td className="text-right">
                  {post.state ? "Activa" : "Pausada"}
                </td>
              </tr>
            </tbody>
          </table>
        </section>
        {/* NEW MESSAGE */}
        <span className="w-2 bg-sky-800" />
      </div>
    </>
  );
};

export default CardMyPost;
