import { PiCalendarDuotone } from "react-icons/pi";
import { MdOutlineNotificationsActive } from "react-icons/md";
import { BiMessageRoundedDots } from "react-icons/bi";
import { BsPersonCircle } from "react-icons/bs";
import { CgMenuRound } from "react-icons/cg";
import { Link } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { HomeContext } from "../../context/HomeContext";
import logo from "../../assets/Logo.svg";
import { MdOutlineAddToPhotos } from "react-icons/md";
import { BsPostcardHeart } from "react-icons/bs";

const sizeFont = "1.5rem";

const navItems = [
  {
    /* icon: <BiMessageRoundedDots style={{ fontSize: sizeFont }} />, */
    icon: <span>Mensajes</span>,
    name: "messages",
  },
  {
    /* icon: <CgMenuRound style={{ fontSize: sizeFont }} />, */
    icon: <span>Cuenta</span>,
    name: "settings",
  },
];

const NavBar = () => {
  const { homeHookData, userHookData } = useContext(HomeContext);

  return (
    <>
      <nav
        className={`bg-white sticky top-0 justify-between right-0 w-full h-12vh flex p-3 pr-3 pl-5 z-40 shadow-lg`}
      >
        <section className="flex items-center">
          <a href="./" className="flex">
            <img className="size-12" src={logo} alt="tuvivienda logo" />
            {/* <h1 className="md:text-xl">
              <span className={`text-center text-blue-300 invisible md:visible top-5 pl-4`}>
                TUVIVIENDA.com
              </span>
            </h1> */}
          </a>
        </section>
        <section className="flex items-center rounded-full gap-4 justify-center">
          {userHookData.loginStatus.isAuth ? (
            navItems.map((item) => {
              return (
                <Link
                  key={item.name}
                  to={`/${item.name}`}
                  className=" text-blue-300 cursor-pointer"
                  name={item.name}
                  onClick={homeHookData.handlerOpenSidebar}
                >
                  <picture>{item.icon}</picture>
                </Link>
              );
            })
          ) : (
            <>
              <Link
                to={`/login`}
                className=" text-blue-300 cursor-pointer"
                name={"login"}
                onClick={homeHookData.handlerOpenSidebar}
              >
                {/* <picture>
                  <BsPersonCircle style={{ fontSize: sizeFont }} />
                </picture> */}
                <span>Iniciar</span>
              </Link>
            </>
          )}

          {userHookData.isAdmin && (
            <>
              <Link
                to={`/post`}
                className=" text-blue-300 cursor-pointer"
                name="post"
                onClick={homeHookData.handlerOpenSidebar}
              >
                {/* <picture>
                  <BsPostcardHeart style={{ fontSize: sizeFont }} />
                </picture> */}
                <span>Publicaciones</span>
              </Link>

              <Link
                to={`/publish`}
                className=" text-blue-300 cursor-pointer border p-2 rounded"
                name="publish"
                onClick={homeHookData.handlerOpenSidebar}
              >
                {/* <picture>
                  <MdOutlineAddToPhotos style={{ fontSize: sizeFont }} />
                </picture> */}
                <span>Crear</span>
              </Link>
            </>
          )}
        </section>
      </nav>
    </>
  );
};

export default NavBar;
// Burger menu

// SubMenu
// react
