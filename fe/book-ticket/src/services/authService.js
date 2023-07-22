import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const authService = {
  sigIn(form) {
    return postData(`${SERVER}authenticate/`, form);
  },
  refeshToken(form) {
    console.log(form);
    return postData(`${SERVER}refeshToken/`, form);
  },
  register(form) {
    return postData(`${SERVER}register/`, form);
  },
};

export default authService;
