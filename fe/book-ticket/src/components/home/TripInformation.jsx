import { useDispatch, useSelector } from "react-redux";
import { SERVER } from "../../assets/js/constants";
import { useEffect } from "react";
import increasePriceService from "../../services/increasePriceService";
import { useForm } from "react-hook-form";
import {
  Autocomplete,
  Box,
  Button,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";
import ticketService from "../../services/ticketService";
import momoService from "../../services/momoService";
import zaloPayService from "../../services/zaloPayService";

export default function TripInformation({ dt, increase, close }) {
  console.log(dt);
  const [option, setOption] = useState([]);
  const [value, setValue] = useState("");
  const [payment, setPayment] = useState("COUNTER");
  const [ticType, setTicType] = useState("Adult");

  const listPayment = ["MOMO", "ZALOPAY", "COUNTER"];
  const listTicType = ["Adult", "Children - 50%"];

  //   console.log(increase);
  let price = parseFloat(dt.price);

  let newPrice = price + (price * increase.increasedPercentage) / 100;

  let now = new Date();
  now = now.getTime();
  //   console.log(dt);
  const user = useSelector((state) => state.auth.user);

  async function fetchExcludedNumbers() {
    const rs = await increasePriceService.getIncreasePrice(
      `${SERVER}ticket/checkSeat/${dt.id}`
    );
    const options = Array.from(
      { length: dt.seatCapacity },
      (_, index) => index + 1
    )
      .filter((number) => !rs.includes(number))
      .map((number) => `${number}`);

    setOption(options);
  }
  useEffect(() => {
    fetchExcludedNumbers();
  }, []);

  const initialForms = {
    field: {
      tripId: dt.id,
      price: dt.price,
      userId: user.id,
      increasePrice: "",
      type: "onl",
      date: now,
      payment: "",
      seat: "",
      ticType: 0,
    },
  };
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });
  async function addTicketCounter(form) {
    const rs = await ticketService.addTicket(form);
    alert(rs.suscess);
    close();
  }
  async function addTicketMomo(form) {
    const response = await momoService.postMomoQr(form);
    localStorage.setItem("momo", JSON.stringify(response));
    localStorage.setItem("ticket", JSON.stringify(form));

    window.location.href = response.payUrl;
  }
  async function addTicketZalo(form) {
    const response = await zaloPayService.postZaloQr(form);
    console.log(response);
    // localStorage.setItem("zalo", JSON.stringify(response));
    localStorage.setItem("ticket", JSON.stringify(form));

    window.location.href = response.orderurl;
  }

  const onSubmit = async (form) => {
    form.increasePrice = increase.id;
    form.payment = payment;
    form.price = newPrice;
    if (ticType == "Adult") {
      form.ticType = 1;
    }
    if (!value) {
      // setError("seat", { message: "This is a required" });
      alert("Seat is a required");
    } else {
      form.seat = value;

      if (payment == "COUNTER") {
        addTicketCounter(form);
      } else if (payment == "MOMO") {
        addTicketMomo(form);
      } else if (payment == "ZALOPAY") {
        addTicketZalo(form);
      }
      console.log(form);
    }
  };
  return (
    <Box
      className="form signInForm"
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
    >
      <TextField
        size="small"
        fullWidth
        margin="normal"
        label="Start Station"
        value={dt.startStation}
        InputProps={{
          readOnly: true,
        }}
      />
      <TextField
        size="small"
        fullWidth
        margin="normal"
        label="End Station"
        value={dt.endStation}
        InputProps={{
          readOnly: true,
        }}
      />
      <TextField
        size="small"
        fullWidth
        margin="normal"
        label="Start Time"
        value={dt.departureTime}
        InputProps={{
          readOnly: true,
        }}
      />
      <TextField
        size="small"
        fullWidth
        margin="normal"
        label="Price"
        value={price}
        InputProps={{
          readOnly: true,
        }}
      />
      <TextField
        size="small"
        fullWidth
        margin="normal"
        label="Surcharge"
        disabled={increase.increasedPercentage === 0}
        value={
          increase.eventName +
          " surcharge " +
          increase.increasedPercentage +
          "%"
        }
        InputProps={{
          readOnly: true,
        }}
      />
      <Autocomplete
        value={ticType}
        onChange={(event, newValue) => {
          setTicType(newValue);
        }}
        id="controllable-states-demo"
        options={listTicType}
        getOptionLabel={(option) => option.toString()}
        isOptionEqualToValue={(option, value) => option === value}
        fullWidth
        renderInput={(params) => <TextField {...params} label="Type" />}
        sx={{ mt: 2 }}
      />
      <TextField
        size="small"
        fullWidth
        margin="normal"
        label="New Price"
        value={newPrice}
        InputProps={{
          readOnly: true,
        }}
      />
      <Autocomplete
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);
        }}
        id="controllable-states-demo"
        options={option}
        getOptionLabel={(option) => option.toString()}
        isOptionEqualToValue={(option, value) => option === value}
        fullWidth
        renderInput={(params) => <TextField {...params} label="Seat" />}
        sx={{ mt: 2 }}
      />
      {/* <ErrorMessage
        errors={errors}
        name="seat"
        render={({ message }) => <Typography color="red">{message}</Typography>}
      /> */}
      <Autocomplete
        value={payment}
        onChange={(event, newValue) => {
          setPayment(newValue);
        }}
        id="controllable-states-demo"
        options={listPayment}
        getOptionLabel={(option) => option.toString()}
        isOptionEqualToValue={(option, value) => option === value}
        fullWidth
        renderInput={(params) => <TextField {...params} label="Payment" />}
        sx={{ mt: 2 }}
      />
      <Button type="submit" fullWidth variant="contained" sx={{ mt: 2 }}>
        Ok
      </Button>
    </Box>
  );
}
