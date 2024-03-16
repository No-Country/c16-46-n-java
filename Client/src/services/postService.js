import axios from "axios";

const DB_URL = "http://localhost:8080";

export const findAll = async () => {
  try {
    return await axios.get(`${DB_URL}/post/getall`);
  } catch (e) {
    console.error("Ops... we can't get post!");
  }

  return null;
};

export const findPostByFilter = async (filter) => {
  try {
    return await axios.get(`${DB_URL}/post/searchByFilter`, filter);
  } catch (e) {
    console.error("Ops... we can't get post!");
  }

  return null;
};

export const saveImgs = async (images) => {
  try {
    return await axios.post(`${DB_URL}/cloudinary/upload_postImages`, images, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  } catch (e) {
    throw e;
  }
};

export const save = async (post) => {
  try {
    // must return an object
    return await axios.post(`${DB_URL}/post/create`, post);
  } catch (e) {
    throw e;
  }
};

export const update = async (newPost, id) => {
  try {
    return await axios.put(`${DB_URL}/post/updateFields/${id}`, newPost);
  } catch (e) {
    throw e;
  }
};

export const remove = async (id) => {
  try {
    await axios.delete(`${DB_URL}/post/delete/${id}`);
  } catch (e) {
    throw e;
  }
  return undefined;
};


export const findAdminPost = async(id) => {

  console.log("admin id for request: ", id)
  try{
    return await axios.get(`${DB_URL}/post/getAllByAdm/${id}`)
  }catch(e){
    throw e
  }

}
