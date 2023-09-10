import { SERVER } from "../assets/js/constants";
import { getDataWithToken, postData } from "../utils/fetchData";

const userService = {
  getInfo() {
    return getDataWithToken(`${SERVER}auth/accessToken/`);
  },

  updateBasicUser(form){
    return postData(`${SERVER}user/update-basic-user/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
};

export default userService;
