import React from 'react';
import {Grid} from "@mui/material";
import HostCard from "../HostCard/HostCard.jsx";

const HostGrid = ({hosts,onEdit,onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {hosts.map((host) => (
                <Grid key={host.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <HostCard host={host} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>

    );
};

export default HostGrid;