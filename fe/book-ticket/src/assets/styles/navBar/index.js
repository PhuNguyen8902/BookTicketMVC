import styled from "styled-components";
import { Box, IconButton, List, ListItemIcon, Typography } from "@mui/material";
import { colors } from "../theme";
import { Link } from "react-router-dom"

import "@fontsource/montez"
//Container
export const NavBarContainer = styled(Box)(() => ({
    flexGrow: 1,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    padding: '2px 8px',
}));

export const NavBarHeader = styled(Typography)(() => ({
    fontSize: '4em  !important',
    fontFamily: '"Montez", "cursive" !important',
    padding: '4px',
    flexGrow: 1,
    color: colors.secondary
}));

export const MyList = styled(List)(( {type} ) => ({
    display: type === "row"? 'flex' : "block",
    flexGrow: 3,
    justifyContent: "center",
    alignItems: "center",
}))

export const StyleLink = styled(Link)(() => ({
    color: colors.third,
    margin: "30px",
    fontSize: "22px",
    "&:hover":{
        color: colors.secondary,
    }
}))

export const StyleListItemIcon = styled(ListItemIcon)(() => ({
    flexGrow: 1,
    marginRight: "3em",
    justifyContent: "right"
}))



