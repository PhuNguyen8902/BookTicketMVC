import { Box, Card, Stack } from "@mui/material"
import styled from "styled-components"


export const AvailableTicketsContainer = styled(Stack)(({
    margin: "20px 0",
    width: "100%",
    alignItems: "center",
})) 

export const AvailableTicketsContent = styled(Stack)(({
    width: "70rem",
    alignItems: "center",
}))

export const AvailableTicketContainer = styled(Card)(({
    width: "100%",
    margin: "0 0 50px 0",
    padding: "30px",
}))

export const AvailableTicketContent = styled(Stack)(({
    width: "100%",
}))

// left side
export const AvailableTicketsLeft = styled(Stack)(({
    width: "70%",
    margin: "0 3rem 0 0"
}))



// right side
export const AvailableTicketsRight = styled(Stack)(({
    width: "30%",
}))

// export const AvailableTicketsRightImg = styled(DirectionsBusIcon)(({
//     color: colors.secondary,
//     width: "7rem !important",
//     height: "9rem !important",

// }))

