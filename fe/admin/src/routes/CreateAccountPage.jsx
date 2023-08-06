import { CreateAccount } from "../components";
import Authenticate from "../components/auth/Authenticate";

export default function CreateAcountPage() {
  return (
    <Authenticate>
      <CreateAccount />
    </Authenticate>
  );
}
