import React, { useContext, useEffect, useState } from "react";
import { MdAddAPhoto } from "react-icons/md";
import usePost from "../../../hooks/usePost";
import { HomeContext } from "../../../context/HomeContext";
import DragDropImage from "../DragDropImage";

const initPropertyData = {
  adminId: 0,
  name: "",
  description: "",
  type: "Casa",
  country: "Colombia",
  bedrooms: 0,
  contractType: "Venta",
  bathrooms: 0,
  address: "",
  price: 0,
  state: true,
  area: 0,
  age: 0,
};

const PropertyForm = () => {
  // hook functions
  const { postHookData, userHookData } = useContext(HomeContext);

  // fields property
  const [postImages, setPostImages] = useState([]);
  const [propertyForm, setPropertyForm] = useState(initPropertyData);
  const {
    name,
    description,
    type,
    bedrooms,
    bathrooms,
    country,
    contract_type,
    address,
    price,
    area,
    age,
  } = propertyForm;

  // error validation
  const [errors, setErrors] = useState({});

  const isValidated = () => {
    const error = {};
    const emptyInputs = Object.keys(propertyForm).filter(
      (val) => propertyForm[val] === "" || propertyForm[val] === 0
    );

    console.log("empty property inputs: ", emptyInputs);

    emptyInputs.map((val) => {
      error[val] = `El campo ${val} no puede ir vacio`;
    });

    setErrors(error);

    if (emptyInputs.length > 0) return false;

    return true;
  };

  const onSubmit = (e) => {
    e.preventDefault();

    if (userHookData.loginStatus.isAuth) {
      let adminId = userHookData.loginStatus.user.administrator.id
      setPropertyForm({
        ...propertyForm,
        adminId,
      });
      if (isValidated()) {
        console.log("property to create: ", propertyForm);
        // postHookData.handlerCreatePost(propertyForm)
        postHookData.handlerSaveImages(postImages, propertyForm, adminId);
      }
    }else console.log("admin id not found!")
  };

  const onInputChange = ({ target }) => {
    const { name, value } = target;
    setPropertyForm({
      ...propertyForm,
      [name]: value,
    });
  };

  const handlerImageChange = (images) => {
    console.log("images from drag drop: ", images);
    setPostImages(images);
  };

  return (
    <form onSubmit={onSubmit} className="bg-white flex flex-col">
      {/* IMAGE */}
      <DragDropImage handleImages={(imgs) => handlerImageChange(imgs)} />

      {/* PROPERTY NAME */}
      <section className="lg:mb-2 mt-4">
        <article className="w-full mb-6 md:mb-0">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-first-name"
          >
            Nombre
          </label>

          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.name && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-first-name"
            name="name"
            placeholder="Ej: Barrio, Edificio"
            type="text"
            value={name}
            onChange={onInputChange}
          />
          {errors.name && (
            <p className="text-red-500 text-xs italic">{errors.name}</p>
          )}
        </article>

        {/* DESCRIPTION */}
        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-description"
          >
            Descripción
          </label>
          <textarea
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.description && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-description"
            name="description"
            placeholder="Agrega comentarios"
            type="text"
            value={description}
            onChange={onInputChange}
          />
          {errors.description && (
            <p className="text-red-500 text-xs italic">{errors.description}</p>
          )}
        </article>

        {/* COST */}
        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-price"
          >
            Precio
          </label>
          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.price && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-price"
            name="price"
            placeholder="Precio"
            type="number"
            value={price}
            onChange={onInputChange}
          />
          {errors.price && (
            <p className="text-red-500 text-xs italic">{errors.price}</p>
          )}
        </article>

        {/* ADDRESS */}
        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-address"
          >
            Dirección
          </label>
          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.address && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-address"
            name="address"
            placeholder="Direccion"
            type="text"
            value={address}
            onChange={onInputChange}
          />
          {errors.address && (
            <p className="text-red-500 text-xs italic">{errors.address}</p>
          )}
        </article>
      </section>

      {/* ROOMS */}
      <section className="flex space-x-2">
        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-bedrooms"
          >
            Habitaciones
          </label>
          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.bedrooms && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-bedrooms"
            name="bedrooms"
            placeholder="0"
            type="number"
            value={bedrooms}
            onChange={onInputChange}
          />
          {errors.bedrooms && (
            <p className="text-red-500 text-xs italic">{errors.bedrooms}</p>
          )}
        </article>

        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-bathrooms"
          >
            Baños
          </label>
          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.bathrooms && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-bathrooms"
            name="bathrooms"
            placeholder="0"
            type="number"
            value={bathrooms}
            onChange={onInputChange}
          />
          {errors.bathrooms && (
            <p className="text-red-500 text-xs italic">{errors.bathrooms}</p>
          )}
        </article>
      </section>

      {/* AREA & AGE */}
      <section className="flex space-x-2">
        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-area"
          >
            Area(m²)
          </label>
          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.area && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-area"
            name="area"
            placeholder="0"
            type="number"
            value={area}
            onChange={onInputChange}
          />
          {errors.area && (
            <p className="text-red-500 text-xs italic">{errors.area}</p>
          )}
        </article>

        <article className="w-full">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-age"
          >
            Antigüedad
          </label>
          <input
            className={`appearance-none block w-full bg-gray-200 text-gray-700 ${
              errors.age && "border border-red-500"
            } rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white`}
            id="grid-age"
            name="age"
            placeholder="0"
            type="number"
            value={age}
            onChange={onInputChange}
          />
          {errors.bathrooms && (
            <p className="text-red-500 text-xs italic">{errors.age}</p>
          )}
        </article>
      </section>

      {/* PROPERTY CONTRACT & TYPE */}
      <section className="flex space-x-2">
        <article className="w-full mb-6 md:mb-0">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-contract-type"
          >
            Publicar
          </label>
          <article className="relative ">
            <select
              id="grid-contract-type"
              name="contract_type"
              value={contract_type}
              onChange={onInputChange}
              className="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
            >
              <option>Venta</option>
              <option>Renta</option>
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

        <article className="w-full mb-6 md:mb-0">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-type"
          >
            Tipo
          </label>
          <article className="relative ">
            <select
              id="grid-type"
              name="type"
              value={type}
              onChange={onInputChange}
              className="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
            >
              <option>Casa</option>
              <option>Apartamento</option>
              <option>Oficina</option>
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
      </section>

      {/* COUNTRY */}
      <section className="w-full mt-2">
        <article className="w-full mb-6 md:mb-0">
          <label
            className="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
            htmlFor="grid-country"
          >
            Pais
          </label>
          <article className="relative ">
            <select
              id="grid-country"
              name="country"
              value={country}
              onChange={onInputChange}
              className="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
            >
              <option>Colombia</option>
              <option>Argentina</option>
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
      </section>

      {/* CREATE PROPERTY */}
      <section className="w-full mt-2">
        <article className="lg:right-0">
          <button
            type="submit"
            className="w-full p-3 bg-sky-500 text-white rounded md:h-12"
          >
            Crear
          </button>
        </article>
      </section>
    </form>
  );
};

export default PropertyForm;
