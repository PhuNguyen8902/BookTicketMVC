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
  AvailableTicketsContainer,
  AvailableTicketsContent,
  AvailableTicketsLeft,
  AvailableTicketsRight,
  AvailableTicketsRightImg,
  TicketContainer,
  TiketCol,
} from "../../assets/styles/homePage";
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
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import { DatePicker } from "@mui/x-date-pickers";

export default function BookingTicket() {
  const [stationNameData, setStationNameData] = useState([]);
  const [date, setDate] = useState(null);

  useEffect(() => {
    async function fetchStationData() {
      const rs = await stationService.getStation(`${SERVER}station/name`);
      if (!rs.message) {
        setStationNameData(rs);
      } else {
        alert("Error");
      }
    }
    fetchStationData();
  }, []);

  const initialForms = {
    field: {
      startStation: "",
      endStation: "",
      startDate: "",
    },

    options: {
      startStation: { required: "This is required." },
      endStation: { required: "This is required." },
      startDate: { required: "This is required." },
    },
    type: {
      startStation: "comboBox",
      endStation: "comboBox",
      startDate: "date",
    },
  };
  const {
    register,
    setError,
    handleSubmit,
    formState: { errors },
    control,
  } = useForm({
    defaultValues: initialForms.field,
  });

  const handleOnchangeStartDate = (value) => {
    // Xu li khi ma thang hoac ngay nho hon 8 thi them so 0
    let day = value.$D > 8 ? `${value.$D}` : `0${value.$D}`;
    let month = value.$M > 8 ? `${value.$M + 1}` : `0${value.$M + 1}`;
    let date = `${value.$y}-${month}-${day}`;
    setDate(date);
  };

  // xu li chuyen kw len url
  const handelClickSearchBtn = (
    searchStaionFrom,
    searchStationTo,
    searchDateFrom
  ) => {
    const queryParams = new URLSearchParams(window.location.search); // search params
    if (
      searchStaionFrom !== "" &&
      searchStationTo !== "" &&
      searchDateFrom !== ""
    ) {
      queryParams.set("startStation", searchStaionFrom);
      queryParams.set("endStation", searchStationTo);
      queryParams.set("startDate", searchDateFrom);
    } else {
      queryParams.delete("startStation");
      queryParams.delete("endStation");
      queryParams.delete("startDate");
    }
    const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
    window.history.pushState({ path: newUrl }, "", newUrl);
    // console.log(newUrl);
    fetch();
  };

  const [trip, setTrip] = useState(null);

  const [addOpen, setAddOpen] = useState(null);
  const handleOpen = (item) => {
    const departureTime = new Date(item.departureTime);
    const startTime = departureTime.getTime();
    const now = new Date();
    now.setHours(now.getHours() + 2);
    const nowTime = now.getTime();
    if (nowTime <= startTime) {
      setAddOpen(true);
    } else {
      alert("Departure time must be greater than Now Time");
    }
  };

  const handleClose = () => {
    setAddOpen(false);
  };

  const fetch = async () => {
    const queryParams = new URLSearchParams(window.location.search); // search params
    const api = `${SERVER}trip?${queryParams.toString()}`;
    const rs = await tripService.getTrip(api);
    if (!rs.message) {
      setTrip(rs);
    } else {
      alert("error");
    }
  };
  const isLogin = useSelector((state) => state.auth.isLogin);
  // submit
  const onSubmit = async (form) => {
    let check = true;
    // kiem tra 2 staion = nhau hay ko
    if (form.startStation == form.endStation) {
      setError("endStation", { message: "khong duoc chon trung" });
      check = false;
    }
    // xet date xem da duoc chon hay chua
    if (date == null) {
      // setError("startDate", { message: "This is required.dfd" })
      alert("cut");
      check = false;
    }

    if (check == false) return;

    form.startDate = date;

    handelClickSearchBtn(form.startStation, form.endStation, form.startDate);
  };
  return (
    <>
      <TicketContainer component={"form"} onSubmit={handleSubmit(onSubmit)}>
        <TiketCol>
          <Autocomplete
            options={stationNameData}
            getOptionLabel={(option) => option.name}
            sx={{ width: "250px" }}
            isOptionEqualToValue={(option, value) =>
              option.name === value.name && option.id === value.id
            }
            renderInput={(params) => (
              <TextField
                {...params}
                label={"startStation"}
                {...register("startStation", {
                  ...initialForms.options["startStation"],
                })}
              />
            )}
          />
          <Autocomplete
            options={stationNameData}
            getOptionLabel={(option) => option.name}
            sx={{ width: "250px" }}
            isOptionEqualToValue={(option, value) =>
              option.name === value.name && option.id === value.id
            }
            renderInput={(params) => (
              <TextField
                {...params}
                label={"endStation"}
                {...register("endStation", {
                  ...initialForms.options["endStation"],
                })}
              />
            )}
          />
        </TiketCol>
        <TiketCol>
          <ErrorMessage
            errors={errors}
            name={"startStation"}
            render={({ message }) => (
              <Typography color="red">{message}</Typography>
            )}
          />
          <ErrorMessage
            errors={errors}
            name={"endStation"}
            render={({ message }) => (
              <Typography color="red">{message}</Typography>
            )}
          />
        </TiketCol>
        <TiketCol>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker
              label="Select Start Date"
              format="MM/DD/YYYY"
              value={date}
              onChange={handleOnchangeStartDate}
            />
            <DesktopDatePicker disabled />
          </LocalizationProvider>
        </TiketCol>
        <TiketCol>
          <ErrorMessage
            errors={errors}
            name={"startDate"}
            render={({ message }) => (
              <Typography color="red">{message}</Typography>
            )}
          />
        </TiketCol>
        <TiketCol>
          <Button type="submit">Search</Button>
        </TiketCol>
      </TicketContainer>
      <AvailableTicketsContainer>
        <AvailableTicketsContent>
          {trip != null
            ? trip.map((item, index) => {
                // tinh khoang thoi gian
                const arrivalTime = new Date(item.arrivalTime);
                const departureTime = new Date(item.departureTime);
                const timeDifferenceInMilliseconds =
                  arrivalTime - departureTime;

                const hours = Math.floor(
                  timeDifferenceInMilliseconds / (1000 * 60 * 60)
                );
                const minutes = Math.floor(
                  (timeDifferenceInMilliseconds % (1000 * 60 * 60)) /
                    (1000 * 60)
                );

                return (
                  <AvailableTicketContainer key={index}>
                    <AvailableTicketContent direction={"row"}>
                      <AvailableTicketsRight direction={"row"}>
                        <AvailableTicketsRightImg />
                        <Box sx={{ margin: "-30px 0 0 0" }}>
                          <Typography variant="h4">
                            {item.startStation} - {item.endStation}
                          </Typography>
                          <Typography variant="h6">
                            Duration: {hours} Hours {minutes} minutes
                          </Typography>
                        </Box>
                      </AvailableTicketsRight>
                      <AvailableTicketsLeft>
                        <Box>
                          <Typography
                            variant="h4"
                            sx={{ margin: "0 0 50px 0" }}
                          >
                            {item.price}/ticket
                          </Typography>
                          <Button
                            onClick={() => handleOpen(item)}
                            variant="contained"
                            sx={{ width: "40%" }}
                          >
                            Add
                          </Button>
                          {!isLogin ? (
                            <DialogAlertError
                              onOpen={addOpen}
                              onClose={handleClose}
                              mess={"Please login account first!"}
                            />
                          ) : // trip != null ?
                          addOpen ? (
                            <DialogAddTicket
                              onOpen={addOpen}
                              onClose={handleClose}
                              dt={item}
                            />
                          ) : null}
                        </Box>
                      </AvailableTicketsLeft>
                    </AvailableTicketContent>
                  </AvailableTicketContainer>
                );
              })
            : null}
        </AvailableTicketsContent>
      </AvailableTicketsContainer>
    </>
  );
}
