import styled from "@emotion/styled"
import { Avatar, Box, Card, Stack, Typography } from "@mui/material"



export const SideBarContainer = styled(Card)(({
    width: "20%",
    height: "100%"
}))

export const SideBarContent = styled(Stack)(({
    width: "100%",
    height: "100%"
}))

// SideBar avatar
export const SideBarAvatarContainer = styled(Box)(({
    height: "30%",
}))

export const SideBarAvatarContent = styled(Stack)(({
    height: "100%",
    alignItems: "center",
    padding: "10%"
}))

export const SideBarAvatar = styled(Avatar)(({
    marginBottom: "20px",
    width: "40%",
    height: "70%",
}))

export const SideBarAvatarName = styled(Typography)(({

}))

// SideBar Nav

export const SideBarNavContainer = styled(Stack)(({
    height: "70%",
    border: "1px solid red"
}))

export const SideBarNavContent = styled(Stack)(({
    height: "100%"
}))