import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from "@mui/material";
import { useState } from "react";
import { useSelector } from "react-redux";
import feedbackService from "../../services/feedbackService";

export default function DialogFeedback({ open, close, data }) {
  const user = useSelector((state) => state.auth.user);

  const [content, setContent] = useState("");

  async function fetchAddFeedback(form) {
    const rs = await feedbackService.addFeedback(form);
    if (!rs.message) {
      alert(rs.suscess);
    } else {
      alert(rs.message);
    }
  }

  const handleSubmit = () => {
    const feedbackForm = {
      content: content,
      userId: user.id,
      tripId: data.tripId,
    };
    console.log(feedbackForm);
    fetchAddFeedback(feedbackForm);
  };
  const handleChangeContent = (event) => {
    setContent(event.target.value);
  };
  return (
    <>
      <Dialog open={open} onClose={close}>
        <DialogTitle>Feedback</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Write feedback for the ride {data.tripId} yours
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Content"
            fullWidth
            variant="standard"
            value={content}
            onChange={handleChangeContent}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={close}>Cancel</Button>
          <Button onClick={handleSubmit}>Submit</Button>
        </DialogActions>
      </Dialog>
    </>
  );
}
