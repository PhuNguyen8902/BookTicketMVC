import { Box, Card, InputAdornment, Stack, TextField } from "@mui/material";
import styled from "styled-components";
import { colors } from "../theme";

export const HomePageContainer = styled(Stack)(({
    width: "100%"    
}))

export const HomePageContent = styled(Stack)(({
  justifyContent: "center",
  alignItems: "center",
  width: "100%"
}))

export const TicketContainer = styled(Card)(({
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    padding: "30px",
    width: "700px",
    backgroundColor: "white",
    margin: "-90px 0 90px 0"
})) 

export const TiketCol = styled(Box)(({
    display: "flex",
    justifyContent: "space-around",
    marginBottom: "20px"
}))


// export const IconTextField = ({ iconStart, iconEnd, ...props }) => {
//     return (
//       <TextField
//         {...props}
//         InputProps={{
//           startAdornment: iconStart ? (
//             <InputAdornment position="start">{iconStart}</InputAdornment>
//           ) : null,
//           endAdornment: iconEnd ? (
//             <InputAdornment position="end">{iconEnd}</InputAdornment>
//           ) : null
//         }}
//       />
//     );
//   };

export const AvailableTicketContainer = styled(Stack)(({
    margin: "40px 0",
    width: "100%",
})) 

export const AvailableTicketContent = styled(Stack)(({
    width: "80%",
    alignItems: "center",
}))