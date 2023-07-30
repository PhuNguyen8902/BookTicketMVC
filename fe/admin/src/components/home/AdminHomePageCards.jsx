import { AdminHomePageCard, AdminHomePageCardTitle, AdminHomePageCardValue, AdminHomePageCardsContainer, AdminHomePageCardsContent } from "../../assets/styles/Home";



export default function AdminHomePageCards(){
    return(
        <AdminHomePageCardsContainer>
            <AdminHomePageCardsContent direction={"row"} spacing={10}>
                <AdminHomePageCard>
                    <AdminHomePageCardTitle>Employees:</AdminHomePageCardTitle>
                    <AdminHomePageCardValue>100</AdminHomePageCardValue>
                </AdminHomePageCard>
                <AdminHomePageCard>
                    <AdminHomePageCardTitle>Customers:</AdminHomePageCardTitle>
                    <AdminHomePageCardValue>1340</AdminHomePageCardValue>
                </AdminHomePageCard>
                <AdminHomePageCard>
                    <AdminHomePageCardTitle>Routes:</AdminHomePageCardTitle>
                    <AdminHomePageCardValue>23</AdminHomePageCardValue>
                </AdminHomePageCard>
            </AdminHomePageCardsContent>
        </AdminHomePageCardsContainer>
    )
}