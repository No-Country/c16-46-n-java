import { useReducer } from "react";
import { findAll,findPostByFilter, save, saveImgs } from "../services/postService";
import { postsReducer } from "../reducers/postsReducer";

const usePost = () => {
  const [allPost, dispatch] = useReducer(postsReducer, []);

  const getAllPosts = async () => {
    const result = await findAll();
    console.log("!!! post request result :", result.data.content);

    dispatch({
      type: "loadingPosts",
      payload: result.data.content,
    });
  };

/*   const getPostByUser = async () => {
    const response = await findAll()
  } */

  const getPostByFilter = async (filter) => {
    const result = await findPostByFilter(filter)

    console.log("found by filter: ", result.data.content)

    dispatch({
      type: 'loadingPosts',
      payload: result.data.content
    })
  }


  // first create images then save post data
  const handlerSaveImages = async (images, post, userId) => {
    
    let response 
    try{

      let postResult = await handlerCreatePost(post)

      console.log("post result: ", postResult)
  
      console.log("images to save: ", images)
  
      const imgData = new FormData()
      imgData.append('postId', postResult.data.id)
      imgData.append('userId', userId)
      imgData.append('multipartFile', images[0])


      response = await saveImgs(imgData)
    }catch(error){
      console.log("image error!!!", error.response)
    }


    console.log("save img status: ", response)
  }

  const handlerCreatePost = async (post) => {
    let response;

    try {

      if (!post.id) {
        response = await save(post);
      } else {
        response = await update(post);
      }

      dispatch({
        type: post.id === 0 ? "addPost" : "updatePost",
        payload: response.data.content,
      });


    } catch (error) {
      if (error.response) {
        console.log("Post error!!!  ", error.response.data);
        console.log("Post error!!!  ", error);
      }
    }

    return response

  };

  return {
    allPost,
    getAllPosts,
    getPostByFilter,
    handlerCreatePost,
    handlerSaveImages,
  };
};

export default usePost;
