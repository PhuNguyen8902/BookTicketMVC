import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
const Authenticate = ({ children }) => {
  const haveLogin = useSelector((state) => state.auth.isLogin);

  if (!haveLogin) {
    return <Navigate to={"/auth"} />;
  }
  return { ...children };
};
export default Authenticate;
