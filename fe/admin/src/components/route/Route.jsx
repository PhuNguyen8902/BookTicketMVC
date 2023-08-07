import MaterialReactTable from "material-react-table";

export default function Route(props) {
  return (
    <MaterialReactTable
      columns={props.columns}
      data={props.data}
      muiTablePaginationProps={{
        rowsPerPageOptions: [],
      }}
      // enableRowSelection
      // enableColumnOrdering
      // enableGlobalFilter={false}
    />
  );
}
