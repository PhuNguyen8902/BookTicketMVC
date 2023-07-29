import styled from "styled-components";
import { Box, List, Typography } from "@mui/material";
import { colors } from "../theme";


export const BannerContainer = styled(Box)(({
    display: "flex",
    justifyContent: "center",
    padding: "0px",
    width: "100%",
    height: "100%",
    color: "white",
    backgroundColor: colors.secondary,
    padding: "60px 0 90px 0"
}))

export const BannerImg = styled("img")(({src}) => ({
    src: `url(${src})`,
    width: "500px"
}))

export const BannerContent =  styled(Box)(({
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    textAlign: "center",
    maxWidth: "620px",
    padding: "20px !important"
}))

export const BannerTitle = styled(Typography)(({
    fontSize: "38px !important",
    marginBottom: "20px !important"
}))

export const BannerDescription = styled(Typography)(({
    lineHeight: 1.5,
    fontSize: "18px !important",
    marginBottom: "3em !important"
}))

