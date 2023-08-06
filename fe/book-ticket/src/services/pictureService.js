import { SERVER } from "../assets/js/constants";
import { postData, postImg } from "../utils/fetchData";

const pictureService = {
  postPicture(form) {
    return postData(`${SERVER}auth/picture/demo/`, {
      body: form,
    });
  },
};

export default pictureService;