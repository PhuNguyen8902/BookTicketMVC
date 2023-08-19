import { Routes, Route } from "react-router-dom";
// import DemoPage from "./routes/DemoPage";
import HomePage from "./routes/HomePage";
import ContactPage from "./routes/ContactPage";
import ProfileAccountPage from "./routes/ProfileAccountPage";
import AboutUsPage from "./routes/AboutUsPage";
import PayDemoPage from "./routes/PayDemoPage";
import ThanksPage from "./routes/ThanksPage";
export default function Routers() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/contact" element={<ContactPage />} />
      <Route path="/aboutUs" element={<AboutUsPage />} />
      <Route path="/profileAcount" element={<ProfileAccountPage />} />
      <Route path="/demo" element={<PayDemoPage />} />
      <Route path="/thanks" element={<ThanksPage />} />
      <Route path="*" element={<h1>Page Not Found</h1>} />
    </Routes>
  );
}
