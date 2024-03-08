import React, { useContext, useState, useEffect, useRef } from "react";
/* ICONS */
import { IoCloseCircleOutline } from "react-icons/io5";
import { TfiRulerAlt2 } from "react-icons/tfi";
import { IoBedOutline } from "react-icons/io5";
import { LiaBathSolid } from "react-icons/lia";
import { LiaFileContractSolid } from "react-icons/lia";
import { IoIosSend } from "react-icons/io";
import CardComment from "./cards/CardComment";
import { HomeContext } from "../../context/HomeContext";

const iconSize = "2rem";
const ModalPostDetail = ({ isOpen, onClose, post }) => {
  const { commentHookData, userHookData } = useContext(HomeContext);

  const [comment, setComment] = useState("");

  const commentInputRef = useRef(null);

  const hanldeCommentChange = () => setComment(commentInputRef.current.value);

  const onSaveComment = () => {
    console.log("comment: ", comment);
    let idUser =
      userHookData.loginStatus.isAuth && userHookData.loginStatus.user.id;
    commentHookData.createComment({
      postId: post && post.id,
      userId: idUser,
      content: comment,
    });
  };

  useEffect(() => {
    if (post && post.id) {
      commentHookData.getCommentsByPost(post && post.id);
    }

    console.log("all comments: ", commentHookData.allComments);
  }, []);
  return (
    <div
      className={`w-full h-full flex flex-col items-center justify-center fixed top-0 left-0 z-50 ${
        !isOpen && "collapse"
      }`}
    >
      <div className="w-5/6 h-full md:w-3/5 flex flex-col p-2">
        {/* CLOSE BUTTON */}
        <button
          className="bg-blue-300 absolute rounded-full bg-gray-200 z-50"
          onClick={() => onClose(false)}
        >
          <IoCloseCircleOutline style={{ fontSize: "1.5rem", zIndex: "0" }} />
        </button>

        {/* POST CONTENT */}

        <div className="w-full h-full flex flex-col mt-2 items-center bg-white rounded-lg overflow-y-auto">
          {post && post.images && (
            <img
              src={post.images[0].imageUrl}
              alt="post image"
              className="w-full rounded-t-lg"
            />
          )}

          <h6 className="text-lg font-bold w-full p-4 text-cyan-800">
            {post && post.name}
          </h6>

          <div className="w-full md:space-y-2 px-4">
            {/* POST DETAILS */}
            <div className="grid grid-cols-2 md:grid-cols-4 place-content-stretch text-gray-600 w-full border border-gray-400 rounded py-2 md:p-4">
              {/* AREA */}
              <div className="flex items-center">
                <TfiRulerAlt2
                  className="hidden md:block"
                  style={{ fontSize: iconSize }}
                />
                <h6 className="flex flex-col text-center w-full">
                  Area(m²)
                  <span className="text-lg font-bold">{post && post.area}</span>
                </h6>
              </div>

              {/* BEDROOMS */}
              <div className="flex items-center">
                <IoBedOutline
                  className="hidden md:block"
                  style={{ fontSize: iconSize }}
                />
                <h6 className="flex flex-col text-center w-full">
                  Hab.
                  <span className="text-lg font-bold">
                    {post && post.bedrooms}
                  </span>
                </h6>
              </div>

              {/* BATHROOMS */}
              <div className="flex items-center">
                <LiaBathSolid
                  className="hidden md:block"
                  style={{ fontSize: iconSize }}
                />
                <h6 className="flex flex-col text-center w-full">
                  Bath
                  <span className="text-lg font-bold">
                    {post && post.bathrooms}
                  </span>
                </h6>
              </div>

              {/* CONTRACT */}
              <div className="flex items-center">
                <LiaFileContractSolid
                  className="hidden md:block"
                  style={{ fontSize: iconSize }}
                />
                <h6 className="flex flex-col text-center w-full">
                  Pais
                  <span className="text-lg font-bold">
                    {post && post.country}
                  </span>
                </h6>
              </div>
            </div>

            {/* DESCRIPTION */}
            <p className="w-full text-sm md:text-lg text-justify pt-4">
              {post && post.description}
            </p>
          </div>
          {/* COMMENTS SECTION */}
          <h5 className="w-full text-lg font-bold px-4 mt-2 text-cyan-800">
            Comentarios
          </h5>
          <section className="w-full">
            {/* CHAT CONTENT */}
            <div className="grow bg-white w-full space-y-2 overflow-y-auto px-6">
              <CardComment />
              <CardComment />
              <CardComment />
            </div>

            {/* INPUT ADD COMMENT */}
            <div className="flex relative w-full items-center">
              <input
                type="text"
                value={comment}
                ref={commentInputRef}
                onChange={hanldeCommentChange}
                className="border p-4 w-full"
                placeholder="Agregar comentario"
              />

              <button
                onClick={onSaveComment}
                className="border p-2 absolute right-0 text-cyan-800 hover:text-white hover:bg-cyan-800 rounded shadow mr-2"
              >
                <IoIosSend />
              </button>
            </div>
          </section>
        </div>
      </div>
    </div>
  );
};

export default ModalPostDetail;
