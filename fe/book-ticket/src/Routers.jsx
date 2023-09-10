import { Routes, Route } from "react-router-dom";
import HomePage from "./routes/HomePage";
import ContactPage from "./routes/ContactPage";
import ProfileAccountPage from "./routes/ProfileAccountPage";
import AboutUsPage from "./routes/AboutUsPage";
import ThanksPage from "./routes/ThanksPage";
import ThanksPageZalo from "./routes/ThanksPageZalo";
import HistoryPage from "./routes/HistoryPage";
export default function Routers() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/contact" element={<ContactPage />} />
      <Route path="/aboutUs" element={<AboutUsPage />} />
      <Route path="/profileAcount" element={<ProfileAccountPage />} />
      <Route path="/history" element={<HistoryPage />} />
      <Route path="/thanks" element={<ThanksPage />} />
      <Route path="/thanks-zalo" element={<ThanksPageZalo />} />

      <Route path="*" element={<h1>Page Not Found</h1>} />
    </Routes>
  );
}
