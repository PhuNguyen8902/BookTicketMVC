import { useSelector } from "react-redux";
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
import { ErrorMessage } from "@hookform/error-message";

export default function TripInformation({ dt, increase }) {
  const [option, setOption] = useState([]);
  const [value, setValue] = useState("");
  const [ticNum, setTicNum] = useState(1);
  const [payment, setPayment] = useState("Pay at the counter");

  const listNum = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const listPayment = ["Momo", "ZaloPay", "Pay at the counter"];

  //   console.log(increase);
  let price = parseFloat(dt.price) * ticNum;

  let newPrice = price + increase.increasedPercentage;

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
      TripId: dt.id,
      Price: price,
      UserId: user.id,
      IncreasePrice: "",
      Type: "onl",
      Date: now,
      Payment: "",
      Seat: "",
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

  const onSubmit = async (form) => {
    form.IncreasePrice = increase.id;
    form.Payment = payment;
    if (value) {
      form.Seat = value;
    } else {
      setError("Seat", { message: "This is a required" });
    }
    console.log(form);
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
      <ErrorMessage
        errors={errors}
        name="Seat"
        render={({ message }) => <Typography color="red">{message}</Typography>}
      />
      <Autocomplete
        value={ticNum}
        onChange={(event, newValue) => {
          setTicNum(newValue);
        }}
        id="controllable-states-demo"
        options={listNum}
        getOptionLabel={(option) => option.toString()}
        isOptionEqualToValue={(option, value) => option === value}
        fullWidth
        renderInput={(params) => (
          <TextField {...params} label="Number of tickets" />
        )}
        sx={{ mt: 2 }}
      />
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
