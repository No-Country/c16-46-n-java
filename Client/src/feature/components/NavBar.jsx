import { PiCalendarDuotone } from "react-icons/pi";
import { MdOutlineNotificationsActive } from "react-icons/md";
import { BiMessageRoundedDots } from "react-icons/bi";
import { BsPersonCircle } from "react-icons/bs";
import { CgMenuRound } from "react-icons/cg";
import { Link } from "react-router-dom";
import { useContext } from "react";
import { HomeContext } from "../../context/HomeContext";
import  logo  from "../../assets/Logo.svg"

const sizeFont = "1.5rem";

const navItems = [
  {
    icon: <PiCalendarDuotone style={{ fontSize: sizeFont }} />,
    name: "calendar",
  },
  {
    icon: <MdOutlineNotificationsActive style={{ fontSize: sizeFont }} />,
    name: "notifications",
  },
  {
    icon: <BiMessageRoundedDots style={{ fontSize: sizeFont }} />,
    name: "messages",
  },
  {
    icon: <CgMenuRound style={{ fontSize: sizeFont }} />,
    name: "settings",
  },
  {
    icon: <BsPersonCircle style={{ fontSize: sizeFont }} />,
    name: "login",
  },
];

const NavBar = () => {
  const { handlerOpenSidebar } = useContext(HomeContext);

  return (
    <>
      <nav
        className={`bg-blue-700 absolute top-0 justify-between right-0 w-full h-12vh flex p-3 pr-3 pl-5 z-60`}
      >
        <section className="flex items-center">
          <a href="./" className="flex">
          <img className="size-12 flex text-blue-300" src={logo} alt="Your SVG" />
            <h1 className="md:text-xl">
              <span className={`text-center text-blue-300 invisible md:visible top-5 pl-4`}>
                TUVIVIENDA.com
              </span>
            </h1>
          </a>
        </section>
        <section className="flex items-center rounded-full gap-4 justify-center">
          {navItems.map((item) => {
            return (
              <Link
              key={item.name}
                to={`/${item.name}`}
                className=" text-blue-300 cursor-pointer"
                name={item.name}
                onClick={handlerOpenSidebar}
              >
                <picture>{item.icon}</picture>
              </Link>
            );
          })}
        </section>
      </nav>
    </>
  );
};

export default NavBar;
