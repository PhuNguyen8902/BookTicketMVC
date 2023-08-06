import Authenticate from "../components/auth/Authenticate";
import Home from "../components/home/AdminHomePage";
// import Authenticate from "../components/index";

export default function AdminHomePage() {
  return (
    <Authenticate>
      <Home />
    </Authenticate>
  );
}
