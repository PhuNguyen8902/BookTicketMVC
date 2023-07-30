import { BrowserRouter } from "react-router-dom";
import { AppContainer, AppContent } from "./assets/styles/app";
import SizeBar from "./components/sideBar/SideBar";
import Routers from "./Routers";

function App() {
  return (
    <BrowserRouter>
      <AppContainer>
        <AppContent direction={"row"}>
          <SizeBar/>
          <Routers/>
        </AppContent>
      </AppContainer>
    </BrowserRouter>
  );
}

export default App;
