import { useSelector } from "react-redux";
import { SideBarContainer, SideBarContent } from "../../assets/styles/sideBar";
import Authenticate from "../auth/Authenticate";
import SideBarAvatarPortion from "./SideBarAvatarPortion";
import SideBarNavPortion from "./SideBarNavPortion";
import SideBarNavPortionEmployee from "./SideBarNavPortionEmployee";

export default function SizeBar() {
  const user = useSelector((state) => state.auth.user);

  return (
    <Authenticate>
      <SideBarContainer>
        <SideBarContent>
          <SideBarAvatarPortion />
          {user.role == "ROLE_ADMIN" ? (
            <SideBarNavPortion />
          ) : (
            <SideBarNavPortionEmployee />
          )}
        </SideBarContent>
      </SideBarContainer>
    </Authenticate>
  );
}
