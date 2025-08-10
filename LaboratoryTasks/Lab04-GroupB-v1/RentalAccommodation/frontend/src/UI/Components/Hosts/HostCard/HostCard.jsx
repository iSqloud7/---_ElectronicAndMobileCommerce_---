import React, {useState} from 'react';
import {useNavigate} from "react-router";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import EditHostDialog from "../EditHostDialog/EditHostDialog.jsx";
import DeleteHostDialog from "../DeleteHostDialog/DeleteHostDialog.jsx";
import useCountries from "../../../../Hooks/useCountries.js";

const HostCard = ({host, onDelete, onEdit}) => {

    const navigate = useNavigate();
    const [editHostDialogOpen, setEditHostDialogOpen] = useState(false)
    const [deleteHostDialogOpen, setDeleteHostDialogOpen] = useState(false)

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{host.name} {host.surname}</Typography>
                    <Typography variant="subtitle2">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                        ducimus enim modi natus odit quibusdam veritatis.
                    </Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{textAlign: "right", fontSize: "1.25rem"}}>Country
                        ID: {host.countryId}</Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditHostDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteHostDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditHostDialog
                open={editHostDialogOpen}
                onClose={() => setEditHostDialogOpen(false)}
                host={host}
                onEdit={onEdit}
            />
            <DeleteHostDialog
                open={deleteHostDialogOpen}
                onClose={() => setDeleteHostDialogOpen(false)}
                host={host}
                onDelete={onDelete}
            />
        </>

    );
};

export default HostCard;