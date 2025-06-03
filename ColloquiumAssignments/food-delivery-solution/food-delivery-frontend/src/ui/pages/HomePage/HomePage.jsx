import React from 'react';
import {Box, Container, Typography} from "@mui/material";

const HomePage = () => {
    return (
        <Box sx={{m:0, p:0}}>
            <Container maxWidth="xl" sx={{mt:3, py: 3}} className="welcome-section">
                <Typography variant="h4" gutterBottom>
                    Welcome to Food Delivery App! 👋
                </Typography>
                <Typography variant="body1" sx={{ mb: 4 }}>
                    This is the home page.
                </Typography>
            </Container>
        </Box>
    );
};

export default HomePage;