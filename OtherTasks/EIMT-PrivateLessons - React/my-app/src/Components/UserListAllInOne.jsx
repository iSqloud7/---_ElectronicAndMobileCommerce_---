// import { useEffect, useState } from "react"

// export default function UserListAllInOne() {

//     // State to store the list of users.
//     const [users, setUsers] = useState([])
//     const [loading, setLoading] = useState(true)
//     const [error, setError] = useState(null)

//     // Fetch the data when the component mounts.
//     useEffect(() => {
//         // Make the API call.
//         fetch('#') // 'https://jsonplaceholder.typicode.com/users'
//             .then(response => {
//                 if (!response.ok) {
//                     throw new Error("Network response was not ok!")
//                 }

//                 return response.json()
//             })
//             .then(response => {
//                 setUsers(response) // Set users data to state.
//                 setLoading(false) // Set loading to false after data is fetched.

//             })
//             .catch(error => {
//                 setError(error.message) // Set error if there is an issue.
//                 setLoading(false) // Set loading to false even is there is an error.
//             })
//     }, []) // Empty array means it runs only once when the component mounts.

//     if (loading === true) {
//         return (
//             <p>Loading...</p>
//         )
//     }

//     if (error !== null) {
//         return (
//             <p>{error}</p>
//         )
//     } 

//     return (
//         <div>
//             <h1>User List:</h1>
//             <ul>
//                 {users.map(user => (
//                     <li key={user.id}>{user.name}</li>
//                 ))}
//             </ul>
//         </div>
//     )
// }