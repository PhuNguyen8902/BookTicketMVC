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
export const postImg = async (api, data = {}, options = {}) => {
  let formData = new FormData();

  // Add each key-value pair in 'data' to the FormData
  // for (const key in data) {
  formData.append("file", data);
  // }
  console.log(data);
  const response = await fetch(api, {
    method: "POST",
    headers: {
      // "Content-Type": "multipart/form-data",
      // "Content-Type":
      //   "multipart/form-data;boundary=----MyUniqueBoundary123456789",
    },
    // body: JSON.stringify(data),
    body: data,

    ...options,
  });

  const responseData = await response.json();
  console.log(responseData);
  return responseData;
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
  if (response.status === 401) {
    const rs = await authService.refeshToken({
      token: token.refeshToken,
    });
    console.log(rs);
    if (!rs.message) {
      localStorage.setItem("token", JSON.stringify(rs));
      getData(`${SERVER}auth/accessToken/`);
    }

    return rs;
  }
  return response.json();
};

export const getTest = async (api, data = {}, options = {}) => {
  const token = JSON.parse(localStorage.getItem("token"));

  // Kiểm tra token tồn tại
  if (!token) {
    console.log(
      "Không có token, xử lý sự kiện không có quyền truy cập hoặc chuyển hướng đến trang đăng nhập."
    );
    // Xử lý sự kiện không có quyền truy cập hoặc chuyển hướng đến trang đăng nhập
    // Chọn một trong hai phương pháp, tùy thuộc vào yêu cầu của ứng dụng của bạn.
    // Ví dụ:
    // window.location.href = "/login"; // Chuyển hướng đến trang đăng nhập
    // return; // Dừng xử lý hàm
  }

  try {
    const response = await fetch(api, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token.accessToken}`,
      },
      ...options,
    });

    // Xử lý kết quả từ API
    return await response.json();
  } catch (error) {
    console.log("Lỗi khi gọi API:", error);
    // Xử lý lỗi nếu cần thiết
  }
};
