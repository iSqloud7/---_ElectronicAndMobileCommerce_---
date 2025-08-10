import {Box, Container} from "@mui/material";
import React from "react";
import {Outlet} from "react-router"

import "./Layout.css"
import Header from "../Header/Header.jsx";

const Layout = () => {

    return (
        <Box className="layout-box">
            <Header/>
            <Container className="outlet-container" sx={{my: 2}}>
                <Outlet/>
            </Container>
        </Box>
    )
}

export default Layout;