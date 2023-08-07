import { Box, Typography } from "@mui/material";
import data from "../assets/fake/user";
import { Trip } from "../components";

export default function TripPage() {
  const columns = [
    {
      header: "Id",
      accessorKey: "id", //simple accessorKey pointing to flat data
    },
    {
      header: "Name",
      accessorKey: "name", //simple accessorKey pointing to flat data
    },
    {
      header: "Email",
      accessorKey: "email", //simple accessorKey pointing to flat data
    },
    {
      header: "Is active",
      accessorKey: "isActive", //simple accessorKey pointing to flat data
    },
    {
      header: "Join date",
      accessorKey: "createdDate", //simple accessorKey pointing to flat data
    },
  ];
  return (
    <Box>
      <Typography variant="h1">Trip</Typography>
      <Trip data={data} columns={columns} />
    </Box>
  );
}
