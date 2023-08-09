import { Box, Typography } from "@mui/material";
import { Route } from "../components";

export default function RoutePage() {
  // const columns = [
  //   {
  //     header: "Id",
  //     accessorKey: "id", //simple accessorKey pointing to flat data
  //   },
  //   {
  //     header: "Name",
  //     accessorKey: "name", //simple accessorKey pointing to flat data
  //   },
  //   {
  //     header: "Start Station",
  //     accessorKey: "startStation", //simple accessorKey pointing to flat data
  //   },
  //   {
  //     header: "End Station",
  //     accessorKey: "endStation", //simple accessorKey pointing to flat data
  //   },
  //   {
  //     header: "Distance",
  //     accessorKey: "distance", //simple accessorKey pointing to flat data
  //   },
  //   {
  //     header: "Duration",
  //     accessorKey: "duration", //simple accessorKey pointing to flat data
  //   },
  // ];
  return (
    <Box>
      <Typography variant="h1">Route</Typography>
      <Route />
    </Box>
  );
}
