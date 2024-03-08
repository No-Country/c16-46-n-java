export const postsReducer = (state = [], action) => {
    switch (action.type) {
      // each case represent an action
      case "addPost":
        return [
          ...state,
          {
            // add a new user
            ...action.payload,
          },
        ];
  
      /* case "updatePost":
        return state.map((u) => {
          if (u.id === action.payload.id) {
            return {
              ...action.payload
            };
          }
          return u;
        }); */
  
      case "removePost":
        // return a new post without selected post
        return state.filter((post) => post.id !== action.payload);
  
      case "loadingPosts":
        return action.payload
  
      default:
        return state;
    }
  };