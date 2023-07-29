import { useRef, useState } from "react";
import { CardsContainer, ContactContent, CardContainer, CardTitle, CardDescription, StyleLocationIcon, StylePhoneInTalkIcon, StyleMailOutlineIcon } from "../../assets/styles/contactPage";
import { CardContent, Stack, Typography } from "@mui/material";
import './Contact.css'
export default function ContactInfomation() {

    function useHoverState(initialValue = false) {
        const [hoveredState, sethoveredState] = useState(initialValue);

        const handleHover = () => {
            sethoveredState(true)
        };

        const handleLeave = () => {
            sethoveredState(false)
        };

        return [hoveredState, handleHover, handleLeave];
    }

    const [hoveredRef1, handleHover1, handleLeave1] = useHoverState();
    const [hoveredRef2, handleHover2, handleLeave2] = useHoverState();
    const [hoveredRef3, handleHover3, handleLeave3] = useHoverState();

    // const handleHover = () => {
    //   hoveredRef.current = true;
    // };

    // const handleLeave = () => {
    //   hoveredRef.current = false;
    // };
    // console.log(hoveredRef.current)
    // const handleHover = () => {
    //     console.log("in")
    //     console.log(hoveredRef.current)
    //     hoveredRef.current.classList.add("active")
    // }
    // const handleLeave = () => {
    //     console.log("out")
    //     console.log(hoveredRef.current)
    //     hoveredRef.current.classList.remove("active")
    // }
    return (
        <>
            <Typography variant="h3" sx={{ margin: "0 0 80px 0 ", textAlign: "center" }}>Contact Us</Typography>
            <CardsContainer direction="row" spacing={2}>
                <CardContainer>
                    {/* <CardContent ref={hoveredRef} className="CardContent" onMouseEnter={handleHover} onMouseLeave={handleLeave}>
                        <StyleLocationIcon className="CardContent_StyleLocationIcon" />
                        <CardTitle className="CardContent_CardTitle">Our Office Location</CardTitle>
                        <CardDescription className="CardContent_CardDescription">33 Nguyen Van Lac, VietNam</CardDescription>
                    </CardContent> */}
                    <CardContent onMouseEnter={handleHover1} onMouseLeave={handleLeave1} >
                        <StyleLocationIcon ishover={hoveredRef1} />
                        <CardTitle ishover={hoveredRef1}>Our Office Location</CardTitle>
                        <CardDescription ishover={hoveredRef1}>33 Nguyen Van Lac, VietNam</CardDescription>
                    </CardContent>
                </CardContainer>
                <CardContainer>
                    <CardContent onMouseEnter={handleHover2} onMouseLeave={handleLeave2}>
                        <StylePhoneInTalkIcon ishover={hoveredRef2} />
                        <CardTitle ishover={hoveredRef2}>Have A Question?</CardTitle>
                        <CardDescription ishover={hoveredRef2}>0908091530</CardDescription>
                    </CardContent>
                </CardContainer>
                <CardContainer>
                    <CardContent onMouseEnter={handleHover3} onMouseLeave={handleLeave3}>
                        <StyleMailOutlineIcon ishover={hoveredRef3} />
                        <CardTitle ishover={hoveredRef3}>Any Question? Email Us!</CardTitle>
                        <CardDescription ishover={hoveredRef3}>vegarphan@gmail.com</CardDescription>
                    </CardContent>
                </CardContainer>
            </CardsContainer>
        </>
    )
}



