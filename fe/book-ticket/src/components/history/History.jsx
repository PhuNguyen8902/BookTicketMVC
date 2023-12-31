import {
  Box,
  Button,
  Checkbox,
  Container,
  Pagination,
  Stack,
  TextField,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { SERVER } from "../../assets/js/constants";
import tripService from "../../services/tripService";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import { DesktopDatePicker } from "@mui/lab";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DemoContainer, DemoItem } from "@mui/x-date-pickers/internals/demo";
import dayjs from "dayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { useRef } from "react";
import EachTicket from "./EachTicket";
import IsLoading from "../utils/IsLoading";

export default function History() {
  const nowDate = new Date();

  const [data, setData] = useState([]);
  const [startDateDisable, setStartDateDisable] = useState(false);
  const [bookDateDisable, setBookDateDisable] = useState(false);
  const [dataSearch, setDataSearch] = useState({
    isGet: "2",
    type: "2",
    startDate: dayjs(nowDate),
    bookDate: dayjs(nowDate),
    startStation: "",
    endStation: "",
    ticketId: "",
    payment: "ALL",
    page:1,
  });

  const user = useSelector((state) => state.auth.user);

  async function fetchData() {
    const queryParams = new URLSearchParams(window.location.search);
    const api = `${SERVER}ticket/history?${queryParams.toString()}`;
    const rs = await tripService.getTrip(api);
    setData(rs);
  }

const setPage=(page)=>{
  const queryParams = new URLSearchParams(window.location.search);
  if(page!=null){
  queryParams.set("page",page)

}else{
  queryParams.set("page",dataSearch.page)

}
  const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
  window.history.pushState({ path: newUrl }, "", newUrl);
  fetchData();

}

  useEffect(() => {
    const queryParams = new URLSearchParams(window.location.search);
    queryParams.set("user", user.id);
    const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
    window.history.pushState({ path: newUrl }, "", newUrl);
    setPage(null);
  }, []);

  const handleSearch = () => {
    console.log("vo roi");
    const queryParams = new URLSearchParams(window.location.search);
    if (dataSearch.isGet != "2") {
      queryParams.set("get", dataSearch.isGet);
    } else {
      queryParams.delete("get");
    }
    if (dataSearch.type != "2") {
      queryParams.set("type", dataSearch.type);
    } else {
      queryParams.delete("type");
    }
    if (dataSearch.payment != "ALL") {
      queryParams.set("payment", dataSearch.payment);
    } else {
      queryParams.delete("payment");
    }
    if (startDateDisable) {
      // const dateStartDate = new Date(dataSearch.startDate);
      // const longStartDate = dateStartDate.getTime();
      queryParams.set("startDate", dataSearch.startDate);
    } else {
      queryParams.delete("startDate");
    }
    if (bookDateDisable) {
      // const dateBookDate = new Date(dataSearch.bookDate);
      // const longBookDate = dateBookDate.getTime();
      queryParams.set("bookDate", dataSearch.bookDate);
    } else {
      queryParams.delete("bookDate");
    }
    if (dataSearch.startStation) {
      queryParams.set("startStation", dataSearch.startStation);
    } else {
      queryParams.delete("startStation");
    }
    if (dataSearch.endStation) {
      queryParams.set("endStation", dataSearch.endStation);
    } else {
      queryParams.delete("endStation");
    }
    if (dataSearch.ticketId) {
      queryParams.set("ticketId", dataSearch.ticketId);
    } else {
      queryParams.delete("ticketId");
    }
    const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
    window.history.pushState({ path: newUrl }, "", newUrl);

    fetchData();
  };

  const handleCheckboxChangeStartDate = () => {
    setStartDateDisable(!startDateDisable);
  };
  const handleCheckboxChangeBookDate = () => {
    setBookDateDisable(!bookDateDisable);
  };
  const handleRadioChange = (event) => {
    setDataSearch((prevData) => ({
      ...prevData,
      isGet: event.target.value,
    }));
  };
  const handleRadioActiveChange = (event) => {
    setDataSearch((prevData) => ({
      ...prevData,
      type: event.target.value,
    }));
  };
  const handleRadioPaymentChange = (event) => {
    setDataSearch((prevData) => ({
      ...prevData,
      payment: event.target.value,
    }));
  };
  const handleOnchangeStartDate = (value) => {
    let day = value.$D > 8 ? `${value.$D}` : `0${value.$D}`;
    let month = value.$M > 8 ? `${value.$M + 1}` : `0${value.$M + 1}`;
    let date = `${value.$y}-${month}-${day}`;

    setDataSearch((prevData) => ({
      ...prevData,
      startDate: date,
    }));
  };
  const handleOnchangeBookDate = (value) => {
    let day = value.$D > 8 ? `${value.$D}` : `0${value.$D}`;
    let month = value.$M > 8 ? `${value.$M + 1}` : `0${value.$M + 1}`;
    let date = `${value.$y}-${month}-${day}`;

    setDataSearch((prevData) => ({
      ...prevData,
      bookDate: date,
    }));
  };
  const handleStartStationChange = (event) => {
    setDataSearch((prevData) => ({
      ...prevData,
      startStation: event.target.value,
    }));
  };
  const handleEndStationChange = (event) => {
    setDataSearch((prevData) => ({
      ...prevData,
      endStation: event.target.value,
    }));
  };
  const handleTicketIdChange = (event) => {
    setDataSearch((prevData) => ({
      ...prevData,
      ticketId: event.target.value,
    }));
  };
  const handleChangePage = (event, value) => {
    setDataSearch((prevData) => ({
      ...prevData,
      page: value,
    }));
    setPage(value);
  };
  return (
    <>
      <Container>
        <Typography
          variant="h3"
          sx={{
            display: "flex",
            justifyContent: "center",
            padding: "2vw",
          }}
        >
          History
        </Typography>
        <Box>
          <FormControl>
            <FormLabel id="demo-radio-buttons-group-label">
              Get tickets
            </FormLabel>
            <RadioGroup
              aria-labelledby="demo-radio-buttons-group-label"
              // defaultValue="2"
              name="radio-buttons-group"
              value={dataSearch.isGet}
              onChange={handleRadioChange}
            >
              <FormControlLabel value="2" control={<Radio />} label="All" />
              <FormControlLabel
                value="1"
                control={<Radio />}
                label="Tickets received"
              />
              <FormControlLabel
                value="0"
                control={<Radio />}
                label="Not Received Tickets"
              />
            </RadioGroup>
          </FormControl>

          <FormControl sx={{ marginLeft: "5vw" }}>
            <FormLabel id="demo-radio-buttons-group-label">
              Type tickets
            </FormLabel>
            <RadioGroup
              aria-labelledby="demo-radio-buttons-group-label"
              // defaultValue="2"
              name="radio-buttons-group"
              value={dataSearch.type}
              onChange={handleRadioActiveChange}
            >
              <FormControlLabel value="2" control={<Radio />} label="All" />
              <FormControlLabel value="1" control={<Radio />} label="Adult" />
              <FormControlLabel
                value="0"
                control={<Radio />}
                label="Children"
              />
            </RadioGroup>
          </FormControl>

          <FormControl sx={{ marginLeft: "5vw" }}>
            <FormLabel id="demo-radio-buttons-group-label">Payment</FormLabel>
            <RadioGroup
              aria-labelledby="demo-radio-buttons-group-label"
              // defaultValue="2"
              name="radio-buttons-group"
              value={dataSearch.payment}
              onChange={handleRadioPaymentChange}
            >
              <FormControlLabel value="ALL" control={<Radio />} label="ALL" />
              <FormControlLabel value="MOMO" control={<Radio />} label="MOMO" />
              <FormControlLabel
                value="ZALOPAY"
                control={<Radio />}
                label="ZALOPAY"
              />
              <FormControlLabel
                value="COUNTER"
                control={<Radio />}
                label="COUNTER"
              />
            </RadioGroup>
          </FormControl>

          <FormControl sx={{ marginLeft: "5vw" }}>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Select Start Date"
                format="MM/DD/YYYY"
                defaultValue={nowDate}
                disabled={!startDateDisable}
                value={dayjs(dataSearch.startDate)}
                onChange={handleOnchangeStartDate}
              />
            </LocalizationProvider>
            <Checkbox onChange={handleCheckboxChangeStartDate} />
          </FormControl>
          <FormControl sx={{ marginTop: "2vw", marginLeft: "1vw" }}>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DatePicker
                label="Select Book Date"
                format="MM/DD/YYYY"
                defaultValue={dayjs("2022-04-17")}
                disabled={!bookDateDisable}
                value={dayjs(dataSearch.bookDate)}
                onChange={handleOnchangeBookDate}
              />
            </LocalizationProvider>
            <Checkbox onChange={handleCheckboxChangeBookDate} />
          </FormControl>
          <FormControl sx={{ marginTop: "2vw", marginLeft: "5vw" }}>
            <TextField
              placeholder="Start Station"
              value={dataSearch.startStation}
              onChange={handleStartStationChange}
            />
          </FormControl>
          <FormControl sx={{ marginTop: "2vw", marginLeft: "5vw" }}>
            <TextField
              placeholder="End Station"
              value={dataSearch.endStation}
              onChange={handleEndStationChange}
            />
          </FormControl>
          <FormControl sx={{ marginTop: "2vw", marginLeft: "5vw" }}>
            <TextField
              placeholder="Ticket Id"
              value={dataSearch.ticketId}
              onChange={handleTicketIdChange}
            />
          </FormControl>
        </Box>
        <Box>
          <Button
            variant="contained"
            className="history__button__search "
            onClick={handleSearch}
          >
            Search
          </Button>
        </Box>
        <Stack spacing={2} mt={2} mb={10} alignItems="center" direction={"column"}>
          {data.map((ticket, index) => (
            <Stack key={index} spacing={2} direction="row" alignItems="center">
              <EachTicket data={ticket} />
            </Stack>
          ))}
          {data.length >0?
            <Stack spacing={2}>
            <Pagination count={data[0].totalPage} page={dataSearch.page} onChange={handleChangePage} />
          </Stack>:
          <IsLoading/>
        }
        
        </Stack>
        
      </Container>
    </>
  );
}
