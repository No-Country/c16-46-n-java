import React, { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { HomeContext } from "../../context/HomeContext";

const initialFilter = [
  {
    columnName: "bedrooms",
    columnValue: 6
  }
];
const SearchBar = () => {
  const { homeHookData, postHookData } = useContext(HomeContext);

  const [filters, setFilters] = useState(initialFilter);

  const onSearch = (e) => {
    e.preventDefault();
    /* setFilters(...filters, filters.push({
      columnName : "type",
      columnValue: "Casa"
    })) */
    console.log("filters: ", filters)
    postHookData.getPostByFilter(filters);
  };

  return (
    <div className="w-full sticky md:flex bg-gray-100 py-4 justify-center items-center md:space-x-4 px-2 space-y-2 md:space-y-0">
      <form
        onSubmit={onSearch}
        className="w-auto md:grid md:grid-cols-5 justify-center items-center md:space-x-2 space-y-2 md:space-y-0"
      >
        {/* SEARCH BY TYPE */}
        <article className="w-auto md:mb-0">
          <article className="flex relative">
            <select
              name="country"
              className="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-state"
            >
              <option>Casas</option>
              <option>Apartamentos</option>
              <option>Oficinas</option>
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
        {/* SEARCH BY STATUS */}
        <article className="w-auto md:mb-0">
          <article className="flex relative">
            <select
              name="country"
              className="block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
              id="grid-state"
            >
              <option>Renta</option>
              <option>Venta</option>
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

        {/* SEARCH BAR */}
        <label
          htmlFor="default-search"
          className="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white"
        >
          Search
        </label>
        <div className="relative w-full col-span-3">
          <div className="absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
            <svg
              className="w-4 h-4 text-gray-500 dark:text-gray-400"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 20 20"
            >
              <path
                stroke="currentColor"
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"
              />
            </svg>
          </div>
          <input
            type="search"
            id="default-search"
            className="block w-full p-4 ps-10 text-sm text-gray-900 border border-gray-300 rounded-lg bg-gray-50 focus:ring-blue-500 focus:border-blue-500"
            placeholder="Buscar contenido..."
          />
          <button
            type="submit"
            className="text-white absolute end-2.5 bottom-2.5 bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2"
          >
            Buscar
          </button>
        </div>
      </form>
    </div>
  );
};

export default SearchBar;
