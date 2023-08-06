import { SideBarContainer, SideBarContent } from "../../assets/styles/sideBar";
import Authenticate from "../auth/Authenticate";
import SideBarAvatarPortion from "./SideBarAvatarPortion";
import SideBarNavPortion from "./SideBarNavPortion";

export default function SizeBar() {
  return (
    <Authenticate>
      <SideBarContainer>
        <SideBarContent>
          <SideBarAvatarPortion />
          <SideBarNavPortion />
        </SideBarContent>
      </SideBarContainer>
    </Authenticate>
  );
}
