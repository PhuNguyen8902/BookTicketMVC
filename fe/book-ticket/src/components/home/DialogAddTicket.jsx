import { Button, Typography } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import momoService from "../../services/momoService";
import TripInformation from "./TripInformation";
import { SERVER } from "../../assets/js/constants";
import { useState } from "react";
import increasePriceService from "../../services/increasePriceService";
import { useEffect } from "react";

const DialogAddTicket = ({ onOpen, onClose, dt }) => {
  const departureTimeString = dt.departureTime;

  const departureTime = new Date(departureTimeString);

  const startTime = departureTime.getTime();

  // console.log(startTime);
  // let now = new Date();
  // now = now.getTime();
  const [increasePrice, setIncreasePrice] = useState("");
  async function fetchIncreasePrice() {
    const rs = await increasePriceService.getIncreasePrice(
      `${SERVER}increase_price/check/${startTime}`
    );
    setIncreasePrice(rs);
  }
  // console.log(dt);
  const handleSubmit = async () => {
    // const response = await momoService.postMomoQr(dt);
    // localStorage.setItem("momo", JSON.stringify(response));
    // window.location.href = response.payUrl;
    onclose();
  };

  useEffect(() => {
    fetchIncreasePrice();
  }, []);
  return (
    <Dialog open={onOpen} onClose={onClose}>
      <DialogTitle>Trip Information</DialogTitle>
      <DialogContent>
        <TripInformation dt={dt} increase={increasePrice} close={onClose} />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Exit
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default DialogAddTicket;
