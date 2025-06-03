import React, {useEffect, useState} from 'react';
import AuthContext from "../contexts/authContext.js";

const decode = (token) => {
    try {
        return JSON.parse(atob(token.split(".")[1]));
    } catch (error) {
        console.log(error);
        return null;
    }
};

const AuthProvider = ({children}) => {
    const [state, setState] = useState({
        user: null,
        isLoading: true,
    });

    const login = (jwtToken) => {
        const payload = decode(jwtToken);
        if (payload) {
            localStorage.setItem("token", jwtToken);
            setState({
                user: payload,
                isLoading: false,
            });
        } else {
            logout();
        }
    };

    const logout = () => {
        localStorage.removeItem("token");
        setState({
            user: null,
            isLoading: false,
        });
    }

    useEffect(() => {
        const jwtToken = localStorage.getItem("token");
        if (jwtToken) {
            const payload = decode(jwtToken);
            if (payload) {
                setState({
                    user: payload,
                    isLoading: false,
                });
            } else {
                logout();
            }
        } else {
            setState({
                user: null,
                isLoading: false,
            });
        }
    }, []);

    return (
        <AuthContext.Provider value={{login, logout, ...state, isLoggedIn: !!state.user}}>
            {children}
        </AuthContext.Provider>
    );
};

export default AuthProvider;