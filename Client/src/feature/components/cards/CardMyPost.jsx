import React from "react";

const CardMyPost = ({post}) => {
  return (
    <div className="w-full flex border shadow-lg rounded m-2">

        <img
          className="w-1/3 h-full object-cover"
          src={post.image[0].imageUrl}
          alt=""
        />

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
              <td className="text-right">{post.state ? "Activa": "Pausada"}</td>
            </tr>
     
          </tbody>
        </table>
      </section>
    </div>
  );
};

export default CardMyPost;
