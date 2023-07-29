import styled from "@emotion/styled";
import { Box, Stack, Typography } from "@mui/material";
import { colors } from "../theme";
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import PhoneIcon from '@mui/icons-material/Phone';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import EmailIcon from '@mui/icons-material/Email';

import "@fontsource/montez"


export const FooterContainer = styled(Stack)(({
    width: "100%",
    alignItems: "center",
    backgroundColor: colors.secondary,
}))

export const FooterContent = styled(Stack)(({
    width: "80%",
    padding: "60px 160px",
}))

export const FooterHeader = styled(Typography)(({
    fontSize: '4em  !important',
    fontFamily: '"Montez", "cursive" !important',
    padding: '4px',
    color: colors.white,
}))

export const FooterRow = styled(Stack)(({
    width: "33.33%",
}))

export const FooterTitle = styled(Typography)(({
    fontSize: '2em  !important',
    fontFamily: 'Garamond, serif;',
    padding: '4px',
    margin: "20px 0",
    color: colors.white,
    position: "relative",
}))

export const FooterTitleBefore = styled("span")(({
    content: "''",
    position: "absolute",
    top: "50px",
    left: "5px",
    width: "60px",
    height: "3px",
    backgroundColor: colors.white,
}))

export const FooterSubTitle = styled(Typography)(({
    fontSize: '1.4em  !important',
    fontFamily: 'Garamond, serif;',
    padding: '4px',
    color: colors.white,
}))

export const FooterText = styled(Typography)(({
    color: colors.fourth,
}))

const StyleIcon = {
    margin: "0 5px -4px 0",
}
export const StyleArrowForwardIcon = styled(ArrowForwardIcon)(({
    ...StyleIcon
}))

export const StylePhoneIcon = styled(PhoneIcon)(({
    ...StyleIcon
}))

export const StyleLocationOnIcon = styled(LocationOnIcon)(({
    ...StyleIcon
}))

export const StyleEmailIcon = styled(EmailIcon)(({
    ...StyleIcon
}))

