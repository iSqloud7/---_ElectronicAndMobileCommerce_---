import useUsers from "../Hooks/useUsers"

export default function UserList() {

    const [users, loading] = useUsers()

    if (loading === true) {
        return (
            <p>Loading...</p>
        )
    }

    return (
        <div>
            <h1>User List:</h1>
            <ul>
                {users.map(user => (
                    <li key={user.id}>{user.name}</li>
                ))}
            </ul>
        </div>
    )
}

// Components -> Hooks -> Repository -> Backend
// Backend -> Repository -> Hooks -> Components