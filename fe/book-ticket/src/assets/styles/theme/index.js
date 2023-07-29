import { createTheme } from "@mui/material";

export const colors = {
    primary: "#F6F6F2" ,
    secondary: "#388087",
    third: "#6FB3B8",
    fourth: "#BADFE7",
    fiveth: "#C2EDCE",
    white: "white",
}

const theme = createTheme({
    palette:{
        primary: {
            main: colors.secondary
        },
        secondary:{
            main: colors.third
        },
       
    }
});

export default theme 