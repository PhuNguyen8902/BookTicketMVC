import styled from "@emotion/styled"
import { Avatar, Box, Card, Stack, Typography } from "@mui/material"
import { colors } from "../Theme"
import HomeIcon from '@mui/icons-material/Home';
import BadgeIcon from '@mui/icons-material/Badge';
import GroupIcon from '@mui/icons-material/Group';
import EmailIcon from '@mui/icons-material/Email';
import RouteIcon from '@mui/icons-material/Route';
import DirectionsCarIcon from '@mui/icons-material/DirectionsCar';
import MessageIcon from '@mui/icons-material/Message';
import ConfirmationNumberIcon from '@mui/icons-material/ConfirmationNumber';
import { Link } from "react-router-dom";


export const SideBarContainer = styled(Card)(({
    width: "20%",
    height: "100%",
    boxSizing: "border-box",
}))

export const SideBarContent = styled(Stack)(({
    width: "100%",
    height: "100%"
}))

// SideBar avatar
export const SideBarAvatarContainer = styled(Box)(({
    height: "30%",
    marginBottom: "40px"
}))

export const SideBarAvatarContent = styled(Stack)(({
    height: "100%",
    alignItems: "center",
    padding: "10%"
}))

export const SideBarAvatar = styled(Avatar)(({
    marginBottom: "20px",
    width: "60%",
    height: "90%",
}))

export const SideBarAvatarName = styled(Typography)(({

}))

// SideBar Nav

export const SideBarNavContainer = styled(Stack)(({
    height: "70%",
}))

export const SideBarNavContent = styled(Stack)(({
    height: "100%"
}))

export const SideBarNavMenu = styled(Stack)(({
    padding: "0 0 0 30px",
    marginBottom: "30px",
}))

export const SideBarNavMenuTitle = styled(Typography)(({
    color: colors.titleNavBar,
    marginBottom: "10px",
}))

export const StyleLink = styled(Link)(({
        textDecoration: "none"
}))
export const SideBarNavMenuText = styled(Typography)(({
    margin: "0 0 8px 20px",
    color: "black",
    textDecoration: "none"
}))

const styleNavIcon = {
    margin: "0 5px -4px 0",
    fontsize: "14px",
    color: colors.titleNavBar + "!important",
}
 
export const StyleHomeIcon = styled(HomeIcon)(({
    ...styleNavIcon,
}))
export const StyleBadgeIcon = styled(BadgeIcon)(({
    ...styleNavIcon,
}))
export const StyleEmailIcon = styled(EmailIcon)(({
    ...styleNavIcon,
}))
export const StyleGroupIcon = styled(GroupIcon)(({
    ...styleNavIcon,
}))
export const StyleRouteIcon = styled(RouteIcon)(({
    ...styleNavIcon,
}))
export const StyleDirectionsCarIcon = styled(DirectionsCarIcon)(({
    ...styleNavIcon,
}))
export const StyleMessageIcon = styled(MessageIcon)(({
    ...styleNavIcon,
}))
export const StyleConfirmationNumberIcon = styled(ConfirmationNumberIcon)(({
    ...styleNavIcon,
}))