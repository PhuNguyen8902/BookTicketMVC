import { AdminHomePageContainer, AdminHomePageContent } from "../../assets/styles/Home";
import AdminHomePageCards from "./AdminHomePageCards";
import AdminHomePageChart from "./AdminHomePageChart";


export default function Home(){
    return(
        <AdminHomePageContainer>
            <AdminHomePageContent>
                <AdminHomePageCards />
                <AdminHomePageChart />
            </AdminHomePageContent>
        </AdminHomePageContainer>
    )
}