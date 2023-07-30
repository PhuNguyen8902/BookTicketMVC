import { useSelector } from "react-redux";
import { BrowserRouter } from "react-router-dom";
import Routers from "./Routers";
import { Popup, AuthBox } from "./components";
import NavBar from "./routes/NavBar";
import Banner from "./components/banner/Banner";
import { ThemeProvider } from "@emotion/react";
import theme from "./assets/styles/theme";
import FooterPage from "./routes/FooterPage";

function App() {
  const popup = useSelector((state) => state.page.popup);
  return (
    <BrowserRouter>
      <ThemeProvider theme={theme}>
        <NavBar />
        <Banner />
        <Routers />
        {Object.keys(popup).filter((key) => popup[key] === true).length > 0 && (
          <Popup>{popup.auth && <AuthBox />}</Popup>
        )}
        <FooterPage />
      </ThemeProvider>
    </BrowserRouter>
  );
}

export default App;
