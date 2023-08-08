import { SERVER } from "../assets/js/constants.js";
import { getData } from "../utils/fetchData";

const routeService = {
  getRoute(api) {
    return getData(api);
  },
  getTotalPage() {
    return getData(`${SERVER}route/total-pages`);
  },
};

export default routeService;
