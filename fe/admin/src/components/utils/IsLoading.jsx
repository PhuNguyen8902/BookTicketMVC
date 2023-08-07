import { Box, CircularProgress } from "@mui/material";

export default function IsLoading() {
  return (
    <Box className="loading" sx={{ display: "flex" }}>
      <CircularProgress />
    </Box>
  );
}
