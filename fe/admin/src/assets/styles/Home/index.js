import styled from "@emotion/styled";
import { FormatAlignJustify } from "@mui/icons-material";
import { Box, Card, Stack, Typography } from "@mui/material";
import { AreaChart } from "recharts";

export const AdminHomePageContainer = styled(Stack)({
  width: "70%",
  height: "100%",
});

export const AdminHomePageContent = styled(Stack)({
  width: "100%",
  height: "100%",
});

// Cards
export const AdminHomePageCardsContainer = styled(Stack)({
  width: "100%",
  marginBottom: "3.5rem",
});

export const AdminHomePageCardsContent = styled(Stack)({
  width: "100%",
  alignItems: "center",
});

export const AdminHomePageCard = styled(Card)({
  width: "30%",
  padding: "2rem",
});

export const AdminHomePageCardTitle = styled(Typography)({
  fontSize: "2.2rem",
});

export const AdminHomePageCardValue = styled(Typography)({
  fontSize: "2rem",
  fontWeight: "bold",
  paddingLeft: "0.5rem",
});

export const AdminHomePageCardText = styled(Typography)({});

//Chart

export const AdminHomePageChartContainer = styled(Card)({
  width: "100%",
  padding: "1rem 0rem",
});

export const AdminHomePageChartContent = styled(Stack)({
  width: "100%",
});

export const AdminHomePageChartTitle = styled(Typography)({
  marginBottom: "1rem",
});
