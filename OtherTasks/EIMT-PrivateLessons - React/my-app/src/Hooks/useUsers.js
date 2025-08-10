import { useEffect, useState } from "react"
import userRepository from "../Repository/userRepository"

// const [users, setUsers] = useState([])
// const [loading, setLoading] = useState(true)

const initialState = {
    "users": [],
    "loading": true
}

const useUsers = () => {
    const [state, setState] = useState(initialState)

    useEffect(() => {
        userRepository
        .findAll()
        .then((response) => {
            setState({
                "users": response.data,
                "loading": false
            })
        })
        .catch((error) => console.log(error))
    }, [])

    return state
}

export default useUsers