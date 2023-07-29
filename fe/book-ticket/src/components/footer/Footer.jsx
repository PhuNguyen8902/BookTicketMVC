import { Box, Stack, Typography } from "@mui/material";
import { FooterContainer, FooterContent, FooterHeader, FooterTitle, FooterText, FooterSubTitle, FooterTitleBefore, FooterRow,
    StyleArrowForwardIcon, StylePhoneIcon, StyleEmailIcon, StyleLocationOnIcon } from "../../assets/styles/footer";
import { Link } from "react-router-dom";

export default function Footer() {
    return (
        <FooterContainer>
            <FooterContent direction={"row"} spacing={10}>
                <FooterRow>
                    <FooterHeader>DRiver</FooterHeader>
                    <FooterText>Centric application producetize front end portals visualize front end is result and value added</FooterText>
                    <FooterSubTitle>We Are Avaiable</FooterSubTitle>
                    <FooterText>Mon-Sat: 9.00 am to 6.30pm</FooterText>
                </FooterRow>
                <FooterRow>
                    <FooterTitle>
                        <FooterTitleBefore />
                        Quick Link
                    </FooterTitle>
                    <Link to="/">
                        <FooterText>
                            <StyleArrowForwardIcon/>
                            Home
                        </FooterText>
                    </Link>
                    <Link to="/">
                        <FooterText>
                            <StyleArrowForwardIcon/>
                            Routes
                        </FooterText>
                    </Link>
                    <Link to="/aboutUs">
                        <FooterText>
                            <StyleArrowForwardIcon/>
                            About Us
                        </FooterText>
                    </Link>
                    <Link to="/Contact">
                        <FooterText>
                            <StyleArrowForwardIcon/>
                            Contact
                        </FooterText>
                    </Link>
                </FooterRow>
                <FooterRow>
                <FooterTitle>
                        <FooterTitleBefore />
                        Contact Details
                    </FooterTitle>
                    <FooterSubTitle>Phone Number</FooterSubTitle>
                    <FooterText>
                        <StylePhoneIcon />
                        0908091530
                    </FooterText>
                    <FooterSubTitle>Email Address</FooterSubTitle>
                    <FooterText>
                        <StyleEmailIcon />
                        vegarphan@gmail.com
                    </FooterText>
                    <FooterSubTitle>Office Location</FooterSubTitle>
                    <FooterText>
                        <StyleLocationOnIcon />
                        33 Nguyen Van Lac, VietNam
                    </FooterText>
                </FooterRow>
            </FooterContent>
        </FooterContainer>
    )
}