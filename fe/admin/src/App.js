import { BrowserRouter } from "react-router-dom";
import { AppContainer, AppContent } from "./assets/styles/app";
import SizeBar from "./components/sideBar/SideBar";

function App() {
  return (
    <BrowserRouter>
      <AppContainer>
        <AppContent>
          <SizeBar/>
        </AppContent>
      </AppContainer>
    </BrowserRouter>
  );
}

export default App;
