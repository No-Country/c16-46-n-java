export const usersReducer = (state = [], action) => {
    switch (action.type) {
      // each case represent an action
      case "addUser":
        return [
          ...state,
          {
            // add a new user
            ...action.payload,
          },
        ];
  
      case "updateUser":
        return state.map((u) => {
          if (u.id === action.payload.id) {
            return {
              ...action.payload
            };
          }
          return u;
        });
  
      case "removeUser":
        // retorn a new user without selected user
        return state.filter((user) => user.id !== action.payload);
  
      case "loadingUsers":
        return action.payload
  
      default:
        return state;
    }
  };