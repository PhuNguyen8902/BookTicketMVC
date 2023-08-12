import { useDispatch } from "react-redux";
import { SERVER } from "../assets/js/constants";
import authService from "../service/authService";
import { set403 } from "../store/slices/authSlice";
// import authService from "../services/authService";

export const postData = (api, options = {}) => {
  return fetch(api, {
    method: "POST",
    // headers: {
    //   "Content-Type": "application/json",
    // },
    // body: JSON.stringify(data),
    ...options,
  }).then((res) => res.json());
};
export const getData = (api, options = {}) => {
  return fetch(api, {
    method: "GET",
    // headers: {
    //   "Content-Type": "application/json",
    // },
    // body: JSON.stringify(data),
    ...options,
  }).then((res) => res.json());
};
export const putData = (api, options = {}) => {
  return fetch(api, {
    method: "PUT",
    // headers: {
    //   "Content-Type": "application/json",
    // },
    // body: JSON.stringify(data),
    ...options,
  }).then((res) => res.json());
};
export const getDataWithToken = async (api, data = {}, options = {}) => {
  const token = JSON.parse(localStorage.getItem("token"));
  if (!token) {
    return {
      status: 403, // loi quyen
      message: "Forbiden",
    };
  }
  const response = await fetch(api, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token.accessToken}`,
    },
    ...options,
  });
  if (response.status === 403) {
    localStorage.removeItem("token");
    return response;
  }
  if (response.status === 401) {
    const rs = await authService.refeshToken({
      token: token.refeshToken,
    });
    console.log(rs);
    if (!rs.message) {
      localStorage.setItem("token", JSON.stringify(rs));
      const rs2 = await getDataWithToken(`${SERVER}employee/accessToken/`);
      return rs2;
    } else {
      return rs;
    }
  } else {
    return response.json();
  }
};
