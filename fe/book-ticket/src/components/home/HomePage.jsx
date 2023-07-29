import { HomePageContainer, HomePageContent } from "../../assets/styles/homePage";
import InfoAboutUs from "../aboutUs/InfoAboutUs";
import BookingTicket from "./BookingTicket";


export default function Home() {
    return (
        <HomePageContainer>
            <HomePageContent>
                <BookingTicket />
            </HomePageContent>
        </HomePageContainer>
    )
}