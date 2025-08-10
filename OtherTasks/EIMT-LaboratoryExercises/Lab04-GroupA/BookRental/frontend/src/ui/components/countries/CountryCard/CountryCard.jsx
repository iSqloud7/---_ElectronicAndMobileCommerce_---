import {useNavigate} from "react-router";
import {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import EditCountryDialog from '../EditCountryDialog/EditCountryDialog';
import DeleteCountryDialog from "../DeleteCountryDialog/DeleteCountryDialog";
import './CountryCard.css';

const CountryCard = ({country, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editCountryDialogOpen, setEditCountryDialogOpen] = useState(false);
    const [deleteCountryDialogOpen, setDeleteCountryDialogOpen] = useState(false);

    return (
        <>
            <Card
                sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography
                        variant="h5">
                        {country.name}
                    </Typography>
                </CardContent>
                <CardActions
                    sx={{justifyContent: 3,}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        sx={{mr: "0.25rem"}}
                        onClick={() => navigate(`/countries/${country.countryID}`)}>
                        Info!
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditCountryDialogOpen(true)}>
                            Edit country!
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteCountryDialogOpen(true)}>
                            Delete country!
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditCountryDialog
                open={editCountryDialogOpen}
                onClose={() => setEditCountryDialogOpen(false)}
                country={country}
                onEdit={onEdit}/>
            <DeleteCountryDialog
                open={deleteCountryDialogOpen}
                onClose={() => setDeleteCountryDialogOpen(false)}
                country={country}
                onDelete={onDelete}/>
        </>
    );
};

export default CountryCard;
