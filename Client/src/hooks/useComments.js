import { useReducer, useState } from "react";
import { findPostComments, savecomment } from "../services/commentService";
import { commentsReducer } from "../reducers/commentsReducer";

const useComments = () => {
  const [allComments, dispatch] = useReducer(commentsReducer, []);

  const createComment = async (comment) => {
    let result;

    try {
      result = await savecomment(comment);
    } catch (error) {
      console.log("comment error: ", error);
    }
    console.log("comment status: ", result.status);

    dispatch({
        type: "addComment",
        payload: result.data,
      });
  };

  const getCommentsByPost = async (id) => {
    return await findPostComments(id);
  };

  return {
    allComments,
    getCommentsByPost,
    createComment,
  };
};

export default useComments;
