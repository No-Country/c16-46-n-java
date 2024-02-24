import NavBar from "./feature/components/NavBar";
//import RegisterForm from "./feature/components/forms/RegisterForm"
import SideBar from "./feature/components/SideBar";
import SearchBar from "./feature/components/SearchBar";
import CardItem from "./feature/components/cards/CardItem";

function App() {

  return (
    <div className="bg-blue-300 w-full h-full flex flex-col">
      <div className="w-full">
        <NavBar />
        <SideBar />
      </div>

      <div className="w-full mt-20">
        <SearchBar />
        <CardItem />
      </div>
    </div>
  );
}

export default App;
