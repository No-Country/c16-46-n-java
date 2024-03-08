import { useContext, useEffect, useState } from "react";
import CardMyPost from "../cards/CardMyPost";
import { HomeContext } from "../../../context/HomeContext";
import { RiFileSearchLine } from "react-icons/ri";

const MyPost = () => {
  const { userHookData, postHookData } = useContext(HomeContext);

  useEffect(() => {
    postHookData.getPostByUser(userHookData.loginStatus.user.administrator.id);
    console.log("my post ", postHookData.adminPost);
  }, []);
  return (
    <div className="flex h-full flex-col justify-center">
      <h3 className="absolute w-full text-center top-3 text-gray-700 font-bold">
        Mis Publicaciones
      </h3>
      <div className="w-full h-full">
        {postHookData.adminPost ? (
          postHookData.adminPost.map((post) => {
            return <CardMyPost key={post.id} post={post} />;
          })
        ) : (
          <div className="w-full h-full flex flex-col items-center justify-center">
            <picture className="h-1/3">
              <RiFileSearchLine className="w-full" style={{ fontSize: "4rem" }} />
              <h4>No hay publicaciones</h4>
            </picture>
          </div>
        )}
      </div>
    </div>
  );
};

export default MyPost;
