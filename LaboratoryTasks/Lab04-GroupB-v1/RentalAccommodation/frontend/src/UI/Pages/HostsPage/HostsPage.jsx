import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import useHosts from "../../../Hooks/useHosts.js";
import HostGrid from "../../Components/Hosts/HostGrid/HostGrid.jsx";
import AddHostDialog from "../../Components/Hosts/AddHostDialog/AddHostDialog.jsx";

const HostsPage = () => {

    const {hosts, loading, onAdd, onDelete, onEdit} = useHosts()
    const [addHostDialogOpen, setAddHostDialogOpen] = useState(false)

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddHostDialogOpen(true)}>
                                Add Host
                            </Button>
                        </Box>
                        <HostGrid hosts={hosts} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddHostDialog
                open={addHostDialogOpen}
                onClose={() => setAddHostDialogOpen(false)}
                onAdd={onAdd}
            />
        </>

    );
};

export default HostsPage;