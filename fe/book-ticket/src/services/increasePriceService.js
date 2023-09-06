import { getData } from "../utils/fetchData";

const increasePriceService = {
  getIncreasePrice(api) {
    return getData(api);
  },
};

export default increasePriceService;
