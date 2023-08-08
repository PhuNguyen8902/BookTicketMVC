import routeService from "../../service/routeService";
import { useEffect, useState } from "react";
import { Pagination } from "@mui/material";

export default function CustomPagination() {
  const [currentPage, setCurrentPage] = useState(1); // Trang hiện tại

  const handlePageChange = (event, newPage) => {
    const queryParams = new URLSearchParams(window.location.search);
    queryParams.set("page", newPage);
    const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
    window.history.pushState({ path: newUrl }, "", newUrl);

    setCurrentPage(newPage); // Cập nhật trang hiện tại khi click
    // Thực hiện hành động của bạn ở đây, ví dụ: gọi API để lấy dữ liệu trang mới
  };
  const [page, setPage] = useState(1);

  const fetchTotalPage = async () => {
    const rs = await routeService.getTotalPage();
    if (!rs.message) {
      setPage(rs);
    } else {
      alert("Error");
    }
  };
  useEffect(() => {
    fetchTotalPage();
  }, []);
  return (
    <Pagination
      count={page}
      showFirstButton
      showLastButton
      onChange={handlePageChange} // Gọi hàm khi có sự kiện click trên nút chuyển trang
      page={currentPage}
    />
  );
}
