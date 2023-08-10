import {
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  DialogContentText,
} from "@mui/material";
import routeService from "../../service/routeService";
export default function AlertDeleteRoute({ open, onClose, form, nameStation }) {
  const handleDelete = async () => {
    nameStation.forEach((station) => {
      if (station.name == form.endStation) {
        form.endStation = station.id;
      }
      if (station.name == form.startStation) {
        form.startStation = station.id;
      }
    });
    const response = await routeService.deleteRoute(form);
    if (!response.message) {
      onClose();
    } else {
      alert(response);
    }
  };
  return (
    <Dialog
      open={open}
      aria-labelledby="alert-dialog-title"
      aria-describedby="alert-dialog-description"
    >
      <DialogTitle id="alert-dialog-title">
        {"Are you sure you want to delete?"}
      </DialogTitle>
      <DialogContent>
        <DialogContentText id="alert-dialog-description">
          Are you sure you want to delete {form.name}? This may affect data
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Disagree</Button>
        <Button onClick={handleDelete} autoFocus>
          Agree
        </Button>
      </DialogActions>
    </Dialog>
  );
}
