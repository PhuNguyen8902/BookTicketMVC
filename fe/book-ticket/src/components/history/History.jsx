import { Avatar, Container, Paper, Stack, Typography } from "@mui/material";

export default function History() {
  return (
    <>
      <Container>
        <Typography
          variant="h3"
          sx={{
            display: "flex",
            justifyContent: "center",
          }}
        >
          History
        </Typography>
        <Stack spacing={2} mt={2} alignItems="center">
          <Stack spacing={2} direction="row" alignItems="center">
            <Avatar>W</Avatar>
            <Typography noWrap>so 1</Typography>
          </Stack>
          <Stack spacing={2} direction="row" alignItems="center">
            <Avatar>W</Avatar>
            <Typography noWrap>so 2</Typography>
          </Stack>
        </Stack>
      </Container>
    </>
  );
}
