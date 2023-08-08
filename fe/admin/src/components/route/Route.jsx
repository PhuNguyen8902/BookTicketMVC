import MaterialReactTable from "material-react-table";
import routeService from "../../service/routeService";
import { useEffect, useState } from "react";
import { Box, Button, IconButton, Pagination, Tooltip } from "@mui/material";
import { SERVER } from "../../assets/js/constants";
import { Delete, Edit } from "@mui/icons-material";

export default function Route(props) {
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const fetchData = async (api) => {
    const rs = await routeService.getRoute(api);
    if (!rs.message) {
      setData(rs);
    } else {
      alert("Error");
    }
  };
  const handleDeleteRow = () => {};
  const setCreateModalOpen = () => {};
  const handleSearch = (newSearchValue) => {
    if (newSearchValue != undefined) {
      const queryParams = new URLSearchParams(window.location.search);
      if (newSearchValue == "") {
        queryParams.delete("kw");
      } else {
        queryParams.set("kw", newSearchValue);
      }
      const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
      window.history.pushState({ path: newUrl }, "", newUrl);
      const api = `${SERVER}route?${queryParams.toString()}`;
      fetchData(api);
    }
  };
  const handlePageChange = (event, newPage) => {
    const queryParams = new URLSearchParams(window.location.search);
    queryParams.set("page", newPage);
    const newUrl = `${window.location.pathname}?${queryParams.toString()}`;
    window.history.pushState({ path: newUrl }, "", newUrl);

    setCurrentPage(newPage);

    const api = `${SERVER}route?${queryParams.toString()}`;
    fetchData(api);
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
    const queryParams = new URLSearchParams(window.location.search);
    const page = queryParams.get("page");
    if (page == null) {
      queryParams.set("page", 1);
    } else {
      setCurrentPage(page);
    }
    fetchData(`${SERVER}route?${queryParams.toString()}`);
  }, []);
  return (
    <>
      <MaterialReactTable
        columns={props.columns}
        data={data}
        enablePagination={false}
        onGlobalFilterChange={handleSearch}
        editingMode="modal"
        enableEditing
        renderRowActions={({ row, table }) => (
          <Box sx={{ display: "flex", gap: "1rem" }}>
            <Tooltip arrow placement="left" title="Edit">
              <IconButton onClick={() => table.setEditingRow(row)}>
                <Edit />
              </IconButton>
            </Tooltip>
            <Tooltip arrow placement="right" title="Delete">
              <IconButton color="error" onClick={() => handleDeleteRow(row)}>
                <Delete />
              </IconButton>
            </Tooltip>
          </Box>
        )}
        renderTopToolbarCustomActions={() => (
          <Button onClick={() => setCreateModalOpen(true)} variant="contained">
            Create New Account
          </Button>
        )}
      />

      <Box sx={{ display: "flex", justifyContent: "center" }}>
        <Pagination
          count={page}
          showFirstButton
          showLastButton
          onChange={handlePageChange}
          page={currentPage}
        />
      </Box>
    </>
  );
}
