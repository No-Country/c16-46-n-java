import { useReducer, useState } from "react";
import {
  findAdminPost,
  findAll,
  findPostByFilter,
  save,
  saveImgs,
} from "../services/postService";
import { postsReducer } from "../reducers/postsReducer";
import { toast } from 'react-toastify';

const usePost = () => {
  const [allPost, dispatch] = useReducer(postsReducer, []);
  const [adminPost, setAdminPost] = useState([])

  const getAllPosts = async () => {
    const result = await findAll();

    toast.info("Resultado de la busqueda: " + result.data.content);
    dispatch({
      type: "loadingPosts",
      payload: result.data.content,
    });
  };

    const getPostByUser = async (id) => {
       const response = await findAdminPost(id)

    setAdminPost(response.data.content)
    }

    const getPostByFilter = async (filter) => {
     const result = await findPostByFilter(filter);
    //The filter options it's now working so i didn't use tostify for this one

    console.log("found by filter: ", result.data.content)

    dispatch({
      type: 'loadingPosts',
      payload: result.data.content
    })
  };

  // first create images then save post data
  const handlerSaveImages = async (images, post, userId) => {
    let response;
    try {
      let postResult = await handlerCreatePost(post);

      toast.success("¡Imagen guardada correctamente!");
      toast.info("Resultado del post: " + postResult);
      toast.info("Imágenes para guardar: " + images);
  
      const imgData = new FormData()
      imgData.append('postId', postResult.data.id)
      imgData.append('userId', userId)
      imgData.append('multipartFile', images[0])

    

      let multipartImgs = []
      images.map((img) => {
        const imgData = new FormData();
        imgData.append("postId", postResult.data.id);
        imgData.append("userId", userId);
        imgData.append("multipartFile", img);
        multipartImgs.push(imgData)
      });
     
      toast.info("multipart image: " + multipartImgs);

      response = await saveImgs(imgData)
      toast.info("Estatus de carga de imagen: " + response);

    }catch(error){
      toast.error("Imagen error: " + error.response);
    }


    toast.error("Error al guardar las imágenes.");
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

        toast.error("Ha ocurrido un error con el post: " + error.response.data);
        toast.error("Ha ocurrido un error con el post: " + error);
      }
    }

    return response;
  };

  return {
    allPost,
    adminPost,
    getAllPosts,
    getPostByFilter,
    getPostByUser,
    handlerCreatePost,
    handlerSaveImages,
  };
};

export default usePost;
