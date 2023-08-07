import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
const Authenticate = ({ children }) => {
  const haveLogin = useSelector((state) => state.auth.isLogin);
  const url = localStorage.getItem("url");

  if (!haveLogin && url == null) {
    return <Navigate to={"/auth"} />;
  }
  return { ...children };
};
export default Authenticate;
