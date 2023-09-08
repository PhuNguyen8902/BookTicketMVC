import { Button } from "@mui/material";
import DialogFeedback from "./DialogFeedback";
import { useEffect, useState } from "react";
import DialogEdit from "./DialogEdit";

export default function EachTicket({ data }) {
  const [addOpen, setAddOpen] = useState(false);
  const [editOpen, setEditOpen] = useState(false);
  const [checkFeedback, setCheckFeedback] = useState(false);
  const [checkEdit, setCheckEdit] = useState(false);

  const handleFeedback = () => {
    setAddOpen(true);
  };
  const handleCloseFeedback = () => {
    setAddOpen(false);
  };
  const handleEdit = () => {
    setEditOpen(true);
  };
  const handleCloseEdit = () => {
    setEditOpen(false);
  };
  const checkEditAndFeedback = () => {
    const currentDate = new Date();
    const currentDateInMillis = currentDate.getTime();
    if (data.arrivalTime < currentDateInMillis) {
      setCheckFeedback(true);
    }
    const currentDate2 = new Date();
    currentDate2.setMinutes(currentDate2.getMinutes() + 60);

    // Lấy giá trị kiểu long sau khi cộng thêm
    const updatedDateInMillis = currentDate2.getTime();
    if (
      data.departureTime >= updatedDateInMillis &&
      data.payment == "Pay at the counter"
    ) {
      setCheckEdit(true);
    }
  };
  useEffect(() => {
    checkEditAndFeedback();
  }, []);
  return (
    <>
      <h1>{data.ticketId}</h1>
      {checkFeedback ? (
        <Button variant="contained" onClick={handleFeedback}>
          Feedback
        </Button>
      ) : null}
      {checkEdit ? (
        <Button variant="contained" onClick={handleEdit}>
          Change Ticket
        </Button>
      ) : null}
      {addOpen ? (
        <DialogFeedback
          open={addOpen}
          close={handleCloseFeedback}
          data={data}
        />
      ) : null}
      {editOpen ? (
        <DialogEdit onOpen={editOpen} onClose={handleCloseEdit} data={data} />
      ) : null}
    </>
  );
}
