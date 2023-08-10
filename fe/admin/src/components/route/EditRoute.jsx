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
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import routeService from "../../service/routeService";
export default function EditRoute({ form, open, onClose, nameStation }) {
  const initialForms = {
    field: {
      id: form.id,
      name: form.name,
      startStation: form.startStation,
      endStation: form.endStation,
      distance: form.distance,
      duration: form.duration,
    },

    options: {
      name: { required: "This is required." },
      startStation: { required: "This is required." },
      endStation: { required: "This is required." },
      distance: { required: "This is required." },
      duration: { required: "This is required." },
    },
    type: {
      id: "disable",
      startStation: "comboBox",
      endStation: "comboBox",
    },
  };

  const {
    register,
    setError,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });

  const onSubmit = async (form) => {
    nameStation.forEach((station) => {
      if (station.name == form.endStation) {
        form.endStation = station.id;
      }
      if (station.name == form.startStation) {
        form.startStation = station.id;
      }
    });
    const response = await routeService.editRoute(form);
    if (!response.message) {
      onClose();
    } else {
      setError(response.name, { message: response.message });
    }
  };
  return (
    <Dialog open={open} component={"form"} onSubmit={handleSubmit(onSubmit)}>
      <DialogTitle textAlign="center">Edit Route</DialogTitle>
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
                  defaultValue={nameStation.find(
                    (option) => option.name === initialForms.field[item]
                  )}
                  isOptionEqualToValue={(option, value) =>
                    option.name === value.name && option.id === value.id
                  }
                  renderInput={(params) => (
                    <TextField
                      {...params}
                      label={item}
                      {...register(item, {
                        ...initialForms.options[item],
                      })}
                    />
                  )}
                />
              ) : (
                <TextField
                  label={item}
                  name={item}
                  style={{
                    display:
                      initialForms.type[item] === "disable" ? "none" : "flex",
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
          Edit Route
        </Button>
      </DialogActions>
    </Dialog>
  );
}
