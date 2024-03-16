import CardItem from "../feature/components/cards/CardItem";
import SearchBar from "../feature/components/SearchBar";
import { useContext, useEffect, useState } from "react";
import { HomeContext } from "../context/HomeContext";
import ModalPostDetail from "../feature/components/ModalDetail";

const Home = () => {
  const { postHookData } = useContext(HomeContext);
  const [isModalOpen, setModalOpen] = useState(false);
  const [postSelected, setPostSelcted] = useState({});

  const handleSeeDetail = (post) => {
    console.log("item selected: ", post);
    // open modal
    setModalOpen(true);
    // change post state
    setPostSelcted(post);
  };

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
          <div className="grid lg:grid-cols-5 md:grid-cols-4 grid-cols-2">
            {postHookData.allPost &&
              postHookData.allPost.map((post) => {
                return (
                  <CardItem
                    key={post.id}
                    post={post}
                    onClickView={handleSeeDetail}
                  />
                );
              })}
          </div>
        </div>
      </div>

      <ModalPostDetail
        isOpen={isModalOpen}
        onClose={(state) => setModalOpen(state)}
        post={postSelected}
      />
    </>
  );
};
export default Home;
