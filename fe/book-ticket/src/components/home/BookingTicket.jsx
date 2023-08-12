
import { Autocomplete, Box, Button, TextField, Typography } from "@mui/material";
import { AvailableTicketContainer, AvailableTicketContent, AvailableTicketsContainer, AvailableTicketsContent, AvailableTicketsLeft, AvailableTicketsRight, AvailableTicketsRightImg, TicketContainer, TiketCol } from "../../assets/styles/homePage";

import { useEffect, useState } from "react";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import moment from "moment";

export default function BookingTicket() {
    const [stationNameData, setStationNameData] = useState("")
    useEffect(() => {
        async function fetchStationData() {
            try {
                const response = await fetch("http://localhost:8080/backend/api/station")
                const data = await response.json()
                const stationName = data.map(station => station.name)
                setStationNameData(stationName)
            } catch (err) {
                console.log(err)
            }
        }
        fetchStationData()
    }, [])


    // search
    const [searchBtn, setSearchBtn] = useState({
        startStationKeyword: "",
        endStartionKeyword: "",
        startDateKeyword: null,
        isActive: false
    })


    const [tripData, setTripData] = useState("")
    // fetch trip api
    useEffect(() => {
        async function fetchTripData() {
            try {
                const api = `http://localhost:8080/backend/api/trip?stateStation=${searchBtn.startStationKeyword}&endStation=${searchBtn.endStartionKeyword}&startDate=${searchBtn.startDateKeyword}`;
                const response = await fetch(api)
                const data = await response.json()

                const t = data.map(item => {
                    const arrivalTime = new Date(item.arrivalTime);
                    const departureTime = new Date(item.departureTime);
                    const timeDifferenceInMilliseconds = arrivalTime - departureTime;

                    const hours = Math.floor(timeDifferenceInMilliseconds / (1000 * 60 * 60));
                    const minutes = Math.floor((timeDifferenceInMilliseconds % (1000 * 60 * 60)) / (1000 * 60));

                    return (
                        <AvailableTicketContainer>
                            <AvailableTicketContent key={item.id} direction={"row"}>
                                <AvailableTicketsRight direction={"row"}>
                                    <AvailableTicketsRightImg />
                                    <Box sx={{ margin: "-30px 0 0 0" }}>
                                        <Typography variant="h4">{item.startStation} - {item.endStation}</Typography>
                                        <Typography variant="h6">Duration: {hours} Hours {minutes} minutes</Typography>
                                    </Box>
                                </AvailableTicketsRight>
                                <AvailableTicketsLeft>
                                    <Box>
                                        <Typography variant="h4" sx={{ margin: "0 0 50px 0" }}>{item.price}/ticket</Typography>
                                        <Button variant="contained" sx={{ width: "40%" }}>
                                        Add
                                        </Button>
                                    </Box>
                                </AvailableTicketsLeft>
                            </AvailableTicketContent>
                        </AvailableTicketContainer>
                    )
                })
                setTripData(t)

            } catch (err) {
                console.log(err)
            }
        }
        fetchTripData()
    }, [searchBtn])

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
    const handelClickSearchBtn = () => {
        setSearchBtn(preData => ({
            ...preData,
            isActive: (preData.startDateKeyword == "") || (preData.endStartionKeyword == "")
                || (preData.startDateKeyword == "") ? false : true
        }))
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
                    <Button onClick={handelClickSearchBtn}>Search</Button>
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
