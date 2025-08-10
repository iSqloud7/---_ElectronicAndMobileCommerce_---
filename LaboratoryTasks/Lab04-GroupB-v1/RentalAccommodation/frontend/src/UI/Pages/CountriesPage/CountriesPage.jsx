import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import useCountries from "../../../Hooks/useCountries.js";
import CountryGrid from "../../Components/Countries/CountryGrid/CountryGrid.jsx";
import AddCountryDialog from "../../Components/Countries/AddCountryDialog/AddCountryDialog.jsx";

const CountriesPage = () => {

    const {countries, loading, onDelete, onEdit, onAdd} = useCountries()

    const [addCountryDialogOpen, setAddCountryDialogOpen] = useState(false)


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
                            <Button variant="contained" color="primary" onClick={() => setAddCountryDialogOpen(true)}>
                                Add Country
                            </Button>
                        </Box>
                        <CountryGrid countries={countries} onDelete={onDelete} onEdit={onEdit}/>
                    </>}
            </Box>
            <AddCountryDialog
                open={addCountryDialogOpen}
                onClose={() => setAddCountryDialogOpen(false)}
                onAdd={onAdd}
            />
        </>

    );
};

export default CountriesPage;