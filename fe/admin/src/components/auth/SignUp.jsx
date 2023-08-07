import {
  Autocomplete,
  Avatar,
  Box,
  Button,
  TextField,
  Typography,
} from "@mui/material";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import authService from "../../service/authService";
import { signIn } from "../../store/slices/authSlice";
import { useState } from "react";
import pictureService from "../../service/pictureService";

const initialForms = {
  field: {
    avatar: "",
    name: "",
    password: "",
    email: "",
    phone: "",
    role: "",
  },
  options: {
    name: { required: "This is required." },
    email: { required: "This is required." },
    password: { required: "This is required." },
    phone: { required: "This is required." },
  },
};
export default function SignUp() {
  const [selectedRole, setSelectedRole] = useState(null);
  const roleOptions = [
    { value: "ROLE_EMPLOYEE", label: "Employee" },
    { value: "ROLE_CUSTOMER", label: "Customer" },
  ];
  const dispatcher = useDispatch();
  const [imageSrc, setImageSrc] = useState("");
  const [imageUrl, setImageUrl] = useState("");

  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });

  const handleFileChange = async (event) => {
    const file = event.target.files[0];
    if (file && file.type.startsWith("image/")) {
      try {
        let response = null;
        const newForm = new FormData();
        newForm.append("file", file);
        response = await pictureService.postPicture(newForm);
        if (response != null) {
          if (!response.message) {
            const reader = new FileReader();
            reader.onload = function (event) {
              setImageSrc(event.target.result);
            };
            reader.readAsDataURL(file);
            setImageUrl(response.url);
          } else {
            alert(response.message);
          }
        }
      } catch (error) {
        console.error(error);
        alert("Có lỗi xảy ra khi tải lên hình ảnh.");
      }
    } else {
      alert("Vui lòng chọn một tệp tin hình ảnh!");
    }
  };

  const onSubmit = async (form) => {
    if (imageUrl === "") {
      setError("avatar", { message: "Vui lòng tải ảnh lên" });
    } else {
      form.role = selectedRole?.value || "";
      form.avatar = imageUrl;
      const response = await authService.register(form);
      if (!response.message) {
        alert("thanh cong");
      } else {
        setError(response.name, { message: response.message });
      }
    }
  };

  return (
    <Box
      className="form signInForm"
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
    >
      <Button
        component="label"
        sx={{
          display: "inline-flex",
          height: "20vh",
          width: "20vw",
          marginLeft: "10%",
        }}
      >
        <Avatar
          alt="Remy Sharp"
          src={imageSrc}
          variant="square"
          sx={{ width: "100%", height: "100%" }}
        />
        <input
          type="file"
          accept="image/*"
          style={{ display: "none" }}
          onChange={handleFileChange}
        />
      </Button>
      {Object.keys(initialForms.field).map((item, index) => (
        <Box key={index}>
          {item != "role" ? (
            <TextField
              size="small"
              label={item}
              {...register(item, {
                ...initialForms.options[item],
              })}
              fullWidth
              margin="normal"
              type={item}
              style={{ display: item === "avatar" ? "none" : "block" }}
            />
          ) : (
            <Autocomplete
              id="combo-box-demo"
              options={roleOptions}
              getOptionLabel={(option) => option.label}
              value={selectedRole}
              onChange={(event, newValue) => setSelectedRole(newValue)}
              isOptionEqualToValue={(option, value) =>
                option.value === value.value
              }
              disablePortal
              renderInput={(params) => <TextField {...params} label="Role" />}
            />
          )}

          <ErrorMessage
            errors={errors}
            name={item}
            render={({ message }) => (
              <Typography sx={{ color: "red" }}>{message}</Typography>
            )}
          />
        </Box>
      ))}
      <Button type="submit" fullWidth variant="contained" sx={{ mt: 2 }}>
        Create Account
      </Button>
    </Box>
  );
}
