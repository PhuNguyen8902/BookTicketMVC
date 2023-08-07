import { Box, Typography } from "@mui/material";
// import data from "../assets/fake/user";
import { Route } from "../components";
import routeService from "../service/routeService";
import { useState } from "react";
import { useEffect } from "react";
import { SERVER } from "../assets/js/constants";

export default function RoutePage() {
  const [data, setData] = useState([]);
  const url = new URL(window.location.href);
  let urlSearch = url.search;
  if (urlSearch == "") {
    urlSearch = "?page=1";
  }

  const api = `${SERVER}routeDemo${urlSearch}`;
  const fetchData = async () => {
    const rs = await routeService.getRoute(api);
    if (!rs.message) {
      setData(rs);
    } else {
      alert("Error");
    }
  };
  useEffect(() => {
    fetchData();
  }, []);
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
      header: "Start Station",
      accessorKey: "startStation", //simple accessorKey pointing to flat data
    },
    {
      header: "End Station",
      accessorKey: "endStation", //simple accessorKey pointing to flat data
    },
    {
      header: "Distance",
      accessorKey: "distance", //simple accessorKey pointing to flat data
    },
    {
      header: "Duration",
      accessorKey: "duration", //simple accessorKey pointing to flat data
    },
  ];
  return (
    <Box>
      <Typography variant="h1">Route</Typography>
      <Route data={data} columns={columns} />
    </Box>
  );
}
