import { Routes, Route } from "react-router-dom";
import HomePage from "./routes/AdminHomePage";
import EmployeesTable from "./routes/EmployeesTable";

export default function Routers() {
  return (
      <Routes>
        <Route path="/Admin/" element={<HomePage/>} />
        <Route path="/Admin/Employees" element={<EmployeesTable />} />
        <Route path="*" element={<h1>Page not found</h1>}></Route>
      </Routes>

  );
}