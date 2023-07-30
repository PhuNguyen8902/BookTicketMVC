import { SideBarNavContainer, SideBarNavContent, SideBarNavMenu, SideBarNavMenuText, SideBarNavMenuTitle,StyleLink, 
    StyleHomeIcon, StyleBadgeIcon, StyleConfirmationNumberIcon, StyleDirectionsCarIcon, StyleRouteIcon, StyleEmailIcon, 
    StyleMessageIcon, StyleGroupIcon } from "../../assets/styles/sideBar";


export default function SideBarNavPortion() {
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
                    <StyleLink to="/Admin/">
                        <SideBarNavMenuText>
                            <StyleBadgeIcon />
                            Employees
                        </SideBarNavMenuText>
                    </StyleLink>
                    <StyleLink to="/Admin/">
                        <SideBarNavMenuText>
                            <StyleGroupIcon />
                            Customers
                        </SideBarNavMenuText>
                    </StyleLink>
                    <StyleLink to="/Admin/">
                        <SideBarNavMenuText>
                            <StyleConfirmationNumberIcon/>
                            Tickets
                        </SideBarNavMenuText>
                    </StyleLink>
                    <StyleLink to="/Admin/">
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
                </SideBarNavMenu>
            </SideBarNavContent>
        </SideBarNavContainer>
    )
}