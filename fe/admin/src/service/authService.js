import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const authService = {
  sigIn(form) {
    return postData(`${SERVER}auth/authenticate/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
  refeshToken(form) {
    console.log(form);
    return postData(`${SERVER}auth/refeshToken/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
  register(form) {
    return postData(`${SERVER}auth/register/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
};

export default authService;
