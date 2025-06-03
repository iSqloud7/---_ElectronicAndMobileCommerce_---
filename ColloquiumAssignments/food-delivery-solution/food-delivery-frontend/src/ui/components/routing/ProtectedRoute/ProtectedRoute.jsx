import React from 'react';
import useAuth from "../../../../hooks/useAuth.js";
import {Navigate, Outlet} from "react-router";

const ProtectedRoute = ({role}) => {
    const {isLoading, user} = useAuth();

    if (isLoading)
        return null;

    if (user === null)
        return <Navigate to="/login" replace/>;

    if (role && !user.roles.includes(role))
        return <Navigate to="/login" replace/>;

    return <Outlet/>;
};

export default ProtectedRoute;