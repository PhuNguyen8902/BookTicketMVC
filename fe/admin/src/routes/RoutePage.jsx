import { Box, Typography } from "@mui/material";
import { Route, RouteEmployee } from "../components";
import { useSelector } from "react-redux";

export default function RoutePage() {
  const user = useSelector((state) => state.auth.user);

  return (
    <Box>
      <Typography variant="h1">Route</Typography>
      {user.role == "ROLE_ADMIN" ? <Route /> : <RouteEmployee />}
      {/* <Route /> */}
    </Box>
  );
}
