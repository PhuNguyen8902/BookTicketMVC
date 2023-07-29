import { BeforeInfoAboutUsUpperTitleLine1, BeforeInfoAboutUsUpperTitleLine2, InfoAboutUsContainer, InfoAboutUsContent, InfoAboutUsUpperTitles, StyleCheckCircleOutlineIcon } from "../../assets/styles/aboutUsPage";
import Imgage from "../../assets/imgs/th.jpg"
import { Button, Stack, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import ArrowRightAltIcon from '@mui/icons-material/ArrowRightAlt';

export default function InfoAboutUs() {
    return (
        <InfoAboutUsContainer direction={"row"} >
            <img src={Imgage} width={"50%"} />
            <InfoAboutUsContent>
                <InfoAboutUsUpperTitles>
                    <BeforeInfoAboutUsUpperTitleLine1 />
                    <BeforeInfoAboutUsUpperTitleLine2 />
                    About our Company
                </InfoAboutUsUpperTitles>
                <Typography variant="h4" sx={{ marginBottom: "20px" }}>Wherever You Need To Go We'll Take You There.</Typography>
                <Typography sx={{ marginBottom: "20px", fontWeight: "100" }}>Authoritatively simplify open-sourse Resources via backend visualize business e-markets before parallel convergence
                    optimize sticky and idea-sharing rather than unique solutionsl.
                </Typography>
                <Stack direction={"row"} spacing={2} sx={{ backgroundColor: "#F5F5F5", padding: "15px", marginBottom: "20px" }}>
                    <img src={Imgage} width={"20%"} />
                    <Stack spacing={1}>
                        <Typography>
                            <StyleCheckCircleOutlineIcon />
                            Easy & Emergency Solutions Anytime
                        </Typography>
                        <Typography>
                            <StyleCheckCircleOutlineIcon />
                            Getting Affordable price upto 2 years
                        </Typography>
                        <Typography>
                            <StyleCheckCircleOutlineIcon />
                            More Reliable & Experieneced Teams
                        </Typography>
                    </Stack>
                </Stack>
                <Link to="/contact">
                    <Button variant="contained" sx={{ padding: "15px 25px" }}>
                        Contact Us
                        <ArrowRightAltIcon sx={{ marginLeft: "5px" }} />
                    </Button>
                </Link>
            </InfoAboutUsContent>
        </InfoAboutUsContainer>
    )
}