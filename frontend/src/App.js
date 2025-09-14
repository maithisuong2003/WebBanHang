import React from "react";
import './assets/css/bootstrap.min.css';
import './assets/css/font-awesome.min.css';
import './assets/css/elegant-icons.css';
import './assets/css/nice-select.css';
import './assets/css/jquery-ui.min.css';
import './assets/css/owl.carousel.min.css';
import './assets/css/slicknav.min.css';
import './assets/css/style.css';

import Index from "./page/index";
import Shop from "./page/shop";

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Blog from "./page/blog";
import Contact from "./page/contact";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Index />} />
                <Route path="/shop" element={<Shop/>} />
                <Route path="/blog" element={<Blog/>}/>
                <Route path="/contact" element={<Contact/>}/>

            </Routes>
        </BrowserRouter>
    );
}

export default App;