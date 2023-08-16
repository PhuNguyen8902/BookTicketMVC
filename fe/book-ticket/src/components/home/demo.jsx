import { Autocomplete, Box, Button, TextField, Typography } from "@mui/material";
import { AvailableTicketContainer, AvailableTicketContent, AvailableTicketsContainer, AvailableTicketsContent, AvailableTicketsLeft, AvailableTicketsRight, AvailableTicketsRightImg, TicketContainer, TiketCol } from "../../assets/styles/homePage";
import { useEffect, useState } from "react";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import { useSelector } from "react-redux";
import DialogAlertError from "./DialogAlertError";
import DialogAddTicket from "./DialogAddTicket";
import stationService from "../../services/stationService";
import { SERVER } from "../../assets/js/constants";
import tripService from "../../services/tripService";

export default function BookingTicket() {
    const [stationNameData, setStationNameData] = useState("")
    useEffect(() => {
        async function fetchStationData(){
            const rs = await stationService.getStation(`${SERVER}station`);
            const stationName = rs.map(station => station.name)
            if (!rs.message) {
                setStationNameData(stationName);
            } else {
                alert("Error");
            }
        };
        fetchStationData()
    }, [])


    const [tripData, setTripData] = useState("")
    const isLogin = useSelector((state) => state.auth.isLogin);

    // open and close dialog
    const [open, setOpen] = useState(false);

    const handleClose = () => {
        setOpen(false);
    };

    const handleAddBtn = (item) => {
        setOpen(true)
    }

     // // arrivalTime - departureTime
                    // const arrivalTime = new Date(item.arrivalTime);
                    // const departureTime = new Date(item.departureTime);
                    // const timeDifferenceInMilliseconds = arrivalTime - departureTime;

                    // const hours = Math.floor(timeDifferenceInMilliseconds / (1000 * 60 * 60));
                    // const minutes = Math.floor((timeDifferenceInMilliseconds % (1000 * 60 * 60)) / (1000 * 60));

    // fetch trip api

    // ticket
    // <AvailableTicketContainer>
    // <AvailableTicketContent key={item.id} direction={"row"}>
    //     <AvailableTicketsRight direction={"row"}>
    //         <AvailableTicketsRightImg />
    //         <Box sx={{ margin: "-30px 0 0 0" }}>
    //             <Typography variant="h4">{item.startStation} - {item.endStation}</Typography>
    //             <Typography variant="h6">Duration: {hours} Hours {minutes} minutes</Typography>
    //         </Box>
    //     </AvailableTicketsRight>
    //     <AvailableTicketsLeft>
    //         <Box>
    //             <Typography variant="h4" sx={{ margin: "0 0 50px 0" }}>{item.price}/ticket</Typography>
    //             <Button onClick={handleAddBtn(item)} variant="contained" sx={{ width: "40%" }}>
    //                 Add
    //             </Button>
    //             {!isLogin ?
    //                 <DialogAlertError
    //                     onOpen={open}
    //                     onClose={handleClose}
    //                 />
    //                 :
    //                 <DialogAddTicket
    //                     data={soleTripData}
    //                     onOpen={open}
    //                     onClose={handleClose}
    //                 />
    //             }
    //         </Box>
    //     </AvailableTicketsLeft>
    // </AvailableTicketContent>
    // </AvailableTicketContainer>

        // search
    const [searchBtn, setSearchBtn] = useState({
            startStationKeyword: "",
            endStartionKeyword: "",
            startDateKeyword: null
    })
  
    const handleSelectStationFromName = (e, value) => {
        setSearchBtn({
            ...searchBtn,
            startStationKeyword: `${value}`,
        })
    }

    const handleSelectStationToName = (e, value) => {
        setSearchBtn({
            ...searchBtn,
            endStartionKeyword: `${value}`,
        })
    }

    const handleOnchangeStartDate = (value) => {
        // Xu li khi ma thang hoac ngay nho hon 8 thi them so 0
        let day = value.$D > 8 ? `${value.$D}` : `0${value.$D}`
        let month = value.$M > 8 ? `${value.$M + 1}` : `0${value.$M + 1}`
        let date = `${value.$y}-${month}-${day}`
        console.log(date)
        setSearchBtn({
            ...searchBtn,
            startDateKeyword: `${date}`,
        })
    }

    // rerender when clicking
    const handelClickSearchBtn = (searchStaionFrom, searchStationTo, searchDateFrom) => {
        
        const queryParams = new URLSearchParams(window.location.search); // search params
        if(searchStaionFrom !== "" && searchStationTo !== "" && searchDateFrom !== ""){
            queryParams.set("startStation", searchStaionFrom)
            queryParams.set("endStation", searchStationTo)
            queryParams.set("startDate", searchDateFrom)
        }else{
            queryParams.delete("startStation")
            queryParams.delete("endStation")
            queryParams.delete("startDate")
        }
        const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
        window.history.pushState({ path: newUrl }, "", newUrl);
        console.log(newUrl);
    }


    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <TicketContainer>
                <TiketCol>
                    <Autocomplete
                        disableClearable
                        disablePortal
                        id="combo-box-demo"
                        options={stationNameData.length && stationNameData.filter(item => item != searchBtn.endStartionKeyword && item != searchBtn.startStationKeyword)}
                        sx={{ width: 250 }}
                        renderInput={(params) => <TextField {...params} label="Station" />}
                        onChange={handleSelectStationFromName}
                        value={searchBtn.startStationKeyword}
                        className="searchStationNameFrom"
                    />
                    <Autocomplete
                        disableClearable
                        disablePortal
                        id="combo-box-demo"
                        options={stationNameData.length && stationNameData.filter(item => item != searchBtn.endStartionKeyword && item != searchBtn.startStationKeyword)}
                        sx={{ width: 250 }}
                        renderInput={(params) => <TextField {...params} label="Station" />}
                        onChange={handleSelectStationToName}
                        value={searchBtn.endStartionKeyword}
                        className="searchStationNameTo"
                    />
                </TiketCol>
                <TiketCol>
                    <DesktopDatePicker
                        label="Select Start Date"
                        format="MM/DD/YYYY"
                        value={searchBtn.startDateKeyword}
                        onChange={handleOnchangeStartDate}
                    />
                    <DesktopDatePicker
                        disabled
                    />
                </TiketCol>
                <TiketCol>
                    <Button onClick={handelClickSearchBtn(searchBtn.startStationKeyword, searchBtn.endStartionKeyword, searchBtn.startDateKeyword)}>Search</Button>
                </TiketCol>
            </TicketContainer>
            <AvailableTicketsContainer>
                <AvailableTicketsContent>
                    {searchBtn.isActive && tripData}
                </AvailableTicketsContent>
            </AvailableTicketsContainer>
        </LocalizationProvider>
    )
}
