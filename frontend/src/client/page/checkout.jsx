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
    const [address, setAddress] = useState("");
    const [note, setNote] = useState("");
    const [sale, setSale] = useState("");
    const [deliveryAt, setDeliveryAt] = useState("");
    const [paymentMethod, setPaymentMethod] = useState("cod");
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

    const handlePlaceOrder = async (e) => {
        e.preventDefault();
        const statusPay = paymentMethod === "cod" ? "Chưa thanh toán" : "Đã thanh toán";
        try {
            const orderRequest = {
                address,
                note,
                sale,
                statusPay,
                deliveryAt: deliveryAt ? new Date(deliveryAt).toISOString() : null
            };
            const response = await axios.post(`${API_BASE_URL}/orders`, orderRequest, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`
                }
            });
            alert("Đặt hàng thành công!");
            navigate("/orders");
        } catch (err) {
            alert("Đặt hàng thất bại: " + (err.response?.data?.message || err.message));
        }
    };

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
                        <form onSubmit={handlePlaceOrder}>
                            <div className="row">
                                <div className="col-lg-8 col-md-6">
                                    <div className="checkout__input">
                                        <p>Address<span>*</span></p>
                                        <input
                                            type="text"
                                            placeholder="Street Address"
                                            value={address}
                                            onChange={e => setAddress(e.target.value)}
                                            required
                                        />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Order notes</p>
                                        <input
                                            type="text"
                                            placeholder="Notes about your order, e.g. special notes for delivery."
                                            value={note}
                                            onChange={e => setNote(e.target.value)}
                                        />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Coupon code</p>
                                        <input
                                            type="text"
                                            placeholder="Enter coupon code"
                                            value={sale}
                                            onChange={e => setSale(e.target.value)}
                                        />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Ngày giao hàng dự kiến</p>
                                        <input
                                            type="datetime-local"
                                            value={deliveryAt}
                                            onChange={e => setDeliveryAt(e.target.value)}
                                        />
                                    </div>
                                    <div className="checkout__input">
                                        <p>Phương thức thanh toán</p>
                                        <select value={paymentMethod} onChange={e => setPaymentMethod(e.target.value)}>
                                            <option value="cod">Thanh toán khi nhận hàng</option>
                                            <option value="paypal">Paypal</option>
                                        </select>
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