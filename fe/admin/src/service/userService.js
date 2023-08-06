import { SERVER } from "../assets/js/constants.js";
import { getDataWithToken } from "../utils/fetchData";

const userService = {
  getInfo() {
    return getDataWithToken(`${SERVER}admin/accessToken/`);
  },
};

export default userService;
