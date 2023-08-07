import { Authenticate, AuthenticateAdmin, CreateAccount } from "../components";

export default function CreateAcountPage() {
  return (
    <Authenticate>
      <CreateAccount />
    </Authenticate>
  );
}
