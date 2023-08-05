import { useSelector } from "react-redux";
import {
  ProfileContainer,
  ProfileContent,
} from "../../assets/styles/profileAcountPage";

export default function Profile() {
  const selectUser = (state) => state.auth.user;
  const user = useSelector(selectUser);
  return (
    <ProfileContainer>
      <ProfileContent>
        <h1>Email:{user.email}</h1>
        <h1>Name:{user.name}</h1>
        <h1>
          Avatar:
          <img src={user.avatar} />
        </h1>
      </ProfileContent>
    </ProfileContainer>
  );
}
