import { Avatar, Box, Button, TextField, Typography } from "@mui/material";
import { useDispatch } from "react-redux";
import { useForm } from "react-hook-form";
import { ErrorMessage } from "@hookform/error-message";
import authService from "../../services/authService";
import { signIn } from "../../store/slices/authSlice";
import { closePopup } from "../../store/slices/pageSlice";
import { useState } from "react";
import pictureService from "../../services/pictureService";

const initialForms = {
  field: {
    name: "",
    password: "",
    email: "",
    phone: "",
    avatar: "",
  },
  options: {
    name: { required: "This is required." },
    email: { required: "This is required." },
    password: { required: "This is required." },
    phone: { required: "This is required." },
  },
};
export default function AuthRegister() {
  const dispatcher = useDispatch();
  const [imageSrc, setImageSrc] = useState("");
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    defaultValues: initialForms.field,
  });
  const handleClose = () => {
    dispatcher(closePopup());
  };
  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file && file.type.startsWith("image/")) {
      const reader = new FileReader();
      reader.onload = function (event) {
        setImageSrc(event.target.result);
      };
      reader.readAsDataURL(file);
    } else {
      alert("Vui lòng chọn một tệp tin hình ảnh!");
    }
  };
  // const uploadPic = async () => {
  //   try {
  //     let response = null;
  //     if (imageSrc) {
  //       const newForm = new FormData();
  //       newForm["file"] = imageSrc;
  //       response = await pictureService.postPicture(newForm);
  //     }

  //     if (response != null) {
  //       if (!response.message) {
  //         console.log(response);
  //       } else {
  //         alert(response.message);
  //       }
  //     }
  //   } catch (error) {
  //     console.error(error);
  //     alert("Có lỗi xảy ra khi tải lên hình ảnh.");
  //   }
  // };
  // const onSubmit = async (form) => {
  //   const response = await authService.register(form);
  //   if (!response.message) {
  //     dispatcher(signIn(response));
  //     dispatcher({ type: "FETCH_INFO" });
  //     handleClose();
  //   } else {
  //     alert(response.message);
  //   }
  // };
  const onSubmit = async (form) => {
    try {
      let response = null;
      if (imageSrc) {
        const newForm = new FormData();
        newForm["file"] = imageSrc;
        console.log(newForm);
        response = await pictureService.postPicture(newForm);
      }

      if (response != null) {
        if (!response.message) {
          console.log(response);
        } else {
          alert(response.message);
        }
      }
    } catch (error) {
      console.error(error);
      alert("Có lỗi xảy ra khi tải lên hình ảnh.");
    }
  };

  return (
    <Box
      className="form signInForm"
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
    >
      {/* {imageSrc && ( */}

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

      {/* // <>
          //   <Avatar
          //     alt="Remy Sharp"
          //     src={imageSrc}
          //     variant="square"
          //     sx={{ width: "20vw", height: "20vh", marginTop: "10vh" }}
          //   />
          // </>
        // )} */}
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
              <Typography sx={{ color: "red" }}>{message}</Typography>
            )}
          />
        </Box>
      ))}
      <Button type="submit" fullWidth variant="contained" sx={{ mt: 2 }}>
        Sign up
      </Button>
    </Box>
  );
}
