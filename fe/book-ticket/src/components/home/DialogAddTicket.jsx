import { Button } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import momoService from "../../services/momoService";

const DialogAddTicket = ({ onOpen, onClose, dt }) => {
  const handleSubmit = async () => {
    const response = await momoService.postMomoQr(dt);
    localStorage.setItem("momo", JSON.stringify(response));
    window.location.href = response.payUrl;
    // onclose();
  };
  return (
    <Dialog open={onOpen} onClose={onClose}>
      <DialogTitle>Alert</DialogTitle>
      <DialogContent>
        <DialogContentText>
          {dt.startStation}-{dt.endStation}
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleSubmit} color="primary">
          OK
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default DialogAddTicket;
