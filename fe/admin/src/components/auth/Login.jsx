import { Box, Button, TextField, Typography } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import authService from "../../service/authService";
import { close403, signIn } from "../../store/slices/authSlice";
import { Navigate } from "react-router-dom";

const initialForms = {
  field: {
    email: "",
    password: "",
  },
  options: {
    email: { required: "This is required." },
    password: { required: "This is required." },
  },
};
export default function Login() {
  const dispatcher = useDispatch();

  const {
    register,
    setError,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });
  const is403 = useSelector((state) => state.auth.is403);
  if (is403) {
    setError("password", { message: "Bạn không có quyền vào đây" });
    dispatcher(close403());
    localStorage.removeItem("url");
  }

  const onSubmit = async (form) => {
    const response = await authService.sigIn(form);
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
            type={
              item === "email"
                ? "email"
                : item === "password"
                ? "password"
                : "text"
            }
          />

          <ErrorMessage
            errors={errors}
            name={item}
            render={({ message }) => (
              <Typography color="red">{message}</Typography>
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
