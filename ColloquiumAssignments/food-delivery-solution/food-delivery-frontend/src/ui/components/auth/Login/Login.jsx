import React, {useState} from 'react';
import {
    Box, TextField, Button, Typography, Container, Paper
} from '@mui/material';
import {useNavigate} from "react-router";
import userRepository from "../../../../repository/userRepository.js";
import useAuth from "../../../../hooks/useAuth.js";

const initialFormData = {
    "username": "",
    "password": "",
};

const Login = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState(initialFormData);

    const {login} = useAuth();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        userRepository
            .login(formData)
            .then((response) => {
                console.log("The user is logged in successfully!");
                login(response.data.token);
                setFormData(initialFormData);
                navigate("/", {replace: true});
            })
            .catch((error) => console.log(error));
    };

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{padding: 4, mt: 8}}>
                <Typography variant="h5" align="center" gutterBottom>Login</Typography>
                <Box>
                    <TextField
                        fullWidth
                        label="Username"
                        name="username"
                        margin="normal"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                    <TextField
                        fullWidth
                        label="Password"
                        name="password"
                        type="password"
                        margin="normal"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                    <Button
                        fullWidth
                        variant="contained"
                        type="submit"
                        sx={{mt: 2}}
                        onClick={handleSubmit}
                        className="submit-btn">
                        Login
                    </Button>
                    <Button
                        fullWidth
                        variant="outlined"
                        type="submit"
                        sx={{mt: 2}}
                        onClick={() => navigate("/register")}
                    >
                        Register
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
};

export default Login;
