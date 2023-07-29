import { Stack, Typography } from "@mui/material";
import { StepAboutUsCard, StepAboutUsCardAfter, StepAboutUsCardBefore, StepAboutUsContainer, StepAboutUsCardAfterTitle, StepAboutUsCardBeforeIcon1,
    StepAboutUsCardBeforeIcon2, StepAboutUsContent, StepAboutUsUpperTitle, StepAboutUsWrapCard, StepAboutUsImg } from "../../assets/styles/aboutUsPage";


export default function StepServiceAboutUs() {
    return (
        <StepAboutUsContainer>
            <StepAboutUsContent>
                <StepAboutUsUpperTitle>Our Working Process</StepAboutUsUpperTitle>
                <Typography variant="h4" sx={{marginBottom: "50px"}}>Our Basic Work Process</Typography>
                <Stack direction={"row"} spacing={20}>
                    <StepAboutUsWrapCard>
                        <StepAboutUsCardBefore>
                            <StepAboutUsCardBeforeIcon1 />
                        </StepAboutUsCardBefore>
                        <StepAboutUsCard>
                            <Typography variant="h5" sx={{marginBottom: "20px"}}>Seaching Route</Typography>
                            <Typography>Searching the Route that you want to participate in</Typography>
                        </StepAboutUsCard>
                        <StepAboutUsCardAfter>
                            <StepAboutUsCardAfterTitle>1</StepAboutUsCardAfterTitle>
                        </StepAboutUsCardAfter>
                    </StepAboutUsWrapCard>
                    <StepAboutUsWrapCard>
                        <StepAboutUsCardBefore>
                            <StepAboutUsCardBeforeIcon2 />
                        </StepAboutUsCardBefore>
                        <StepAboutUsCard>
                            <Typography variant="h5" sx={{marginBottom: "20px"}}>Book A Ticket</Typography>
                            <Typography>The users contacts the provider through a phone call, an app, a webiste.</Typography>
                        </StepAboutUsCard>
                        <StepAboutUsCardAfter>
                            <StepAboutUsCardAfterTitle>2</StepAboutUsCardAfterTitle>
                        </StepAboutUsCardAfter>
                    </StepAboutUsWrapCard>
                    <StepAboutUsWrapCard>   
                        <StepAboutUsCardBefore>
                            <StepAboutUsCardBeforeIcon1 />
                        </StepAboutUsCardBefore>
                        <StepAboutUsCard>
                            <Typography variant="h5" sx={{marginBottom: "20px"}}>Arrive Safely</Typography>
                            <Typography>The users provides their pick up location, and preferred time of arrival.</Typography>
                        </StepAboutUsCard>
                        <StepAboutUsCardAfter>
                            <StepAboutUsCardAfterTitle>3</StepAboutUsCardAfterTitle>
                        </StepAboutUsCardAfter>
                    </StepAboutUsWrapCard>
                </Stack>
            </StepAboutUsContent>
        </StepAboutUsContainer>
    )
}