import { Box, Stack, Typography } from "@mui/material";
import styled from "styled-components";
import { colors } from "../theme";
import CheckCircleOutlineIcon from "@mui/icons-material/CheckCircleOutline";
import SearchIcon from "@mui/icons-material/Search";
import MobileFriendlyIcon from "@mui/icons-material/MobileFriendly";

export const AboutUsContainer = styled(Stack)({
  margin: "40px 0",
  width: "100%",
  marginTop: "100px",
});

export const AboutUsContent = styled(Stack)({
  justifyContent: "center",
  alignItems: "center",
  width: "100%",
});

//section 1: information about us
export const InfoAboutUsContainer = styled(Stack)({
  justifyContent: "center",
  width: "65%",
  marginBottom: "100px",
});

export const InfoAboutUsContent = styled(Stack)({
  width: "50%",
  padding: "30px 0 20px 65px",
});

export const InfoAboutUsUpperTitles = styled(Typography)({
  position: "relative",
  marginBottom: "20px !important",
  color: colors.secondary,
});

export const BeforeInfoAboutUsUpperTitleLine1 = styled("span")({
  position: "absolute",
  content: "",
  top: "8px",
  left: "-35px",
  width: "30px",
  height: "1.5px",
  backgroundColor: colors.secondary,
});
export const BeforeInfoAboutUsUpperTitleLine2 = styled("span")({
  position: "absolute",
  content: "",
  top: "15px",
  left: "-50px",
  width: "45px",
  height: "1.5px",
  backgroundColor: colors.secondary,
});

export const StyleCheckCircleOutlineIcon = styled(CheckCircleOutlineIcon)({
  margin: "0 5px -5px 0",
  fontSize: "18px !important",
  color: colors.secondary,
});

// section 2: Our Vehicles

export const VehiclesAboutUsContainer = styled(Stack)({
  justifyContent: "center",
  width: "100%",
  backgroundColor: "#F5F5F5",
  alignItems: "center",
  marginBottom: "100px",
});

export const VehiclesAboutUsContent = styled(Stack)({
  width: "80%",
  padding: "50px 0 80px 65px",
});

export const VehiclesAboutUseUpperTitle = styled(Typography)({
  marginBottom: "15px",
  color: colors.secondary,
});

// section 2: Our Working Process (step)
export const StepAboutUsContainer = styled(Stack)({
  justifyContent: "center",
  width: "100%",
  alignItems: "center",
  marginBottom: "100px",
});

export const StepAboutUsContent = styled(Stack)({
  width: "80%",
  padding: "50px 0 80px 65px",
  alignItems: "center",
});

export const StepAboutUsUpperTitle = styled(Typography)({
  color: "white",
  backgroundColor: colors.secondary,
  padding: "5px 12px",
  marginBottom: "15px !important",
});

export const StepAboutUsWrapCard = styled(Box)({
  filter: "drop-shadow(0px 0px 4px black)",
  position: "relative",
});
export const StepAboutUsCard = styled(Stack)({
  textAlign: "center",
  justifyContent: "center",
  width: "240px",
  height: "270px",
  position: "relative",
  backgroundColor: "white",
  clipPath: "polygon(50% 0, 100% 25%, 100% 75%, 50% 100%, 0 75%, 0 25%)",
});

export const StepAboutUsCardBefore = styled(Stack)({
  justifyContent: "center",
  alignItems: "center",
  position: "absolute",
  top: "-30px",
  left: "30%",
  width: "90px",
  height: "100px",
  zIndex: 1,
  backgroundColor: colors.secondary,
  clipPath:
    "path('M39 1.88675C42.094 0.100423 45.906 0.100423 49 1.88675L82.3013 21.1132C85.3953 22.8996 87.3013 26.2008 87.3013 29.7735V68.2265C87.3013 71.7992 85.3953 75.1004 82.3013 76.8867L49 96.1133C45.906 97.8996 42.094 97.8996 39 96.1133L5.69873 76.8867C2.60472 75.1004 0.69873 71.7992 0.69873 68.2265V29.7735C0.69873 26.2008 2.60472 22.8996 5.69873 21.1132L39 1.88675Z')",
});

export const StepAboutUsCardBeforeIcon1 = styled(SearchIcon)({
  fontSize: "40px !important",
  color: colors.white,
});

export const StepAboutUsCardBeforeIcon2 = styled(MobileFriendlyIcon)({
  fontSize: "40px !important",
  color: colors.white,
});

export const StepAboutUsCardAfter = styled(Stack)({
  justifyContent: "center",
  alignItems: "center",
  position: "absolute",
  bottom: "-20px",
  left: "34%",
  width: "70px",
  height: "70px",
  borderRadius: "50%",
  zIndex: 1,
  backgroundColor: colors.third,
});

export const StepAboutUsCardAfterTitle = styled(Typography)({
  fontSize: "23px !important",
  color: colors.white,
});
