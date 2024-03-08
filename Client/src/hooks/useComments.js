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
  };

  const getCommentsByPost = async (id) => {
    const response = await findPostComments(id);

    dispatch({
      type: "addComment",
      payload: response.data.content,
    });
  };

  return {
    allComments,
    getCommentsByPost,
    createComment,
  };
};

export default useComments;
