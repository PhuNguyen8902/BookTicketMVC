import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import AccountCircle from "@mui/icons-material/AccountCircle";
import Switch from "@mui/material/Switch";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormGroup from "@mui/material/FormGroup";
import MenuItem from "@mui/material/MenuItem";
import Menu from "@mui/material/Menu";
import { useDispatch, useSelector } from "react-redux";
import { Avatar, Button } from "@mui/material";
import { openAuth } from "../../store/slices/pageSlice";
import { signOut } from "../../store/slices/authSlice";
import adminService from "../../services/adminService";
import pictureService from "../../services/pictureService";
import { useState } from "react";

export default function Demo() {
  const dispatcher = useDispatch();
  const [auth, setAuth] = React.useState(true);
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleChange = (event) => {
    setAuth(event.target.checked);
  };

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const checkLogin = () => {
    const isLogin = localStorage.getItem("token");
    if (isLogin) {
      setAuth(true);
    } else {
      setAuth(false);
    }
  };

  const checkRoleAdmin = async () => {
    const response = await adminService.test();
    console.log(response);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleSignIn = () => {
    dispatcher(openAuth());
  };
  const handleSignOut = () => {
    dispatcher(signOut());
    setAuth(false);
  };
  const [imageSrc, setImageSrc] = React.useState("");
  const [form, setForm] = useState(new FormData());

  // Xử lý sự kiện khi người dùng chọn tệp tin
  const handleFileChange = (event) => {
    const file = event.target.files[0];

    // Kiểm tra xem người dùng đã chọn một tệp tin hình ảnh hay không
    if (file && file.type.startsWith("image/")) {
      const reader = new FileReader();

      // // Đọc tệp tin và cập nhật trạng thái với đường dẫn của hình ảnh
      // reader.onload = function (event) {
      // setImageSrc(event.target.result);
      // reader.onload = function (event) {
      const newForm = new FormData();
      newForm.append("file", file);
      setForm(newForm);
      console.log(form);
      // };
      setImageSrc(file);
      // };
      reader.readAsDataURL(file); // Đọc tệp tin dưới dạng URL dữ liệu
    } else {
      // Nếu tệp tin không phải là hình ảnh, bạn có thể hiển thị thông báo hoặc xử lý khác tùy ý.
      alert("Vui lòng chọn một tệp tin hình ảnh!");
    }
  };
  const uploadPic = async () => {
    console.log(">>> ", imageSrc);
    try {
      // const form = {
      //   file: imageSrc,
      // };
      let response = null;
      if (imageSrc) {
        const newForm = new FormData();
        newForm["file"] = imageSrc;
        console.log(newForm);
        response = await pictureService.postPicture(newForm);

        // Gửi newForm đi hoặc thực hiện các thao tác khác với dữ liệu đã nhập.
        // Ví dụ sử dụng Axios để gửi request lên server.
      }
      // Lúc này, "formData" chứa cặp key-value với key là "file" và value là giá trị của "imageSrc"
      // Bạn có thể thêm nhiều cặp key-value vào "formData" nếu cần
      if (response != null) {
        if (!response.message) {
          console.log(response);
        } else {
          alert(response.message);
        }
      }
    } catch (error) {
      console.error(error);
      alert("Có lỗi xảy ra khi tải lên hình ảnh.");
    }
  };

  const isLogin = useSelector((store) => store.auth.isLogin);
  console.log(isLogin);
  const user = useSelector((store) => (isLogin ? store.auth.user : null));
  if (user != null) {
    console.log(user);
  }

  return (
    <>
      <Box sx={{ flexGrow: 1 }}>
        <FormGroup>
          <FormControlLabel
            control={
              <Switch
                checked={auth}
                onChange={handleChange}
                aria-label="login switch"
              />
            }
            label={auth ? "Logout" : "Login"}
          />
        </FormGroup>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ mr: 2 }}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
              Photos
            </Typography>
            {auth && (
              <div>
                <IconButton
                  size="large"
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleMenu}
                  color="inherit"
                >
                  <AccountCircle />
                </IconButton>
                <Menu
                  id="menu-appbar"
                  anchorEl={anchorEl}
                  anchorOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: "top",
                    horizontal: "right",
                  }}
                  open={Boolean(anchorEl)}
                  onClose={handleClose}
                >
                  <MenuItem onClick={handleClose}>Profile</MenuItem>
                  <MenuItem onClick={handleClose}>My account</MenuItem>
                </Menu>
              </div>
            )}
          </Toolbar>
        </AppBar>
      </Box>

      <Button variant="contained" onClick={handleSignIn}>
        Sign in
      </Button>
      <Button variant="contained" onClick={handleSignOut}>
        Sign out
      </Button>
      <Button variant="contained" onClick={checkLogin}>
        Check Login
      </Button>
      <Button variant="contained" onClick={checkRoleAdmin}>
        Check Role Admin
      </Button>
      <Button variant="contained" component="label">
        Upload File
        <input
          type="file"
          accept="image/*"
          style={{ display: "none" }}
          onChange={handleFileChange}
        />
      </Button>

      {imageSrc && (
        <>
          <Avatar
            alt="Remy Sharp"
            src={imageSrc}
            variant="square"
            sx={{ width: "20vw", height: "20vh", marginTop: "10vh" }}
          />
          <Button variant="contained" onClick={uploadPic}>
            Upload
          </Button>
        </>
      )}
    </>
  );
}
