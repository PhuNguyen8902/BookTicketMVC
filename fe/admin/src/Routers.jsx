import { Routes, Route } from "react-router-dom";
import HomePage from "./routes/AdminHomePage";
import EmployeesPage from "./routes/EmployeesPage";
import { AuthPage } from "./routes";
import CreateAcountPage from "./routes/CreateAccountPage";

export default function Routers() {
  return (
    <Routes>
      <Route path="/Admin/" element={<HomePage />} />
      <Route path="/Admin/Employees" element={<EmployeesPage />} />
      <Route path="/auth" element={<AuthPage />} />
      <Route path="/create-account" element={<CreateAcountPage />} />
      <Route path="*" element={<h1>Page not found</h1>}></Route>
    </Routes>
  );
}