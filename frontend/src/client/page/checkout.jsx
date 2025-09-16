import React, {useEffect, useState} from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import axios from "axios";
import {API_BASE_URL} from "../service/AuthService";
import {useNavigate} from "react-router-dom";

const Checkout = () => {
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
            <Header />
            <section
                className="breadcrumb-section set-bg"
                style={{ backgroundImage: `url(/img/breadcrumb.jpg)` }}
            >
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12 text-center">
                            <div className="breadcrumb__text">
                                <h2>Checkout</h2>
                                <div className="breadcrumb__option">
                                    <a href="/">Home</a>
                                    <span>Checkout</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* Checkout Section */}
            <section className="checkout spad">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <h6>
                                <span className="icon_tag_alt"></span> Have a coupon?{" "}
                                <a href="#">Click here</a> to enter your code
                            </h6>
                        </div>
                    </div>
                    <div className="checkout__form">
                        <h4>Billing Details</h4>
                        <form>
                            <div className="row">
                                <div className="col-lg-8 col-md-6">
                                    <div className="row">
                                        <div className="col-lg-6">
                                            <div className="checkout__input">
                                                <p>First Name<span>*</span></p>
                                                <input type="text" />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="checkout__input">
                                                <p>Last Name<span>*</span></p>
                                                <input type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="checkout__input">
                                        <p>Country<span>*</span></p>
                                        <input type="text" />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Address<span>*</span></p>
                                        <input type="text" placeholder="Street Address" className="checkout__input__add" />
                                        <input type="text" placeholder="Apartment, suite, unite ect (optional)" />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Town/City<span>*</span></p>
                                        <input type="text" />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Country/State<span>*</span></p>
                                        <input type="text" />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Postcode / ZIP<span>*</span></p>
                                        <input type="text" />
                                    </div>
                                    <div className="row">
                                        <div className="col-lg-6">
                                            <div className="checkout__input">
                                                <p>Phone<span>*</span></p>
                                                <input type="text" />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="checkout__input">
                                                <p>Email<span>*</span></p>
                                                <input type="text" />
                                            </div>
                                        </div>
                                    </div>
                                    <div className="checkout__input__checkbox">
                                        <label htmlFor="acc">
                                            Create an account?
                                            <input type="checkbox" id="acc" />
                                            <span className="checkmark"></span>
                                        </label>
                                    </div>
                                    <p>
                                        Create an account by entering the information below. If you are a returning customer
                                        please login at the top of the page
                                    </p>
                                    <div className="checkout__input">
                                        <p>Account Password<span>*</span></p>
                                        <input type="text" />
                                    </div>
                                    <div className="checkout__input__checkbox">
                                        <label htmlFor="diff-acc">
                                            Ship to a different address?
                                            <input type="checkbox" id="diff-acc" />
                                            <span className="checkmark"></span>
                                        </label>
                                    </div>
                                    <div className="checkout__input">
                                        <p>Order notes<span>*</span></p>
                                        <input type="text" placeholder="Notes about your order, e.g. special notes for delivery." />
                                    </div>
                                </div>
                                <div className="col-lg-4 col-md-6">
                                    <div className="checkout__order">
                                        <h4>Your Order</h4>
                                        <div className="checkout__order__products">
                                            Products <span>Total</span>
                                        </div>
                                        <ul>
                                            {cartItems.map((item, idx) => (
                                                <li key={idx}>
                                                    {item.productEntity.nameProduct} <span>${item.price.toFixed(2)}</span>
                                                </li>
                                            ))}
                                        </ul>
                                        <div className="checkout__order__subtotal">
                                            Tổng tiền: <span>{cart?.totalPrice?.toLocaleString()} đ</span>
                                        </div>
                                        <div className="checkout__order__total">
                                            Tổng tiền: <span>{cart?.totalPrice?.toLocaleString()} đ</span>
                                        </div>
                                        <div className="checkout__input__checkbox">
                                            <label htmlFor="acc-or">
                                                Create an account?
                                                <input type="checkbox" id="acc-or" />
                                                <span className="checkmark"></span>
                                            </label>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adip elit, sed do eiusmod tempor incididunt
                                            ut labore et dolore magna aliqua.
                                        </p>
                                        <div className="checkout__input__checkbox">
                                            <label htmlFor="payment">
                                                Check Payment
                                                <input type="checkbox" id="payment" />
                                                <span className="checkmark"></span>
                                            </label>
                                        </div>
                                        <div className="checkout__input__checkbox">
                                            <label htmlFor="paypal">
                                                Paypal
                                                <input type="checkbox" id="paypal" />
                                                <span className="checkmark"></span>
                                            </label>
                                        </div>
                                        <button type="submit" className="site-btn">
                                            PLACE ORDER
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
            <Footer />
        </div>
    );
};

export default Checkout;