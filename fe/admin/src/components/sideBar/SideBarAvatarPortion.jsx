import { useSelector } from "react-redux";
import {
  SideBarAvatarContainer,
  SideBarAvatarContent,
  SideBarAvatar,
  SideBarAvatarName,
} from "../../assets/styles/sideBar";

export default function SideBarAvatarPortion() {
  const user = useSelector((state) => state.auth.user);

  return (
    <SideBarAvatarContainer>
      <SideBarAvatarContent>
        <SideBarAvatar src={user.avatar} />
        <SideBarAvatarName variant="h5">{user.name}</SideBarAvatarName>
      </SideBarAvatarContent>
    </SideBarAvatarContainer>
  );
}
