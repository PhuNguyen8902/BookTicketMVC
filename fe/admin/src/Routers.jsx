import { Routes, Route } from "react-router-dom";
import HomePage from "./routes/AdminHomePage";
import EmployeesPage from "./routes/EmployeesPage";

export default function Routers() {
  return (
      <Routes>
        <Route path="/Admin/" element={<HomePage/>} />
        <Route path="/Admin/Employees" element={<EmployeesPage />} />
        <Route path="*" element={<h1>Page not found</h1>}></Route>
      </Routes>

  );
}