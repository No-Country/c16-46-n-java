import React from "react";

const CardComment = ({comment}) => {
  return (
    <div className="rounded w-3/4 border-2 p-2 flex flex-col">
      <span className="font-bold">{comment.name}</span>
      {comment.content}
    </div>
  );
};

export default CardComment;
