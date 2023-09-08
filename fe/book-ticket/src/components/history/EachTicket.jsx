import { Button } from "@mui/material";
import DialogFeedback from "./DialogFeedback";
import { useState } from "react";

export default function EachTicket({ data }) {
  const [addOpen, setAddOpen] = useState(false);

  const handleEdit = () => {
    // console.log(data);
    setAddOpen(true);
  };
  const handleCloseEdit = () => {
    setAddOpen(false);
  };
  return (
    <>
      <h1>{data.ticketId}</h1>
      {data.isActive == 0 ? (
        <Button variant="contained" onClick={handleEdit}>
          button
        </Button>
      ) : null}
      {addOpen ? (
        <DialogFeedback open={addOpen} close={handleCloseEdit} data={data} />
      ) : null}
    </>
  );
}
