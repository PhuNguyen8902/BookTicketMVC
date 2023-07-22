import { useSelector } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import Routers from "./Routers";
import { Popup, DemoAuthBox } from "./components";

function App() {
  const popup = useSelector((state) => state.page.popup);
  return (
    <BrowserRouter>
      <Routers />
      {Object.keys(popup).filter((key) => popup[key] === true).length > 0 && (
        <Popup>{popup.auth && <DemoAuthBox />}</Popup>
      )}
    </BrowserRouter>
  );
}

export default App;
