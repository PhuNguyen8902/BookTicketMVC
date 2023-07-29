import { BannerContainer, BannerContent, BannerTitle, BannerDescription, BannerImg } from "../../assets/styles/banner";
import { Box, Typography } from "@mui/material";
// import image from '../../assets/imgs/banner.png'

export default function Banner() {
    return (
        <BannerContainer>
            {/* <img src={image} width={466} /> */}
            <BannerContent>
                <Typography>Welcome to My Page</Typography>
                <BannerTitle>BOOK VEHICAL FOR YOUR RIDE</BannerTitle>
                <BannerDescription variant="subtitle">
                    I don't know what to say tho. But one thing that I'm pretty sure is I still love her.
                    It has been for 8 years. But still those seeds that she planted in my heart, I can't ever
                    weeb it out. What should I do now.
                </BannerDescription>
            </BannerContent>
        </BannerContainer>
    )
}