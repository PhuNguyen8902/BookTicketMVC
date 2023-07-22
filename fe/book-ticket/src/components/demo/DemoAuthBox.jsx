import { TabContext, TabList, TabPanel } from "@mui/lab";
import { Box, Divider, Tab, Typography } from "@mui/material";
import { useState } from "react";
import DemoAuthLogin from "./DemoAuthLogin";
import DemoAuthRegister from "./DemoAuthRegister";

export default function DemoAuthBox() {
  const [tabIndex, setTabIndex] = useState("0");
  const handleTabIndex = (e, value) => {
    setTabIndex(value);
  };
  return (
    <Box className="authbox" width={"30vw"}>
      <Box className="authbox__header" padding={1}>
        <Typography align="center" variant="h6" color={"#fff"}>
          Welcome to our website, please log in to continue
        </Typography>
      </Box>
      <TabContext value={tabIndex}>
        <Box
          className="authbox__accordion"
          bgcolor={"#fff"}
          paddingX={5}
          paddingBottom={2}
        >
          <TabList onChange={handleTabIndex} variant="fullWidth">
            <Tab label="Sign in" value="0" />
            <Tab label="Sign up" value="1" />
          </TabList>
          <Divider>
            <Typography variant="subtitle2">or</Typography>
          </Divider>
          <TabPanel value="0" sx={{ p: 0 }}>
            <DemoAuthLogin />
          </TabPanel>
          <TabPanel value="1" sx={{ p: 0 }}>
            <DemoAuthRegister />
          </TabPanel>
        </Box>
      </TabContext>
    </Box>
  );
}
