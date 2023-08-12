import { useDispatch } from "react-redux";
import {
  SideBarNavContainer,
  SideBarNavContent,
  SideBarNavMenu,
  SideBarNavMenuText,
  SideBarNavMenuTitle,
  StyleLink,
  StyleHomeIcon,
  StyleBadgeIcon,
  StyleConfirmationNumberIcon,
  StyleDirectionsCarIcon,
  StyleRouteIcon,
  StyleEmailIcon,
  StyleMessageIcon,
  StyleGroupIcon,
} from "../../assets/styles/sideBar";
import { signOut } from "../../store/slices/authSlice";
import { Button } from "@mui/material";

export default function SideBarNavPortionEmployee() {
  const dispatcher = useDispatch();
  const handleSignOut = () => {
    dispatcher(signOut());
  };
  return (
    <SideBarNavContainer>
      <SideBarNavContent>
        <SideBarNavMenu>
          <SideBarNavMenuTitle>Dashboard</SideBarNavMenuTitle>
          <StyleLink to="/Admin/">
            <SideBarNavMenuText>
              <StyleHomeIcon />
              Home
            </SideBarNavMenuText>
          </StyleLink>
        </SideBarNavMenu>
        <SideBarNavMenu>
          <SideBarNavMenuTitle>Quick Menu</SideBarNavMenuTitle>
          <StyleLink to="/authen/">
            <SideBarNavMenuText>
              <StyleGroupIcon />
              Customers
            </SideBarNavMenuText>
          </StyleLink>
          <StyleLink to="/trip/">
            <SideBarNavMenuText>
              <StyleEmailIcon />
              Trip
            </SideBarNavMenuText>
          </StyleLink>
          <StyleLink to="/Admin/">
            <SideBarNavMenuText>
              <StyleConfirmationNumberIcon />
              Tickets
            </SideBarNavMenuText>
          </StyleLink>
          <StyleLink to="/route/">
            <SideBarNavMenuText>
              <StyleRouteIcon />
              Routes
            </SideBarNavMenuText>
          </StyleLink>
          <StyleLink to="/Admin/">
            <SideBarNavMenuText>
              <StyleDirectionsCarIcon />
              Vehicles
            </SideBarNavMenuText>
          </StyleLink>
        </SideBarNavMenu>
        <SideBarNavMenu>
          <SideBarNavMenuTitle>Notifications</SideBarNavMenuTitle>
          <StyleLink to="/Admin/">
            <SideBarNavMenuText>
              <StyleEmailIcon />
              Mails
            </SideBarNavMenuText>
          </StyleLink>
          <StyleLink to="/Admin/">
            <SideBarNavMenuText>
              <StyleMessageIcon />
              Feebacks
            </SideBarNavMenuText>
          </StyleLink>
          <Button variant="contained" onClick={handleSignOut}>
            Sign Out
          </Button>
        </SideBarNavMenu>
      </SideBarNavContent>
    </SideBarNavContainer>
  );
}
