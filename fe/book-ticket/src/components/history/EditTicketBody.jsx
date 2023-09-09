import { Autocomplete, Box, Button, TextField } from "@mui/material";
import { format } from "date-fns";
import { SERVER } from "../../assets/js/constants";
import { useEffect } from "react";
import { useState } from "react";
import increasePriceService from "../../services/increasePriceService";
import ticketService from "../../services/ticketService";

export default function EditTicketBody({ data }) {
  const [value, setValue] = useState(data.seat);
  const [option, setOption] = useState([]);
  const [valueTrip, setValueTrip] = useState(data.tripId);
  const [optionTrip, setOptionTrip] = useState([]);

  const startDate = new Date(data.departureTime);
  let formatStartDate = format(startDate, "yyyy-MM-dd HH:mm:SS");
  const bookDate = new Date(data.bookTime);
  const formaBookDate = format(bookDate, "yyyy-MM-dd HH:mm:SS");

  let price = data.price;

  async function fetchExcludedNumbers(id) {
    const rs = await increasePriceService.getIncreasePrice(
      `${SERVER}ticket/checkSeat/${id}`
    );
    const options = Array.from(
      { length: data.seatCapacity },
      (_, index) => index + 1
    )
      .filter((number) => !rs.includes(number))
      .map((number) => `${number}`);

    options.push(data.seat);

    setOption(options);
  }
  async function fetchExcludedTrip() {
    const rs = await increasePriceService.getIncreasePrice(
      `${SERVER}trip/get/${data.routeId}`
    );
    setOptionTrip(rs);
  }
  useEffect(() => {
    fetchExcludedNumbers(data.tripId);
    fetchExcludedTrip();
  }, []);
  useEffect(() => {
    fetchExcludedNumbers(valueTrip.id);
  }, [valueTrip]);
  const handleSubmit = () => {
    if (value != data.seat || valueTrip.id != data.tripId) {
      let price2 =
        parseInt(valueTrip.price, 10) +
        (parseInt(valueTrip.price, 10) * data.increasedPercentage) / 100;
      let newPrice2 = price2;
      if (data.type == 0) {
        newPrice2 = newPrice2 / 2;
      }
      let time = new Date(valueTrip.departureTime);
      time = time.getTime();
      if (valueTrip.id === undefined) {
        alert("Please choose the trip");
      } else {
        const changeForm = {
          seat: value,
          orderId: data.orderId,
          ticketId: data.ticketId,
          tripId: valueTrip.id,
          price: price2,
          newPrice: newPrice2,
          departureTime: time,
        };
        console.log(changeForm);
        fetchChangeTicket(changeForm);
      }
    }
  };
  async function fetchChangeTicket(form) {
    const rs = await ticketService.changeTicket(form);
    if (rs) {
      alert(rs.suscess);
    } else {
      alert(rs.message);
    }
  }
  return (
    <>
      <Box>
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Name"
          value={data.userName}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Start Station"
          value={data.startStation}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="End Station"
          value={data.endStation}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="License Plate"
          value={data.licensePlate}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Start Time"
          value={formatStartDate}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Book Time"
          value={formaBookDate}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Type"
          value={data.type == 1 ? "Adult" : "Children"}
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Payment"
          value={data.payment}
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
          disabled={data.increasedPercentage === 0}
          value={
            data.eventName + " surcharge " + data.increasedPercentage + "%"
          }
          InputProps={{
            readOnly: true,
          }}
        />
        <TextField
          size="small"
          fullWidth
          margin="normal"
          label="Receive"
          value={
            data.isGet == 1
              ? "Reveived"
              : data.payment == "COUNTER"
              ? "Not received + Unpaid"
              : "Not received + Paid"
          }
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
        <Autocomplete
          value={valueTrip}
          defaultValue={data.tripId}
          onChange={(event, newValue) => {
            setValueTrip(newValue);
          }}
          id="controllable-states-demo"
          options={optionTrip}
          getOptionLabel={(option) => (option.id ? option.id.toString() : "")}
          renderOption={(props, option) => (
            <Box
              component="li"
              sx={{ "& > img": { mr: 2, flexShrink: 0 } }}
              {...props}
            >
              {option.departureTime} + {option.price}+VND
            </Box>
          )}
          isOptionEqualToValue={(option, value) => option === value}
          fullWidth
          renderInput={(params) => (
            <TextField {...params} label="Change Trip" />
          )}
          sx={{ mt: 2 }}
        />
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 2 }}
          onClick={handleSubmit}
        >
          Ok
        </Button>
      </Box>
    </>
  );
}
