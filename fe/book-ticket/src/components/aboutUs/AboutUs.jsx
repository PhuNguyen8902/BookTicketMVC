import { AboutUsContainer, AboutUsContent } from "../../assets/styles/aboutUsPage";
import InfoAboutUs from "./InfoAboutUs";
import OurVehicles from "./OurVehicles";
import StepServiceAboutUs from "./StepServiceAboutUs";

export default function AboutUs() {
    return (
        <>
            <AboutUsContainer>
                <AboutUsContent>
                    <InfoAboutUs />
                    <OurVehicles />
                    <StepServiceAboutUs />
                </AboutUsContent>
            </AboutUsContainer>
        </>
    )
}