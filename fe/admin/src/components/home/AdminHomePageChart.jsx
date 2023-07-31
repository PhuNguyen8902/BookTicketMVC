import { AdminHomePageCardsContent, AdminHomePageChartContainer, AdminHomePageChartTitle } from "../../assets/styles/Home";
import React, { PureComponent } from 'react';
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Legend } from 'recharts';

const data1 = [
    { name: 'Jan', sales: 60 },
    { name: 'Feb', sales: 100 },
    { name: 'Mar', sales: 150 },
    { name: 'Apr', sales: 50 },
];

const data2 = [
    { name: 'Jan', sales: 80 },
    { name: 'Feb', sales: 150 },
    { name: 'Mar', sales: 120 },
    { name: 'Apr', sales: 70 },
];


export default function AdminHomePageChart() {

    return (
        <AdminHomePageChartContainer>
            <AdminHomePageCardsContent>
                <AdminHomePageChartTitle variant="h5">Annual ticket sales</AdminHomePageChartTitle>
                    <AreaChart width={800} height={400} data={data1}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="name" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Area type="monotone" dataKey="sales" fill="#8884d8" stroke="#8884d8" />
                        <Area type="monotone" dataKey="sales" fill="#82ca9d" stroke="#82ca9d" />
                    </AreaChart>
            </AdminHomePageCardsContent>
        </AdminHomePageChartContainer>
    )
}