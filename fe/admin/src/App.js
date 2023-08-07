import { BrowserRouter } from "react-router-dom";
import { AppContainer, AppContent } from "./assets/styles/app";
import SizeBar from "./components/sideBar/SideBar";
import Routers from "./Routers";
import { IsLoading, Popup } from "./components";
import { useSelector } from "react-redux";

function App() {
  const popup = useSelector((state) => state.page.popup);
  return (
    <BrowserRouter>
      <AppContainer>
        <AppContent direction={"row"} spacing={10}>
          <SizeBar />
          <Routers />
        </AppContent>
      </AppContainer>
      {Object.keys(popup).filter((key) => popup[key] === true).length > 0 && (
        <Popup>{popup.isLoading && <IsLoading />}</Popup>
      )}
    </BrowserRouter>
  );
}

export default App;
