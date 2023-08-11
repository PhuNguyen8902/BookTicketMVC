import MaterialReactTable from "material-react-table";
import routeService from "../../service/routeService";
import { useEffect, useState } from "react";
import { Box, Button, IconButton, Pagination, Tooltip } from "@mui/material";
import { SERVER } from "../../assets/js/constants";
import { Delete, Edit } from "@mui/icons-material";
import { CreateNewRouteModal, EditRoute } from "../../components/index";
import stationService from "../../service/stationService";
import { useCallback } from "react";
import AlertDeleteRoute from "./AlertDeleteRoute";

export default function RouteEmployee() {
  //   const [createModalOpen, setCreateModalOpen] = useState(false);
  //   const [editOpen, setEditOpen] = useState(false);
  //   const [deleteOpen, setDeleteOpen] = useState(false);
  //   const [form, setForm] = useState({});
  const [data, setData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  //   const [nameStation, setNameStation] = useState([]);

  const columns = [
    {
      header: "Id",
      accessorKey: "id",
      type: "disable",
    },
    {
      header: "Name",
      accessorKey: "name", //simple accessorKey pointing to flat data
    },
    {
      header: "Start Station",
      accessorKey: "startStation",
      type: "comboBox",
      //simple accessorKey pointing to flat data
    },
    {
      header: "End Station",
      accessorKey: "endStation", //simple accessorKey pointing to flat data
      type: "comboBox",
    },
    {
      header: "Distance",
      accessorKey: "distance", //simple accessorKey pointing to flat data
    },
    {
      header: "Duration",
      accessorKey: "duration", //simple accessorKey pointing to flat data
    },
  ];
  //fetch dau tien
  const fetchData = async (api) => {
    const rs = await routeService.getRoute(api);
    if (!rs.message) {
      setData(rs);
    } else {
      alert("Error");
    }
  };
  //   const fetchNameStation = async () => {
  //     try {
  //       const result = await stationService.getNameStation();
  //       let arr = [];
  //       result.forEach((rs) => {
  //         arr.push({ name: rs.name, id: rs.id });
  //       });

  //       setNameStation(arr);
  //     } catch (error) {
  //       console.error("Error fetching data:", error);
  //     }
  //   };
  useEffect(() => {
    const queryParams = new URLSearchParams(window.location.search);
    const page = queryParams.get("page");
    if (page == null) {
      queryParams.set("page", 1);
    } else {
      setCurrentPage(page);
    }
    fetchData(`${SERVER}route?${queryParams.toString()}`);
    // fetchNameStation();
  }, []);
  //---------------------------------------------------
  // handle Open
  //   const handleEdit = (row) => {
  //     setEditOpen(true);
  //     setForm(row._valuesCache);
  //   };
  //   const handleDeleteRow = useCallback((row) => {
  //     setDeleteOpen(true);
  //     setForm(row._valuesCache);
  //   }, []);

  //---------------------------------------------------
  // handle Close

  //   const handleCloseDeleteRow = useCallback(() => {
  //     fetchData(`${SERVER}route?page=1`);
  //     setDeleteOpen(false);
  //   }, []);
  //   const handleCloseEditRoute = () => {
  //     const queryParams = new URLSearchParams(window.location.search);
  //     const page = queryParams.get("page");
  //     if (page == null) {
  //       queryParams.set("page", 1);
  //     } else {
  //       setCurrentPage(page);
  //     }
  //     fetchData(`${SERVER}route?${queryParams.toString()}`);

  //     setEditOpen(false);
  //   };
  //   const handleCloseNewModal = () => {
  //     const queryParams = new URLSearchParams(window.location.search);
  //     const page = queryParams.get("page");
  //     if (page == null) {
  //       queryParams.set("page", 1);
  //     } else {
  //       setCurrentPage(page);
  //     }
  //     fetchData(`${SERVER}route?${queryParams.toString()}`);

  //     setCreateModalOpen(false);
  //   };
  //---------------------------------------------------

  const handleSearch = (newSearchValue) => {
    if (newSearchValue != undefined) {
      const queryParams = new URLSearchParams(window.location.search);
      if (newSearchValue == "") {
        queryParams.delete("kw");
        queryParams.set("page", 1);
        setCurrentPage(1);
      } else {
        queryParams.set("kw", newSearchValue);
        queryParams.set("page", 1);
        setCurrentPage(1);
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

  return (
    <>
      <MaterialReactTable
        columns={columns}
        data={data}
        enablePagination={false}
        onGlobalFilterChange={handleSearch}
        // editingMode="modal"
        // enableEditing
        // renderRowActions={({ row, table }) => (
        //   <Box sx={{ display: "flex", gap: "1rem" }}>
        //     <Tooltip arrow placement="left" title="Edit">
        //       <IconButton onClick={() => handleEdit(row)}>
        //         <Edit />
        //       </IconButton>
        //     </Tooltip>
        //     <Tooltip arrow placement="right" title="Delete">
        //       <IconButton color="error" onClick={() => handleDeleteRow(row)}>
        //         <Delete />
        //       </IconButton>
        //     </Tooltip>
        //   </Box>
        // )}
        // renderTopToolbarCustomActions={() => (
        //   <Button onClick={() => setCreateModalOpen(true)} variant="contained">
        //     Create New Account
        //   </Button>
        // )}
      />
      {/* <CreateNewRouteModal
        columns={columns}
        open={createModalOpen}
        onClose={handleCloseNewModal}
        nameStation={nameStation}
      />
      {editOpen && (
        <EditRoute
          form={form}
          open={editOpen}
          nameStation={nameStation}
          onClose={handleCloseEditRoute}
        />
      )}
      {deleteOpen && (
        <AlertDeleteRoute
          open={deleteOpen}
          form={form}
          onClose={handleCloseDeleteRow}
          nameStation={nameStation}
        />
      )} */}

      <Box sx={{ display: "flex", justifyContent: "center" }}>
        {data.length > 0 && (
          <Pagination
            count={data[0].totalPage}
            showFirstButton
            showLastButton
            onChange={handlePageChange}
            page={currentPage}
          />
        )}
      </Box>
    </>
  );
}
