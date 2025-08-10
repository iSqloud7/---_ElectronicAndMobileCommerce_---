import {Box, Container} from "@mui/material";
import Header from "../Header/Header.jsx";
import {Outlet} from "react-router-dom";

const Layout = () => {
    return (
        <Box
            className="layout-box">
            <Header/>
            <Container
                className="outlet-container"
                sx={{my: 2}}
                maxWidth="xl">
                <Outlet/>
            </Container>
        </Box>
    );
};

export default Layout;
