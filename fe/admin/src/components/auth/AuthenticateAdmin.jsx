import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Navigate } from "react-router-dom";
import AuthenPage from "../../routes/AuthenPage";
import authService from "../../service/authService";
import { close403 } from "../../store/slices/authSlice";

const AuthenticateAdmin = ({ children }) => {
  const is403 = useSelector((state) => state.auth.is403);
  const dispatch = useDispatch();

  useEffect(() => {
    const check = async () => {
      const response = await authService.checkAdmin();
      if (response.status === 403) {
        console.log("403 roi");
        dispatch(close403());
      }
    };

    check();
  }, [dispatch]);

  if (is403) {
    return <AuthenPage />;
  }

  return { ...children };
};

export default AuthenticateAdmin;
