import { Box, Button, TextField, Typography } from "@mui/material";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
// import authService from "../../services/authService";
import authService from "../../service/authService";
import { signIn } from "../../store/slices/authSlice";
import { Navigate } from "react-router-dom";
// import { closePopup } from "../../store/slices/pageSlice";

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
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });
  //   const handleClose = () => {
  //     dispatcher(closePopup());
  //   };
  const onSubmit = async (form) => {
    const response = await authService.sigIn(form);
    if (!response.message) {
      dispatcher(signIn(response));
      dispatcher({ type: "FETCH_INFO" });
      //   <Navigate to={"/Admin/"} />;
      //   handleClose();
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
