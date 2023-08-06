import Authenticate from "../components/auth/Authenticate";
import Employees from "../components/employees/Employees";

export default function EmployeesPage() {
  return (
    <Authenticate>
      <Employees />
    </Authenticate>
  );
}
