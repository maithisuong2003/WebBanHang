import React, {useEffect, useState} from "react";
import Header from "../components/Header";
import {API_BASE_URL} from "../service/AuthService";
import axios from "axios";
import Footer from "../components/Footer";
import {useNavigate} from "react-router-dom";

const ShopingCart = () => {
    const [cartItems, setCartItems] = useState([]);
    const token = localStorage.getItem('token');
    const [cart, setCart] = useState(null);
    const navigate = useNavigate();
    useEffect(() => {
        if (!token) {
            navigate("/login");
            return;
        }
        axios.get(`${API_BASE_URL}/carts/my-cart`, {
            headers: {
                 Authorization: `Bearer ${token}`
            }
        })
            .then(res => {
                if(res.data && res.data.result) {
                    setCart(res.data.result);
                    setCartItems(res.data.result.cartItems || []);
                }
            })
            .catch(err => {
                console.error("Error fetching cart:", err);
            });
    }, []);
    return (
        <div>
            <Header/>

            <section
                className="breadcrumb-section set-bg"
                style={{ backgroundImage: `url(img/breadcrumb.jpg)` }}
            >
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12 text-center">
                            <div className="breadcrumb__text">
                                <h2>Shopping Cart</h2>
                                <div className="breadcrumb__option">
                                    <a href="./index.html">Home</a>
                                    <span>Shopping Cart</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* Shoping Cart Section */}
            <section className="shoping-cart spad">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="shoping__cart__table">
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="shoping__product">Products</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {cartItems && cartItems.length > 0 ? cartItems.map(item => (
                                        <tr key={item.id}>
                                            <td className="shoping__cart__item">
                                                <img src={item.productEntity.imageUrl} alt=""/>
                                                <h5>{item.productEntity.nameProduct}</h5>
                                            </td>
                                            <td className="shoping__cart__price">
                                                {item.price?.toLocaleString()} đ
                                            </td>
                                            <td className="shoping__cart__quantity">
                                                <div className="quantity">
                                                    <div className="pro-qty">
                                                        <input
                                                            type="text"
                                                            value={item.quantity}
                                                            readOnly
                                                        />
                                                    </div>
                                                </div>
                                            </td>
                                            <td className="shoping__cart__total">
                                                {(item.price * item.quantity)?.toLocaleString()} đ
                                            </td>
                                            <td className="shoping__cart__item__close">
                                                <span className="icon_close"></span>
                                            </td>
                                        </tr>
                                    )) : (
                                        <tr>
                                            <td colSpan={5}>Không có sản phẩm nào trong giỏ hàng.</td>
                                        </tr>
                                    )}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="shoping__cart__btns">
                                <a href="/shop" className="primary-btn cart-btn">
                                    CONTINUE SHOPPING
                                </a>
                                <a href="#" className="primary-btn cart-btn cart-btn-right">
                                    <span className="icon_loading"></span> Update Cart
                                </a>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="shoping__continue">
                                <div className="shoping__discount">
                                    <h5>Discount Codes</h5>
                                    <form action="#">
                                    <input
                                            type="text"
                                            placeholder="Enter your coupon code"
                                        />
                                        <button type="submit" className="site-btn">
                                            APPLY COUPON
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="shoping__checkout">
                                <h5>Cart Total</h5>
                                <ul>
                                    <li>
                                        Subtotal <span>$454.98</span>
                                    </li>
                                    <li>
                                        Tổng tiền: <span>{cart?.totalPrice?.toLocaleString()} đ</span>
                                    </li>
                                </ul>
                                <a href="/checkout" className="primary-btn">
                                PROCEED TO CHECKOUT
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <Footer/>

        </div>
    );
};

export default ShopingCart;