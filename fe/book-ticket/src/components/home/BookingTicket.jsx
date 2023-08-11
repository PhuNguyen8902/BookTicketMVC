import {
  Autocomplete,
  Box,
  Button,
  TextField,
  Typography,
} from "@mui/material";
import {
  AvailableTicketContainer,
  AvailableTicketContent,
  TicketContainer,
  TiketCol,
} from "../../assets/styles/homePage";
import { useEffect, useState } from "react";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import moment from "moment";

export default function BookingTicket() {
  const [stationNameData, setStationNameData] = useState("");
  const [selectStationFromName, setSelectStationFromName] = useState("");
  const [selectStationToName, setSelectStationToName] = useState("");
  const [startDate, setStartDate] = useState("");

  const handleSelectStationFromName = (e, value) => {
    setSelectStationFromName(value);
  };

  const handleSelectStationToName = (e, value) => {
    setSelectStationToName(value);
  };

  const handleOnchangeStartDate = (value) => {
    setStartDate(value);
    console.log(value.$d);
    console.log(startDate);
  };

  useEffect(() => {
    async function fetchStationData() {
      try {
        const response = await fetch(
          "http://localhost:8080/backend/api/station"
        );
        const data = await response.json();
        const stationName = data.map((station) => station.name);
        setStationNameData(stationName);
      } catch (err) {
        console.log(err);
      }
    }
    fetchStationData();
  }, []);

  const [tripData, setTripData] = useState("");
  const [searchBtn, setSearchBtn] = useState({
    isActive: false,
  });

  const handelClickSearchBtn = () => {
    console.log(searchBtn);
    setSearchBtn((preData) => ({
      ...preData,
      isActive: !preData.isActive,
    }));
  };

  const [searchKeyword, setSearchKeyword] = useState("");
  useEffect(() => {
    async function fetchTripData() {
      try {
        const response = await fetch(
          `http://localhost:8080/backend/api/trip?kw=${searchKeyword}`
        );
        const data = await response.json();

        const t = data.map((item) => {
          const departureTime = item.departureTime;
          const date = new Date(departureTime * 1000);
          // console.log(Object.prototype.toString.call(item.departureTime))
          console.log(date.toUTCString());

          return (
            <Box key={item.id}>
              <Typography>
                {item.startStation}-{item.endStation}-{item.departureTime}
              </Typography>
            </Box>
          );
        });

        setTripData(t);
      } catch (err) {
        console.log(err);
      }
    }
    fetchTripData();
  }, []);

  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <TicketContainer>
        <TiketCol>
          <Autocomplete
            disableClearable
            disablePortal
            id="combo-box-demo"
            options={
              stationNameData.length &&
              stationNameData.filter(
                (item) =>
                  item != selectStationFromName && item != selectStationToName
              )
            }
            sx={{ width: 250 }}
            renderInput={(params) => <TextField {...params} label="Station" />}
            onChange={handleSelectStationFromName}
            value={selectStationFromName}
            className="searchStationNameFrom"
          />
          <Autocomplete
            disableClearable
            disablePortal
            id="combo-box-demo"
            options={
              stationNameData.length &&
              stationNameData.filter(
                (item) =>
                  item != selectStationFromName && item != selectStationToName
              )
            }
            sx={{ width: 250 }}
            renderInput={(params) => <TextField {...params} label="Station" />}
            onChange={handleSelectStationToName}
            value={selectStationToName}
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
          <DesktopDatePicker disabled />
        </TiketCol>
        <TiketCol>
          <Button onClick={handelClickSearchBtn}>Search</Button>
        </TiketCol>
      </TicketContainer>
      <AvailableTicketContainer>
        <AvailableTicketContent>
          {startDate && <p>{startDate.toISOString().split("T")[0]}</p>}
          {searchBtn.isActive && tripData}
        </AvailableTicketContent>
      </AvailableTicketContainer>
    </LocalizationProvider>
  );
}
