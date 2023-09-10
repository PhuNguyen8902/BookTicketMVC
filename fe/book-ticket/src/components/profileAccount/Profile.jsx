import { useSelector } from "react-redux";
import {
  ProfileContainer,
  ProfileContent,
} from "../../assets/styles/profileAcountPage";
import { Box, Input, Stack, TextField, Typography } from "@mui/material";
import { useState } from "react";

export default function Profile() {
  const selectUser = (state) => state.auth.user;
  const user = useSelector(selectUser);

  const [profile, setProfile] = useState({
    email: user.email,
    name: user.name,
    phone: user.phone,
  });

  return (
    <ProfileContainer>
      <ProfileContent>
        <Stack direction={"row"}>
          <Box sx={{width: "40%"}}>
            <img width="80%" src={user.avatar} />
          </Box>
          <Box>
            <Stack direction={"row"} sx={{margin: "0 0 0.7rem 0"}}>
              <Typography variant="h5" sx={{margin: "auto 1rem auto 0"}}>Email:</Typography>
              <TextField
                value={profile.email}
              />
            </Stack>
            <Stack direction={"row"} sx={{margin: "0 0 0.7rem 0"}}>
              <Typography variant="h5" sx={{margin: "auto 1rem auto 0"}}>Name:</Typography>
              <TextField
                value={profile.name}
              />
            </Stack>
            <Stack direction={"row"} sx={{margin: "0 0 0.7rem 0"}}>
              <Typography variant="h5" sx={{margin: "auto 1rem auto 0"}}>Phone:</Typography>
              <TextField
                value={profile.phone}
              />
            </Stack>
          </Box>
        </Stack>
        
        
      </ProfileContent>
    </ProfileContainer>
  );
}
