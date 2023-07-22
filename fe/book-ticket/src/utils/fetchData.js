import { SERVER } from "../assets/js/constants";
import authService from "../services/authService";

export const postData = (api, data = {}, options = {}) => {
  return fetch(api, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    ...options,
  }).then((res) => res.json());
};

export const getData = async (api, data = {}, options = {}) => {
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
    const rs = await authService.refeshToken({
      token: token.refeshToken,
    });
    console.log(rs);
    if (!rs.message) {
      localStorage.setItem("token", JSON.stringify(rs));
      getData(`${SERVER}accessToken/`);
    }
    // } else {
    //   return rs;
    //   // alert("Đăng nhập lại đi");
    // }
    return rs;
  }
  return response.json();
};
