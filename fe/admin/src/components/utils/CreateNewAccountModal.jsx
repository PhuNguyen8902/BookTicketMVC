import {
  Box,
  Button,
  TextField,
  Typography,
  Autocomplete,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Stack,
} from "@mui/material";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import { useState } from "react";
import { useEffect } from "react";
import stationService from "../../service/stationService";
import routeService from "../../service/routeService";

export default function CreateNewAccountModal({
  open,
  columns,
  onClose,
  //   onSubmit,
}) {
  const initialForms = {
    field: {},
    options: {},
    type: {},
    header: {},
  };
  columns.forEach((column) => {
    if (column.type != "disable") {
      initialForms.field[column.accessorKey] = "";
      initialForms.type[column.accessorKey] = column.type;
      initialForms.header[column.accessorKey] = column.header;

      initialForms.options[column.accessorKey] = {
        required: "This is required.",
      };
    }
  });
  const dispatcher = useDispatch();

  const {
    register,
    setError,
    setValue,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });

  const [nameStation, setNameStation] = useState([]);

  useEffect(() => {
    const fetchNameStation = async () => {
      try {
        const result = await stationService.getNameStation();
        let arr = [];
        result.forEach((rs) => {
          arr.push({ name: rs.name, id: rs.id });
        });

        setNameStation(arr);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchNameStation();
  }, []);

  const handleClose = () => {
    onClose();
  };
  const onSubmit = async (form) => {
    nameStation.forEach((station) => {
      if (station.name == form.endStation) {
        form.endStation = station.id;
      }
      if (station.name == form.startStation) {
        form.startStation = station.id;
      }
    });
    const response = await routeService.addRoute(form);
    if (!response.message) {
      handleClose();
    } else {
      setError(response.name, { message: response.message });
    }
  };
  return (
    <Dialog open={open} component={"form"} onSubmit={handleSubmit(onSubmit)}>
      <DialogTitle textAlign="center">Create New Route</DialogTitle>
      <DialogContent>
        <Stack
          sx={{
            width: "100%",
            minWidth: { xs: "300px", sm: "360px", md: "400px" },
            gap: "1.5rem",
          }}
        >
          {Object.keys(initialForms.field).map((item, index) => (
            <Box key={index}>
              {initialForms.type[item] === "comboBox" ? (
                <Autocomplete
                  options={nameStation}
                  getOptionLabel={(option) => option.name}
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label={initialForms.header[item]}
                      {...register(item, {
                        ...initialForms.options[item],
                      })}
                    />
                  )}
                />
              ) : (
                <TextField
                  label={initialForms.header[item]}
                  name={item}
                  style={{
                    display: "flex",
                  }}
                  {...register(item, {
                    ...initialForms.options[item],
                  })}
                />
              )}
              <ErrorMessage
                errors={errors}
                name={item}
                render={({ message }) => (
                  <Typography color="red">{message}</Typography>
                )}
              />
            </Box>
          ))}
        </Stack>
      </DialogContent>
      <DialogActions sx={{ p: "1.25rem" }}>
        <Button onClick={onClose}>Cancel</Button>
        <Button type="submit" fullWidth variant="contained">
          Create New Route
        </Button>
      </DialogActions>
    </Dialog>
  );
}
