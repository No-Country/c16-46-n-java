export const commentsReducer = (state = [], action) => {
    switch (action.type) {
      // each case represent an action
      case "addComment":
        return [
          ...state,
          {
            // add a new user
            ...action.payload,
          },
        ];

      default:
        return state;
    }
  };