import { useSelector } from "react-redux";
import ProfileAccount from "../components/profileAccount/ProfileAccount";
import { IsLoading } from "../components";


export default function ProfileAcountPage(){
    const selectUser = (state) => state.auth.isLogin;
    const isLogin = useSelector(selectUser);
    return(
        isLogin ? <ProfileAccount /> : <IsLoading/>
    );
}