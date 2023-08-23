import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const momoService = {
  postMomoQr(item) {
    return postData(`${SERVER}momo/create-qr`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });
  },
  postGetMess(item) {
    return postData(`${SERVER}momo/query`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });
  },
};

export default momoService;
