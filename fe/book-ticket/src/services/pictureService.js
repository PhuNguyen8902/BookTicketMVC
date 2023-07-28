import { SERVER } from "../assets/js/constants";
import { postData, postImg } from "../utils/fetchData";

const pictureService = {
  postPicture(form) {
    return postImg(`${SERVER}auth/picture/demo/`, form);
  },
};

export default pictureService;
