import NavBar from "./feature/components/NavBar";
import SideBar from "./feature/components/SideBar";
import Home from "./pages/Home";
import ModalChat from "./feature/components/ModalChat";
import ModalPostDetail from "./feature/components/ModalDetail";
import { ToastContainer } from 'react-toastify';

function App() {
  return (
  
      <main className="w-full min-h-screen flex flex-col">
        <NavBar />
        <SideBar />
        <Modal />
        <ModalChat />
        <ModalPostDetail />
        <ToastContainer
          position="top-center"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          theme="light"
          transition= "Bounce"
          />
        <Home />
        {/* <ChatFront /> */}
      </main>
 
  );
}

export default App;
