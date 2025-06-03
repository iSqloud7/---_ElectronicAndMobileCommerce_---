import {useContext} from "react";
import AuthContext from "../contexts/authContext.js";

const useAuth = () => useContext(AuthContext);

export default useAuth;