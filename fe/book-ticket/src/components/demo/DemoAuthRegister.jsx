import { Box, Button, TextField, Typography } from "@mui/material";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import authService from "../../services/authService";
import { signIn } from "../../store/slices/authSlice";

const initialForms = {
  field: {
    name: "",
    password: "",
    email: "",
    phone: "",
  },
  options: {
    name: { required: "This is required." },
    email: { required: "This is required." },
    password: { required: "This is required." },
    phone: { required: "This is required." },
  },
};
export default function DemoAuthRegister() {
  const dispatcher = useDispatch();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });

  const onSubmit = async (form) => {
    const response = await authService.register(form);
    if (!response.message) {
      dispatcher(signIn(response));
      dispatcher({ type: "FETCH_INFO" });
    } else {
      alert(response.message);
    }
  };
  return (
    <Box
      className="form signInForm"
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
    >
      {Object.keys(initialForms.field).map((item, index) => (
        <Box key={index}>
          <TextField
            size="small"
            label={item}
            {...register(item, {
              ...initialForms.options[item],
            })}
            fullWidth
            margin="normal"
            type={item}
          />

          <ErrorMessage
            errors={errors}
            name={item}
            render={({ message }) => (
              <Typography color="primary">{message}</Typography>
            )}
          />
        </Box>
      ))}
      <Button type="submit" fullWidth variant="contained" sx={{ mt: 2 }}>
        Sign in
      </Button>
    </Box>
  );
}
