import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const authService = {
  sigIn(form) {
    return postData(`${SERVER}auth/authenticate/`, form);
  },
  refeshToken(form) {
    console.log(form);
    return postData(`${SERVER}auth/refeshToken/`, form);
  },
  register(form) {
    return postData(`${SERVER}auth/register/`, form);
  },
};

export default authService;
