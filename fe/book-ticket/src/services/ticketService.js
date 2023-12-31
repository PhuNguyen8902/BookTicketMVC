import { SERVER } from "../assets/js/constants";
import { postData } from "../utils/fetchData";

const ticketService = {
  addTicket(form) {
    return postData(`${SERVER}ticket/add-onl/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
  addTicketMomo(form) {
    return postData(`${SERVER}momo/create-qr/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
  changeTicket(form) {
    return postData(`${SERVER}ticket/change/`, {
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(form),
    });
  },
};

export default ticketService;
