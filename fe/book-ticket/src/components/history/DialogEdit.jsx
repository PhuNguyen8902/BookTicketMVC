import { Button } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import EditTicketBody from "./EditTicketBody";

const DialogEdit = ({ onOpen, onClose, data }) => {
  console.log(data);
  return (
    <Dialog open={onOpen} onClose={onClose}>
      <DialogTitle>Change Ticket</DialogTitle>
      <DialogContent>
        <EditTicketBody data={data} />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} variant="contained">
          Exit
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default DialogEdit;
