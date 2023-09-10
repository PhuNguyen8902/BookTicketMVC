import { useSelector } from "react-redux";
import {
  ProfileContainer,
  ProfileContent,
} from "../../assets/styles/profileAcountPage";
import { Box, Button, Input, Stack, TextField, Typography } from "@mui/material";
import { useState } from "react";
import { useForm } from "react-hook-form";
import userService from "../../services/userService";
import { useEffect } from "react";

export default function Profile() {

  const selectUser = (state) => state.auth.user;
  const user = useSelector(selectUser);

  const [isEdit, setIsEdit] = useState(true)

  const initialForms = {
    field: {
      uuid: "",
      email: user.email,
      name: user.name,
      phone: user.phone,
    },
    options: {
      name: { required: "This is required." },
      email: { required: "This is required." },
      password: { required: "This is required." },
      phone: { required: "This is required." },
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
    form.uuid =user.id;
    setIsEdit(!isEdit)
    const rs = await userService.updateBasicUser(form)
    alert("Successful");
  }

  return (
    <ProfileContainer>
      <ProfileContent
      component={"form"}
      onSubmit={handleSubmit(onSubmit)}
      >
        <Stack direction={"row"} sx={{margin: "0 0 1rem 0"}}>
          <Box sx={{width: "40%"}}>
            <img width="80%" src={user.avatar} />
          </Box>
          <Box>
            <Stack direction={"row"} sx={{margin: "0 0 0.7rem 0"}}>
              <Typography variant="h5" sx={{margin: "auto 1rem auto 0"}}>Email:</Typography>
              <TextField
                type= "email"
                {...register("email", {
                  ...initialForms.options["email"],
                })}
                InputProps={{
                  readOnly: true
                }}
              />
            </Stack>
            <Stack direction={"row"} sx={{margin: "0 0 0.7rem 0"}}>
              <Typography variant="h5" sx={{margin: "auto 1rem auto 0"}}>Name:</Typography>
              <TextField
                {...register("name", {
                  ...initialForms.options["name"],
                })}
                InputProps={{
                  readOnly: isEdit
                }}
              />
            </Stack>
            <Stack direction={"row"} sx={{margin: "0 0 0.7rem 0"}}>
              <Typography variant="h5" sx={{margin: "auto 1rem auto 0"}}>Phone:</Typography>
              <TextField
                {...register("phone", {
                  ...initialForms.options["phone"],
                })}
                InputProps={{
                  readOnly: isEdit
                }}
              />
            </Stack>
          </Box>
        </Stack>
        <Stack direction={"row"} sx={{alignItems: "center"}}>
          <Button variant="contained" onClick={() => setIsEdit(!isEdit)} disabled={!isEdit} sx={{margin: "0 1rem 0 0"}}>Edit</Button>
          {!isEdit?
            <Button type="submit" variant="contained">Confirm</Button>
            : null
          }
          
        </Stack>
      </ProfileContent>
    </ProfileContainer>
  );
}
