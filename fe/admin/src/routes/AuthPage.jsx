import { Box, Container, Typography } from "@mui/material";
import { Login } from "../components/index";
import { Navigate } from "react-router-dom";
import { useSelector } from "react-redux";
export default function AuthPage() {
  const haveLogin = useSelector((state) => state.auth.isLogin);
  if (!haveLogin) {
    return (
      <>
        <Container maxWidth="false" className="auth__background">
          <Box className="authbox" width={"30vw"}>
            <Box className="authbox__header" padding={1}>
              <Typography align="center" variant="h6" color={"#fff"}>
                Enter your email and password
              </Typography>
            </Box>
            <Box
              className="authbox__accordion"
              bgcolor={"#fff"}
              paddingX={5}
              paddingBottom={2}
            >
              <Login />
            </Box>
          </Box>
        </Container>
      </>
    );
  } else {
    const url = localStorage.getItem("url");

    return (
      <>{url == null ? <Navigate to={"/Admin/"} /> : <Navigate to={url} />}</>
    );
  }
}
