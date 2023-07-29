import { NavBarContainer, NavBarHeader, MyList, StyleLink, StyleListItemIcon } from "../../assets/styles/navBar"
import { Box, Avatar, Button, IconButton, Menu, MenuItem } from "@mui/material"
import { Link } from "react-router-dom"
import { useState } from "react"
export default function NavBar() {
    const [auth, setAuth] = useState(true);
    const [anchorEl, setAnchorEl] = useState(null);

    const handleChange = (event) => {
        setAuth(event.target.checked);
    };

    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <NavBarContainer>
            <NavBarHeader>DRiver</NavBarHeader>
            <MyList type='row'>
                <StyleLink to="/">Home</StyleLink>
                <StyleLink to="/">Routes</StyleLink>
                <StyleLink to="/aboutUs">About Us</StyleLink>
                <StyleLink to="/contact">Contact</StyleLink>
            </MyList>
            <StyleListItemIcon>
                <Box sx={{ display: "flex", alignItems: "center" }}>
                    <Button sx={{ marginRight: "4px" }}>Sign In</Button>
                    {auth && (
                        <div>
                            <IconButton
                                size="large"
                                aria-label="account of current user"
                                aria-controls="menu-appbar"
                                aria-haspopup="true"
                                onClick={handleMenu}
                            >
                                <Avatar />
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
                                <MenuItem onClick={handleClose}>
                                    <Link to="/profileAcount">Profile</Link>
                                </MenuItem>
                                <MenuItem onClick={handleClose}>
                                    <Link to="/">My Account</Link>
                                </MenuItem>
                            </Menu>
                        </div>
                    )}
                </Box>
            </StyleListItemIcon>
        </NavBarContainer>
    )
}