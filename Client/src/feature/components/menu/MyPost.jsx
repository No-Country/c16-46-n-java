import { useContext, useEffect, useState } from "react";
import CardMyPost from "../cards/CardMyPost";
import { HomeContext } from "../../../context/HomeContext";

const MyPost = () => {
  const { userHookData } = useContext(HomeContext);

  const [myPost, setMyPost] = useState(userHookData.loginStatus.user.administrator.posts ?? []) 

  useEffect(() => {
    console.log("my post ", myPost)
  }, [])
  return (
    <div className="flex flex-col justify-center">
      <h3 className="absolute w-full text-center top-3 text-gray-700 font-bold">
        Mis Publicaciones
      </h3>
      <div className="w-full h-full">
        {myPost && myPost.map((post) => {
            return <CardMyPost key={post.id} post={post}/>
        })}
      </div>
    </div>
  );
};

export default MyPost;
