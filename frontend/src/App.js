import React from "react";
import './assets/css/bootstrap.min.css';
import './assets/css/font-awesome.min.css';
import './assets/css/elegant-icons.css';
import './assets/css/nice-select.css';
import './assets/css/jquery-ui.min.css';
import './assets/css/owl.carousel.min.css';
import './assets/css/slicknav.min.css';
import './assets/css/style.css';

import Home from "./client/page/home";
import Shop from "./client/page/shop";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Blog from "./client/page/blog";
import Contact from "./client/page/contact";
import Login from "./client/page/login";
import {AuthProvider} from "./client/context/AuthContext";
import Register from "./client/page/register";

function App() {
    return (
        <BrowserRouter>
            <AuthProvider>
            <Routes>
                <Route path="/login" element={<Login />} />
                <Route path="register" element={<Register/>}/>
                <Route path="/" element={<Home />} />
                <Route path="/shop" element={<Shop/>} />
                <Route path="/blog" element={<Blog/>}/>
                <Route path="/contact" element={<Contact/>}/>
            </Routes>
            </AuthProvider>
        </BrowserRouter>
    );
}

export default App;