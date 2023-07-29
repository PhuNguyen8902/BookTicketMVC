import { ContactContent, ContactPageContainer } from "../../assets/styles/contactPage";
import ContactEmailForm from "./ContactEmailForm";
import ContactInfomation from "./ContactInformation";


export default function Contact() {
    return (
        <ContactPageContainer>
            <ContactContent>
                <ContactInfomation />
                <ContactEmailForm />
            </ContactContent>
        </ContactPageContainer>
    )
}