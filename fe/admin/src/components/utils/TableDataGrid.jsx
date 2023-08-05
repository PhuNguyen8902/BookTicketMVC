import { DataGrid } from '@mui/x-data-grid';



export default function TableDataGrid(props) {
    return (
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid
                rows={props.row}
                columns={props.col}
                initialState={{
                    pagination: {
                        paginationModel: { page: 0, pageSize: 10 },
                    },
                }}
                checkboxSelection
            />
        </div>
    )
}