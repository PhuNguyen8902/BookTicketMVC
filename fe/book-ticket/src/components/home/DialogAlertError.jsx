import { Button } from "@mui/material";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

const DialogAlertError = ({ onOpen, onClose, mess }) => {
  return (
    <Dialog open={onOpen} onClose={onClose}>
      <DialogTitle>Alert</DialogTitle>
      <DialogContent>
        <DialogContentText>{mess}</DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          OK
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default DialogAlertError;
