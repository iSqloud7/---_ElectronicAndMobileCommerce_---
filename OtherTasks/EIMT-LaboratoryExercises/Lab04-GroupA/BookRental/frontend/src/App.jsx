import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Layout from "./ui/components/layouts/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import BookPage from "./ui/pages/BookPage/BookPage.jsx";
import AuthorPage from "./ui/pages/AuthorPage/AuthorPage.jsx";
import CountryPage from "./ui/pages/CountryPage/CountryPage.jsx";
import BookDetails from "./ui/components/books/BookDetails/BookDetails.jsx";
import AuthorDetails from "./ui/components/authors/AuthorDetails/AuthorDetails.jsx";
import CountryDetails from "./ui/components/countries/CountryDetails/CountryDetails.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route path="books" element={<BookPage/>}/>
                    <Route path="books/:id" element={<BookDetails/>}/>

                    <Route path="authors" element={<AuthorPage/>}/>
                    <Route path="authors/:id" element={<AuthorDetails/>}/>

                    <Route path="countries" element={<CountryPage/>}/>
                    <Route path="countries/:id" element={<CountryDetails/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;
