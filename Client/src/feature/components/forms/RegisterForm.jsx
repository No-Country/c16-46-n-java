import { useContext, useEffect, useState } from "react";
import { IoCloseCircleOutline } from "react-icons/io5";
import { HomeContext } from "../../../context/HomeContext";

// INITIAL OBJECT VALUES
const initialRegisterData = {
  firstName: "",
  lastName: "",
  email: "",
  password: "",
  password2: "",
  country: "Argentina",
};

const initialErrors = {
  firstName: "",
  lastName: "",
  email: "",
  password: "",
  password2: "",
  country: "",
};

const RegisterForm = () => {
  // taken from context
  const { homeHookData, userHookData } = useContext(HomeContext);

  // all user fields
  const [registerForm, setRegisterForm] = useState(initialRegisterData);
  const { firstName, lastName, email, password, password2, country } =
    registerForm;

  const [errors, setErrors] = useState(initialErrors);

  const onInputChange = ({ target }) => {
    const { name, value } = target;

    setRegisterForm({
      ...registerForm,
      [name]: value,
    });
  };

  //    const [inputLocation, setInputLocation] = useState('');
  const onSubmit = (e) => {
    e.preventDefault();
    let error = {}


    if (firstName && lastName && email && password && password2) {
      if (password !== password2) {
        // sending error type to object
        setErrors(
          {...errors,
          password2 : "Las contraseñas no coinciden!"}
        )
        return;
      }

      userHookData.handlerRegisterUser(registerForm);
      // clear data
      setRegisterForm(initialRegisterData);
      // and close modal
      homeHookData.handlerRegisterClose();
    } else {
      // checking for empty inputs

      const emptyInputs = Object.keys(registerForm).filter(
        (val) => registerForm[val] === ""
      );

      console.log("empty inputs: ", emptyInputs);

      
      emptyInputs.map((val) => {
        error[val] = `El campo ${val} no puede ir vacio`
      });

    }
    setErrors(error)
    error = {}
  };

  return (
    <div
      className={`w-full h-full flex items-center justify-center fixed top-0 left-0 z-30 ${
        !homeHookData.isRegisterOpen && "collapse"
      }`}
    >
      <form
        onSubmit={onSubmit}
        className="w-3/4 lg:w-2/4 py-4 my-4 bg-white border border-neutral-400 shadow-lg shadow-neutral-400 flex flex-col
        lg:p-4 gap-4 bg-#fff rounded r-0 z-30 overflow-y-auto"
      >
        <IoCloseCircleOutline
          onClick={homeHookData.handlerRegisterClose}
          style={{ fontSize: "1.8rem", cursor: "pointer" }}
          className="mx-4"
        />

        <section className="flex flex-wrap lg:mb-2">
          <article className="w-full md:w-1/2 px-3 mb-6 md:mb-0">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-first-name"
            >
              Nombre
            </label>

            <input
              className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
                errors.firstName && "border border-red-500"
              } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
              id="grid-first-name"
              name="firstName"
              placeholder="Nombre..."
              type="text"
              value={firstName}
              onChange={onInputChange}
            />
            {errors.firstName && <p className="text-red-500 text-xs italic">
              {errors.firstName}
            </p>}
          </article>
          <article className="w-full md:w-1/2 px-3">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-last-name"
            >
              Apellido
            </label>
            <input
              className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
                errors.lastName && "border border-red-500"
              } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
              id="grid-last-name"
              name="lastName"
              placeholder="Apellido..."
              type="text"
              value={lastName}
              onChange={onInputChange}
            />
            {errors.lastName && <p className="text-red-500 text-xs italic">
              {errors.lastName}
            </p>}
          </article>
        </section>
        <section className="flex flex-wrap lg:mb-2">
          <article className="w-full px-3">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-email"
            >
              Email
            </label>
            <input
              className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
                errors.email && "border border-red-500"
              } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
              id="grid-email"
              name="email"
              placeholder="Email..."
              type="email"
              value={email}
              onChange={onInputChange}
            />
            {errors.email && (
              <p className="text-red-500 text-xs italic">{errors.email}</p>
            )}
          </article>
        </section>
        <section className="flex flex-wrap lg:mb-2">
          <article className="w-full md:w-1/2 px-3">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-password"
            >
              Contraseña
            </label>
            <input
              id="grid-password"
              name="password"
              className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
                errors.password && "border border-red-500"
              } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
              type="password"
              onChange={onInputChange}
              value={password}
              placeholder="******************"
            />
            {errors.password && (
              <p className="text-red-500 text-xs italic">{errors.password}</p>
            )}
          </article>

          <article className="w-full md:w-1/2 px-3">
            <label
              className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
              htmlFor="grid-password-2"
            >
              Comfirmar contraseña
            </label>
            <input
              id="grid-password-2"
              name="password2"
              className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
                errors.password && "border border-red-500"
              } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
              type="password"
              onChange={onInputChange}
              value={password2}
              placeholder="******************"
            />
            {errors.password2 && (
              <p className="text-red-500 text-xs italic">{errors.password2}</p>
            )}
          </article>
        </section>
        <section className="flex flex-wrap justify-between w-full">
          <article className="w-full md:w-1/3 px-3 mb-6 md:mb-0">
            <article className="relative ">
              <select
                name="country"
                value={country}
                onChange={onInputChange}
                className="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                id="grid-state"
              >
                <option>Argentina</option>
                <option>Colombia</option>
                <option>Peru</option>
              </select>
              <article className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                <svg
                  className="fill-current h-4 w-4"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z" />
                </svg>
              </article>
            </article>
          </article>
          <article className="lg:right-0 mx-4">
            <button className="p-3 bg-sky-500 text-white rounded md:h-12">
              Registrarse
            </button>
          </article>
        </section>
      </form>
    </div>
  );
};

export default RegisterForm;

/**
 <form 
           onSubmit={onSubmit}
           className="fixed self-end h-screen top-10 right-[40%] flex flex-col gap-4 bg-stone-200 rounded r-0 top-12vh p-4 z-70">
              <section>
                  <label htmlFor="name"></label>
                  <input 
                  name="name"
                  placeholder="Name..."
                  className="p-4 rounded"
                  type="text" 
                  value={inputName}
                  onChange={handleName}
                  />
              </section>
              <section>
                  <label htmlFor="email"></label>
                  <input 
                  name="email"
                  placeholder="Email..."
                  className="p-4 rounded"
                  type="text" 
                  value={inputEmail}
                  onChange={handleEmail}
                  />
              </section>
              <section>
                  <label htmlFor="location"></label>
                  <input 
                  name="location"
                  placeholder="City..."
                  className="p-4 rounded"
                  type="text" 
                  value={inputLocation}
                  onChange={handleLocation}
                  />
              </section>
              <button
              className="p-4 border-none bg-cyan-800 self-center decoration-white text-white"
              > Submit 
              </button>
           </form>

 */
