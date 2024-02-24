import axios from "axios";
// import { notifyError, notifySuccess } from "../utils/notifications";

const DB_URL = "http://localhost:8080/user";

/* export const findAll = async () => {
  try {
    return await axios.get(DB_URL);
  } catch (e) {
    console.error("Ops... we can't get users!");
  }

  return null;
}; */

export const login = async ({ email, password }) => {
  console.log("email from axios: ", email)
  try {
    return await axios.post(`${DB_URL}/login`, {
      email,
      password,
    });
  } catch (e) {
    throw e;
  }
};

export const save = async (user) => {
  try {
    // must return an object

    return await axios.post(`${DB_URL}/register`, {
      name: `${user.firstName} ${user.lastName}`,
      email: user.email,
      password: user.password,
      password2: user.password2,
      country: user.country,
    });
  } catch (e) {
    throw e;
  }
};

export const update = async ({ username, email, id }) => {
  try {
    return await axios.put(`${DB_URL}/${id}`, {
      username,
      email,
    });
  } catch (e) {
    throw e;
  }
};

export const remove = async (id) => {
  try {
    // notifySuccess("User deleted");
    console.log("User deleted");
    await axios.delete(`${DB_URL}/${id}`);
  } catch (e) {
    throw e;
  }
  return undefined;
};
