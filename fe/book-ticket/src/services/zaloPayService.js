import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const zaloPayService = {
  postZaloQr(item) {
    return postData(`${SERVER}zalo/create-qr`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(item),
    });
  },
  postGetMess() {
    return postData(`${SERVER}zalo/query`, {
      headers: {
        "Content-Type": "application/json",
      },
      //   body: JSON.stringify(item),
    });
  },
};

export default zaloPayService;
