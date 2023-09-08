import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const feedbackService = {
  addFeedback(form) {
    return postData(`${SERVER}feedback/add/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
};

export default feedbackService;
