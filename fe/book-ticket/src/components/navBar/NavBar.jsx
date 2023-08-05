import {
  NavBarContainer,
  NavBarHeader,
  MyList,
  StyleLink,
  StyleListItemIcon,
} from "../../assets/styles/navBar";
import { Box, Avatar, Button, IconButton, Menu, MenuItem } from "@mui/material";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import { openAuth } from "../../store/slices/pageSlice";
import { useDispatch, useSelector } from "react-redux";
import { signOut } from "../../store/slices/authSlice";

export default function NavBar() {
  const dispatcher = useDispatch();
  const [auth, setAuth] = useState(true);
  // const [anchorEl, setAnchorEl] = useState(null);
  const handleChange = (event) => {
    setAuth(event.target.checked);
  };

  // const handleMenu = (event) => {
  //   setAnchorEl(event.currentTarget);
  // };

  // const handleClose = () => {
  //   setAnchorEl(null);
  // };
  const handleSignIn = () => {
    dispatcher(openAuth());
  };
  const handleSignOut = () => {
    dispatcher(signOut());
    setAuth(false);
  };
  const selectLogin = (state) => state.auth.isLogin;
  const isLogin = useSelector(selectLogin);
  useEffect(() => {
    if (isLogin) {
      setAuth(true);
    } else {
      setAuth(false);
    }
  });

  return (
    <NavBarContainer>
      <NavBarHeader>DRiver</NavBarHeader>
      <MyList type="row">
        <StyleLink to="/">Home</StyleLink>
        <StyleLink to="/">Routes</StyleLink>
        <StyleLink to="/aboutUs">About Us</StyleLink>
        <StyleLink to="/contact">Contact</StyleLink>
      </MyList>
      <StyleListItemIcon>
        <Box sx={{ display: "flex", alignItems: "center" }}>
          {auth ? (
            <>
              <Button sx={{ marginRight: "4px" }} onClick={handleSignOut}>
                Sign Out
              </Button>
              <Link to="/profileAcount">
                <IconButton
                  size="large"
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                >
                  <Avatar />
                </IconButton>
              </Link>
            </>
          ) : (
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleSignIn}
            >
              <Avatar />
            </IconButton>
          )}
        </Box>
      </StyleListItemIcon>
    </NavBarContainer>
  );
}
