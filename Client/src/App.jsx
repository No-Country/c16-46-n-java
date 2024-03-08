import NavBar from "./feature/components/NavBar";
import SideBar from "./feature/components/SideBar";
import Home from "./pages/Home";
import ModalChat from "./feature/components/ModalChat";
import ModalPostDetail from "./feature/components/ModalDetail";

function App() {


  return (
  
      <main className="w-full h-full flex flex-col">
        <NavBar />
        <SideBar />
        <ModalChat />
        <ModalPostDetail />
        <Home />
        {/* <ChatFront /> */}
      </main>
 
  );
}

export default App;
