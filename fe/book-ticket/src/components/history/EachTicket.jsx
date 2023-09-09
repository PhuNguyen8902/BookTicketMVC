import { Box, Button, Stack, Typography } from "@mui/material";
import { format } from "date-fns";
import DialogFeedback from "./DialogFeedback";
import { useEffect, useState } from "react";
import DialogEdit from "./DialogEdit";
import { AvailableTicketContainer, AvailableTicketContent, AvailableTicketsContainer, AvailableTicketsContent, AvailableTicketsLeft, AvailableTicketsRight } from "../../assets/styles/historyPage";
import LivingIcon from '@mui/icons-material/Living';
import AdjustIcon from '@mui/icons-material/Adjust';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';

export default function EachTicket({ data }) {
  console.log(data);
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
      data.payment == "COUNTER"
    ) {
      setCheckEdit(true);
    }
  };
  useEffect(() => {
    checkEditAndFeedback();
  }, []);

  const startDate = new Date(data.departureTime);
  let formatStartDate = format(startDate, "yyyy-MM-dd HH:mm:SS");
  const endDate = new Date(data.arrivalTime);
  let formatEndDate = format(endDate, "yyyy-MM-dd HH:mm:SS");
  const bookDate = new Date(data.bookTime);
  const formaBookDate = format(bookDate, "yyyy-MM-dd HH:mm:SS");
  const durtaion = endDate - startDate;
  const formaDuration = format(durtaion, "HH:mm");


  return (
    <AvailableTicketsContainer>
      <AvailableTicketsContent>
        <AvailableTicketContainer>
          <AvailableTicketContent direction="col">
            <AvailableTicketsLeft>
              <Stack sx={{ color: "#388087" }} direction={"row"}>
                <LivingIcon sx={{ fontSize: "40px", margin: "0px 7px 0 0" }} />
                <Typography variant="h4">Seat Number: {data.seat}</Typography>
              </Stack>
              <hr></hr>
              <br></br>
              <Stack direction={"column"}>
                <Stack direction={"row"}>
                  <AdjustIcon sx={{ color: "#388087", fontSize: "27px", margin: "0 7px 0 0" }} />
                  <Typography variant="h6">{formatStartDate} ----- {data.startStation}</Typography>
                </Stack>
                <FiberManualRecordIcon sx={{ color: "#388087", fontSize: "10px", margin: "0 0 6px 8px" }} />
                <FiberManualRecordIcon sx={{ color: "#388087", fontSize: "10px", margin: "0 0 6px 8px" }} />
                <Stack direction={"row"}>
                  <FiberManualRecordIcon sx={{ color: "#388087", fontSize: "10px", margin: "2px 10px 0px 8px" }} />
                  <Typography variant="h7">{formaDuration} Hour</Typography>
                </Stack>
                <FiberManualRecordIcon sx={{ color: "#388087", fontSize: "10px", margin: "0 0 6px 8px" }} />
                <FiberManualRecordIcon sx={{ color: "#388087", fontSize: "10px", margin: "0 0 0 8px" }} />

                <Stack direction={"row"}>
                  <LocationOnIcon sx={{ color: "#388087", fontSize: "32px", margin: "0 7px 0 -3px" }} />
                  <Typography variant="h6">{formatEndDate} ----- {data.endStation}</Typography>
                </Stack>
              </Stack>
            </AvailableTicketsLeft>
            <AvailableTicketsRight>
              <Stack
                sx={{
                  width: "100%",
                  color: "white",
                  textAlign: "center",
                  margin: "0 0 15px 0",
                  alignItems: "end"
                }}>
                {data.isGet ? (
                  <Box
                    sx={{
                      backgroundColor: "rgba(48, 185, 48, 1)",
                      padding: "5px",
                      
                    }}>
                    <Typography>Has Been Taken</Typography>
                  </Box>
                ) :
                  <Box
                    sx={{
                      width: "60%",
                      backgroundColor: "rgba(196, 25, 25, 1)",
                      padding: "5px",
                      
                    }}>
                    <Typography>Hasn't Been Taken</Typography>
                  </Box>
                }
              </Stack>

              <Stack sx={{alignItems: "end", margin: "0 0 10px 0"}}>
                <Typography  variant="h4">{data.price}d</Typography>
              </Stack>
              
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
            </AvailableTicketsRight>
          </AvailableTicketContent>
        </AvailableTicketContainer>
      </AvailableTicketsContent>
    </AvailableTicketsContainer>

  );
}
