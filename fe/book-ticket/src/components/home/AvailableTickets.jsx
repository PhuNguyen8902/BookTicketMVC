// import { Box, Button, Typography } from "@mui/material";
// import { AvailableTicketContainer, AvailableTicketContent, AvailableTicketsLeft, AvailableTicketsRight, AvailableTicketsRightImg } from "../../assets/styles/homePage";
// import tripService from "../../services/tripService";
// import { SERVER } from "../../assets/js/constants";
// import { useEffect, useState } from "react";
// import { useSelector } from "react-redux";
// import DialogAlertError from "./DialogAlertError";
// import DialogAddTicket from "./DialogAddTicket";


// export default function AvailableTickets() {
//     const [tickets, setTickets] = useState(null);
//     const [open, setOpen] = useState(false)
//     const [data, setData] = useState(null)

//     const isLogin = useSelector((state) => state.auth.isLogin);

//     const handleOpen = (item) =>{
//         setData(item)
//         setOpen(true)
//     }

//     const handleClose = () =>{
//         setOpen(false)
//     }

//     const fetchData = async () => {
//         const queryParams = new URLSearchParams(window.location.search); // search params
//         const api = `${SERVER}trip?${queryParams.toString()}`;
//         const rs = await tripService.getTrip(api);
        
//         if (!rs.message) {
//             const ticket = rs.map(item => {
//                 // tinh khoang thoi gian 
//                 const arrivalTime = new Date(item.arrivalTime);
//                 const departureTime = new Date(item.departureTime);
//                 const timeDifferenceInMilliseconds = arrivalTime - departureTime;

//                 const hours = Math.floor(timeDifferenceInMilliseconds / (1000 * 60 * 60));
//                 const minutes = Math.floor((timeDifferenceInMilliseconds % (1000 * 60 * 60)) / (1000 * 60));
//                 // render ra tung ve dung voi params
//                 return (
//                     <AvailableTicketContainer key={item.id} >
//                         <AvailableTicketContent direction={"row"}>
//                             <AvailableTicketsRight direction={"row"}>
//                                 <AvailableTicketsRightImg />
//                                 <Box sx={{ margin: "-30px 0 0 0" }}>
//                                     <Typography variant="h4">{item.startStation} - {item.endStation}</Typography>
//                                     <Typography variant="h6">Duration: {hours} Hours {minutes} minutes</Typography>
//                                 </Box>
//                             </AvailableTicketsRight>
//                             <AvailableTicketsLeft>
//                                 <Box>
//                                     <Typography variant="h4" sx={{ margin: "0 0 50px 0" }}>{item.price}/ticket</Typography>
//                                     <Button onClick={() => handleOpen(item)} variant="contained" sx={{ width: "40%" }}>
//                                         Add
//                                     </Button>
//                             {!isLogin ?
//                             <DialogAlertError
//                                 onOpen={open}
//                                 onClose={handleClose}
//                             />
//                             :
//                             data != null ? 
//                             <DialogAddTicket
//                                 onOpen={open}
//                                 onClose={handleClose}
//                                 dt={data}
//                             /> : null
//                             }
//                                 </Box>
//                             </AvailableTicketsLeft>
//                         </AvailableTicketContent>
//                     </AvailableTicketContainer>
//                 )

//             })
//             setTickets(ticket);
//         }
//         else {
//             alert("error: can't fetch data");
//         }
//     }

//     useEffect(() => {
//         fetchData();
//     }, [open])


//     return (
//         <>
//             {tickets}
//         </>
//     );
// }