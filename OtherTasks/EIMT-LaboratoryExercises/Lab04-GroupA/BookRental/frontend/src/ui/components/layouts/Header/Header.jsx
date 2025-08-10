import {AppBar, Box, Button, IconButton, Toolbar, Typography} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import {Link} from "react-router-dom";
import "./Header.css"


const pages = [
    {"path": "/", "name": "home"},
    {"path": "/books", "name": "books"},
    {"path": "/authors", "name": "authors"},
    {"path": "/countries", "name": "countries"},
];

const Header = () => {
    return (
        <Box>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{mr: 2}}>
                        <MenuIcon/>
                    </IconButton>
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{mr: 3}}>
                        Book Rental
                    </Typography>
                    <Box
                        sx={{flexGrow: 1, display: {xs: "none", md: "flex"}}}>
                        {
                            pages.map((page) => (
                                <Link key={page.name} to={page.path}>
                                    <Button
                                        sx={{my: 2, color: "white", display: "block", textDecoration: "none"}}>
                                        {page.name}
                                    </Button>
                                </Link>
                            ))
                        }
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Header;
