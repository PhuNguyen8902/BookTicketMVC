import { SERVER } from "../assets/js/constants";
import { getData, postData, putData } from "../utils/fetchData";

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
  editRoute(form) {
    return putData(`${SERVER}route/edit`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
  deleteRoute(form) {
    return putData(`${SERVER}route/delete`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
};

export default routeService;
