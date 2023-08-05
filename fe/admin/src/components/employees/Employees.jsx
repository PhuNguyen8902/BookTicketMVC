import { TableContainer, TableContent } from "../../assets/styles/TablePage";
import TableDataGrid from "../utils/TableDataGrid";


const rows = [
   
];

const columns = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'name', headerName: 'name', width: 130 },
    {
        field: 'email',
        headerName: 'email',
        width: 90,
    },
    {
        field: 'phone',
        headerName: 'phone',
        type: 'number',
        width: 160,
    },
];

export default function Employees() {
    return (
        <TableContainer>
            <TableContent>
                <TableDataGrid 
                row={rows}
                col={columns}
                />
            </TableContent>
        </TableContainer>
    )
}