import { Box, Container, Typography } from "@mui/material";
import SignUp from "./SignUp";
export default function AuthPage() {
  return (
    <>
      <Container maxWidth="false" className="auth__background">
        <Box className="authbox" width={"30vw"}>
          <Box className="authbox__header" padding={1}>
            <Typography align="center" variant="h6" color={"#fff"}>
              Create an account for employee or customer
            </Typography>
          </Box>
          <Box
            className="authbox__accordion"
            bgcolor={"#fff"}
            paddingX={5}
            paddingBottom={2}
          >
            <SignUp />
          </Box>
        </Box>
      </Container>
    </>
  );
}
