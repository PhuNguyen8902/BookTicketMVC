import { useSelector } from "react-redux";
import { History } from "../components";
import { Typography } from "@mui/material";

export default function HistoryPage() {
  const isLogin = useSelector((state) => state.auth.isLogin);
  return (
    <>
      {isLogin ? (
        <History />
      ) : (
        <Typography
          variant="h3"
          sx={{
            display: "flex",
            justifyContent: "center",
            margin: "10vh",
            color: "red ",
          }}
        >
          Vui lòng đăng nhập
        </Typography>
      )}
    </>
  );
}
