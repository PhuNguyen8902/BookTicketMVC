import { Authenticate, AuthenticateAdmin } from "../components";
import Employees from "../components/employees/Employees";
import { useEffect } from "react";

export default function EmployeesPage() {
  return (
    <AuthenticateAdmin>
      <Employees />
    </AuthenticateAdmin>
  );
}
