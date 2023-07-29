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
import { Button } from "@mui/material";
import { openAuth } from "../../store/slices/pageSlice";
import { signOut } from "../../store/slices/authSlice";
import adminService from "../../services/adminService";

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
    // dispatcher({ type: "FETCH_INFO" });
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
    </>
  );
}
