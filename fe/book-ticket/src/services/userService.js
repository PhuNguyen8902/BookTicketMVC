import { SERVER } from "../assets/js/constants";
import { getDataWithToken } from "../utils/fetchData";

const userService = {
  getInfo() {
    return getDataWithToken(`${SERVER}auth/accessToken/`);
  },
};

export default userService;
