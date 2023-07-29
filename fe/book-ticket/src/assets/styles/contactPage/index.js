import { Box, Button, Card, Stack, TextField, Typography, keyframes } from "@mui/material";
import styled from "styled-components";
import { colors } from "../theme";
import LocationOnIcon from '@mui/icons-material/LocationOn';
import PhoneInTalkIcon from '@mui/icons-material/PhoneInTalk';
import MailOutlineIcon from '@mui/icons-material/MailOutline';

export const ContactPageContainer = styled(Box)(({
    display: "flex",
    justifyContent: "center",
    margin: "40px 0",
    width: "100%"
}))


export const ContactContent = styled(Box)(({
    display: "flex",
    flexDirection: "column",
    width: "80%"
}))
// Cards
export const CardsContainer = styled(Stack)(({
    justifyContent: "space-around",
    width: "100%",
    marginBottom: "120px",
}))

export const CardContainer = styled(Card)(({
    width: "30%",
    "&:hover": {
        backgroundColor: colors.secondary,
    }
}))

export const CardContent = styled(Box)(({
    display: "flex",
    flexDirection: "column",
}))

const StyleIcon = {
    font: "center",
    margin: "10px 44%",
    fontSize: "40px !important",
}
export const StyleLocationIcon = styled(LocationOnIcon)(({ishover}) => ({
    color: ishover? "white" : colors.secondary, 
    ...StyleIcon,
}))

export const StylePhoneInTalkIcon = styled(PhoneInTalkIcon)(({ishover}) => ({
    ...StyleIcon,
    color: ishover? "white" : colors.secondary  
}))

export const StyleMailOutlineIcon = styled(MailOutlineIcon)(({ishover}) => ({
    color: ishover? "white" : colors.secondary, 
    ...StyleIcon,
}))

export const CardTitle = styled(Typography)(({ishover}) => ({
    textAlign: "center",
    marginBottom: "10px !important",
    fontSize: "14px !important",
    color: ishover? "white" : "black"  
}))

export const CardDescription = styled(Typography)(({ishover}) =>({
    textAlign: "center",
    fontSize: "22px !important",
    color: ishover? "white" : "black"  
}))

//email form

export const EmailFormContainer = styled(Stack)(({
    width: "100%",
    marginBottom: "80px"
}))

export const EmailFormContent = styled(Stack)(({
    width: "50%",
    backgroundColor: "#F5F5F5",
    padding: "50px"
}))

export const EmailFormButton = styled(Button)(({
    backgroundColor: colors.secondary,
}))

export const TextArea = styled(TextField)(({
    backgroundColor: "white",
    padding: "20px",
    border: "none !important" ,
    marginBottom: "40px !important"
}))