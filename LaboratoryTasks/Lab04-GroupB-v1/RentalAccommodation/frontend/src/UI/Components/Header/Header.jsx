import React from 'react';
import "./Header.css"
import {AppBar, Box, Button, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu"
import {Link} from "react-router"

const pages = [
    {"path": "/", "name": "home"},
    {"path": "/bookings", name: "bookings"},
    {"path": "/hosts", name: "hosts"},
    {"path": "/countries", name: "countries"},

];

const Header = () => {
    return (
        <Box>
            <AppBar position="static">
                <Toolbar sx={{justifyContent: 'flex-start'}}>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{mr: 2}}
                    >
                    </IconButton>
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{mr: 3}}
                    >
                        Lab Group B - Bookings App
                    </Typography>
                    <Box
                        sx={{flexGrow: 1, display: {xs: "none", md: "flex"}}}
                    >
                        {/*    tuka gi prikazuvame linkovite so <Link> - React Router navigiranje bez da treba reload na strana
                               link - key/to
                        */}

                        {pages.map((item) => (
                            <Link key={item.name} to={item.path}>
                                <Button sx={{my: 2, color: "white", display: "block", textDecoration: "none"}}>
                                    {item.name}
                                </Button>
                            </Link>
                        ))}

                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Header;