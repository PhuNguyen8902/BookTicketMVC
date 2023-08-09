import { SERVER } from "../assets/js/constants";
import { getData, postData } from "../utils/fetchData";

const routeService = {
  getRoute(api) {
    return getData(api);
  },
  addRoute(form) {
    return postData(`${SERVER}route/add`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
};

export default routeService;
