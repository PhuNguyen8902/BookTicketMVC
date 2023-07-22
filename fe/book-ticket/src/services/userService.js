import { SERVER } from "../assets/js/constants";
import { getData } from "../utils/fetchData";

const userService = {
  getInfo() {
    return getData(`${SERVER}accessToken/`);
  },
};

export default userService;
