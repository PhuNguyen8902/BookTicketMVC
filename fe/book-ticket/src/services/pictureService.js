import { SERVER } from "../assets/js/constants";
import { postImg } from "../utils/fetchData";

const pictureService = {
  postPicture(form) {
    return postImg(`${SERVER}auth/picture/demo/`, form);
  },
};

export default pictureService;
