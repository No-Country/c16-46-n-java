import { useContext, useState } from "react";
import RegisterForm from "./RegisterForm";
import { HomeContext } from "../../../context/HomeContext";

const initialLoginForm = {
  email: "",
  password: "",
};

const LoginForm = () => {
  const { handlerRegisterOpen, handlerLoginUser } = useContext(HomeContext);
  const [loginForm, setLoginForm] = useState(initialLoginForm);
  const { email, password } = loginForm;

  const onInputChange = ({ target }) => {
    const { name, value } = target;

    setLoginForm({
      ...loginForm,
      [name]: value,
    });
  };

  const onSubmit = (e) => {
    e.preventDefault();

    console.log("user data: ", loginForm)

    if (!email || !password) console.log("Los campos no deben ir vacios!");
    else handlerLoginUser(loginForm);
  };

  return (
    <div>
      <h3 className="absolute  w-full text-center top-3 text-gray-700 font-bold">
        Login
      </h3>
      <form
        onSubmit={onSubmit}
        className=" bg-blue-300 bg-white w-full h-full p-2"
      >
        <div className="mb-4">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="email"
          >
            Correo
          </label>
          <input
            className={`shadow appearance-none border ${
              !email ? "border-red-500" : ""
            } rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline`}
            name="email"
            type="text"
            value={email}
            placeholder="tucorreo@ejemplo.com"
            onChange={onInputChange}
          />
        </div>
        <div className="mb-6">
          <label
            className="block text-gray-700 text-sm font-bold mb-2"
            htmlFor="password"
          >
            Contraseña
          </label>
          <input
            className={`shadow appearance-none border ${
              !password && "border-red-500"
            } rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline`}
            name="password"
            type="password"
            value={password}
            placeholder="*************"
            onChange={onInputChange}
          />
          <p className="text-red-500 text-xs italic">Escribe una contraseña.</p>
        </div>
        <div className="flex flex-col items-center justify-between gap-4">
          <button
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit"
          >
            Ingresar
          </button>
          <a
            className="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
            href="#"
          >
            Olvidé mi contraseña.
          </a>
        </div>

        <p className="m-2 text-sm w-full text-center">
          {"No tengo una cuenta, "}
          <a
            className="text-sky-500 hover:text-#fff"
            href="#"
            onClick={handlerRegisterOpen}
          >
            Registrarme.
          </a>
        </p>
      </form>

      <RegisterForm />
    </div>
  );
};

export default LoginForm;
