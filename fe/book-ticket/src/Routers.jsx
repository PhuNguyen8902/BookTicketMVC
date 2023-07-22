import { Routes, Route } from "react-router-dom";
import DemoPage from "./routes/DemoPage";
export default function Routers() {
  return (
    <Routes>
      <Route path="/demo" element={<DemoPage />} />
    </Routes>
  );
}
