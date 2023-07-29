import { Autocomplete, TextField, Typography } from "@mui/material";
import { AvailableTicketContainer, AvailableTicketContent, TicketContainer, TiketCol } from "../../assets/styles/homePage";
import { useEffect, useState } from "react";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";


export default function BookingTicket() {

    const top100Films = [
        { id: 1, label: 'The Shawshank Redemption', year: 1994 },
        { id: 2, label: 'The Godfather', year: 1972 },
        { id: 3, label: 'The Godfather: Part II', year: 1974 },
        { id: 4, label: 'The Dark Knight', year: 2008 },
        { id: 5, label: '12 Angry Men', year: 1957 },
    ]

    const routes = [
        { id: 1, stationFrom: 'The Shawshank Redemption', stationTo: 'The Godfather' },
        { id: 1, stationFrom: 'The Dark Knight', stationTo: 'The Godfather' },
        { id: 1, stationFrom: 'The Shawshank Redemption', stationTo: '12 Angry Men' },
        { id: 1, stationFrom: 'The Dark Knight', stationTo: 'The Shawshank Redemption' },
    ]



    const [isSeach, setIsSeach] = useState(false)
    const [startDate, setStartDate] = useState(null)
    const [seachStationFrom, setSeachStationFrom] = useState("")
    const [seachStationTo, setSeachStationTo] = useState("")

    const stationRoutes = routes.filter((route) =>{
        return route.stationFrom === seachStationFrom && route.stationTo === seachStationTo;
    })
    
    const printStationRoutesDefault = routes.map(route => {
        return(
            <Typography>{route.stationFrom} - {route.stationTo}</Typography>
        )
    })

    const printStationRoutes = stationRoutes.map(route => {
        return(
            <Typography>{route.stationFrom} - {route.stationTo}</Typography>
        )
    })

    const handleOnchangeStationFrom = (e, value) =>{
        setSeachStationFrom(value.label)
        setIsSeach(true)
    }

    const handleOnchangeStationTo = (e, value) =>{
        setSeachStationTo(value.label)
        setIsSeach(true)
    }

    const handleOnchangeStartDate = (date) => {
        setStartDate(date)
    }

    
    // console.log("Date: ", startDate)
    // const stations = top100Films.filter((station) => station.label.includes(seachStationFrom)).map(station => {
    //     return (
    //         <Typography>{station.label}</Typography>
    //     )
    // })


    return (
        <LocalizationProvider dateAdapter={AdapterDayjs}>
            <TicketContainer>
                <TiketCol>
                    <Autocomplete
                        disableClearable
                        disablePortal
                        id="combo-box-demo"
                        options={top100Films}
                        sx={{ width: 250 }}
                        renderInput={(params) => <TextField {...params} label="Station" />}
                        onChange={handleOnchangeStationFrom}
                        value={seachStationFrom}
                        className="searchStationNameFrom"
                    />
                    <Autocomplete
                        disableClearable
                        disablePortal
                        id="combo-box-demo"
                        options={top100Films}
                        sx={{ width: 250 }}
                        renderInput={(params) => <TextField {...params} label="Station" />}
                        onChange={handleOnchangeStationTo}
                        value={seachStationTo}
                        className="searchStationNameTo"
                    />
                </TiketCol>
                <TiketCol>
                    <DesktopDatePicker 
                        label="Select Start Date"
                        format="MM/DD/YYYY"
                        value={startDate}
                        onChange={handleOnchangeStartDate}
                    />
                    <DesktopDatePicker 
                    disabled
                    />
                </TiketCol>
            </TicketContainer>
            <AvailableTicketContainer>
                <AvailableTicketContent>
                    {isSeach ? printStationRoutes : printStationRoutesDefault}
                </AvailableTicketContent>
            </AvailableTicketContainer>
        </LocalizationProvider>
    )
}