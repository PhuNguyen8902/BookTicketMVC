import { SideBarAvatarContainer, SideBarAvatarContent, SideBarAvatar, SideBarAvatarName } from "../../assets/styles/sideBar";


export default function SideBarAvatarPortion(){
    return(
        <SideBarAvatarContainer>
            <SideBarAvatarContent>
                <SideBarAvatar />
                <SideBarAvatarName variant="h5">Your Name</SideBarAvatarName>
            </SideBarAvatarContent>
        </SideBarAvatarContainer>
    )
}