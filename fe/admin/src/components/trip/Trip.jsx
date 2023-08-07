import MaterialReactTable from "material-react-table";

export default function Trip(props) {
  return (
    <MaterialReactTable
      columns={props.columns}
      data={props.data}
      enableRowSelection
      enableColumnOrdering
      enableGlobalFilter={false}
    />
  );
}
