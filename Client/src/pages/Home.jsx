import CardItem from "../feature/components/cards/CardItem";
import SearchBar from "../feature/components/SearchBar";
import { useContext, useEffect } from "react";
import usePost from "../hooks/usePost";
import { HomeContext } from "../context/HomeContext";

const Home = () => {
  const { postHookData } = useContext(HomeContext);

  useEffect(() => {
    postHookData.getAllPosts();
  }, []);

  return (
    <>
      <div className="w-full">
        <SearchBar />

        {/* HOME POST CONTENT */}
        <div className="w-full">
          {/* NOT POST FOUND */}
          {postHookData.allPost.length === 0 && (
            <div className="w-full text-xl italic rounded-md mt-4 p-4 shadow-lg bg-yellow-200 text-orange-800">
              Ops! No hay publicaciones...
            </div>
          )}

          {/* POST FOUND */}
          <div className="grid lg:grid-cols-4 md:grid-cols-3 grid-cols-2">
            {postHookData.allPost &&
              postHookData.allPost.map((post) => {
                return <CardItem key={post.id} post={post} />;
              })}
          </div>
        </div>
      </div>
    </>
  );
};
export default Home;
