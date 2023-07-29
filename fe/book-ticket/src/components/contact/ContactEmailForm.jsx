import { Typography } from "@mui/material";
import { EmailFormButton, EmailFormContainer, EmailFormContent, TextArea } from "../../assets/styles/contactPage";
import image from '../../assets/imgs/th.jpg'


export default function ContactEmailForm() {
    return (
        <>
            <EmailFormContainer direction={"row"}>
                <img src={image} width="50%" />
                <EmailFormContent>
                    <Typography variant="h3" sx={{ marginBottom: "20px" }}>Get A Free Quote</Typography>
                    <Typography variant="h7" sx={{ marginBottom: "40px" }}>Contact us through email to know more information about ours services and company</Typography>
                    <TextArea
                        placeholder="Message"
                        multiline
                        rows={9}
                        maxRows={12}
                    />
                    <EmailFormButton variant="contained">SEND MESSAGE</EmailFormButton>
                </EmailFormContent>
            </EmailFormContainer>
        </>
    )
}