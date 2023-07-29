import { SideBarContainer, SideBarContent } from "../../assets/styles/sideBar";
import SideBarAvatarPortion from "./SideBarAvatarPortion";
import SideBarNavPortion from "./SideBarNavPortion";



export default function SizeBar(){
    return(
        <SideBarContainer>
            <SideBarContent>
                <SideBarAvatarPortion/>
                <SideBarNavPortion/>
            </SideBarContent>
        </SideBarContainer>
    )
}