import { Autocomplete, Box, Button, TextField } from "@mui/material";
import { format } from "date-fns";
import { SERVER } from "../../assets/js/constants";
import { useEffect } from "react";
import { useState } from "react";
import increasePriceService from "../../services/increasePriceService";

export default function EditTicketBody({ data }) {
  const [value, setValue] = useState(data.seat);
  const [option, setOption] = useState([]);
  const [valueTrip, setValueTrip] = useState(data.tripId);
  const [optionTrip, setOptionTrip] = useState([]);

  const startDate = new Date(data.departureTime);
  const formatStartDate = format(startDate, "yyyy-MM-dd");
  const bookDate = new Date(data.bookTime);
  const formaBookDate = format(bookDate, "yyyy-MM-dd");

  async function fetchExcludedNumbers() {
    const rs = await increasePriceService.getIncreasePrice(
      `${SERVER}ticket/checkSeat/${data.tripId}`
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
    console.log(rs);
    // setOptionTrip(rs);
  }
  useEffect(() => {
    fetchExcludedNumbers();
    fetchExcludedTrip();
  }, []);
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
          value={data.price}
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
              : data.payment == "Pay at the counter"
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
          onChange={(event, newValue) => {
            setValueTrip(newValue);
          }}
          id="controllable-states-demo"
          options={optionTrip}
          getOptionLabel={(optionTrip) => optionTrip.toString()}
          isOptionEqualToValue={(optionTrip, value) => optionTrip === value}
          fullWidth
          renderInput={(params) => (
            <TextField {...params} label="Change Trip" />
          )}
          sx={{ mt: 2 }}
        />
        <Button type="submit" fullWidth variant="contained" sx={{ mt: 2 }}>
          Ok
        </Button>
      </Box>
    </>
  );
}
