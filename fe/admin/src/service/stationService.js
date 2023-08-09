import { SERVER } from "../assets/js/constants";
import { getData } from "../utils/fetchData";

const stationService = {
  getNameStation() {
    return getData(`${SERVER}station/name`);
  },
};

export default stationService;
