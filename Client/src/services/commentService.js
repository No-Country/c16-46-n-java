import axios from "axios"
const DB_URL = "http://localhost:8080/comment";


export const savecomment = (comment) => {
    try{
        /* {
            postId,
            userId,
            content
        } */
        return axios.post(`${DB_URL}/add`, comment)
    }catch(e){
        throw e
    }
}

export const findPostComments = (idPost) => {
    try{
        return axios.get(`^${DB_URL}/getAll/${idPost}`)
    }catch(e){
        throw e
    }
}