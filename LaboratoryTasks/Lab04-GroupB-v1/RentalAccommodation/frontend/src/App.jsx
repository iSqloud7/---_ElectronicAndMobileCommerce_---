import {useState} from 'react'
import './App.css'

import {BrowserRouter, Routes, Route} from "react-router";

import Layout from "./UI/Components/Layout/Layout.jsx";
import HomePage from "./UI/Pages/HomePage/HomePage.jsx";
import BookingsPage from "./UI/Pages/BookingsPage/BookingsPage.jsx";
import HostsPage from "./UI/Pages/HostsPage/HostsPage.jsx";
import CountriesPage from "./UI/Pages/CountriesPage/CountriesPage.jsx";

function App() {
    const [count, setCount] = useState(0)

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="/bookings" element={<BookingsPage/>}/>
                    <Route path="/hosts" element={<HostsPage/>}/>
                    <Route path="/countries" element={<CountriesPage/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App
