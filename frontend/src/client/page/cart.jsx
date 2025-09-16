// ShopingCart.jsx
import React from "react";
import Header from "../components/Header";

const cartData = [
    {
        id: 1,
        name: "Vegetable’s Package",
        img: "img/cart/cart-1.jpg",
        price: 55.0,
        quantity: 1,
        total: 110.0,
    },
    {
        id: 2,
        name: "Fresh Garden Vegetable",
        img: "img/cart/cart-2.jpg",
        price: 39.0,
        quantity: 1,
        total: 39.99,
    },
    {
        id: 3,
        name: "Organic Bananas",
        img: "img/cart/cart-3.jpg",
        price: 69.0,
        quantity: 1,
        total: 69.99,
    },
];

const ShopingCart = () => {
    // Bạn có thể dùng useState để quản lý cart thực tế
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
                                    {cartData.map((item) => (
                                        <tr key={item.id}>
                                            <td className="shoping__cart__item">
                                                <img src={item.img} alt="" />
                                                <h5>{item.name}</h5>
                                            </td>
                                            <td className="shoping__cart__price">
                                                ${item.price.toFixed(2)}
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
                                                ${item.total.toFixed(2)}
                                            </td>
                                            <td className="shoping__cart__item__close">
                                                <span className="icon_close"></span>
                                            </td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="shoping__cart__btns">
                                <a href="#" className="primary-btn cart-btn">
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
                                        Total <span>$454.98</span>
                                    </li>
                                </ul>
                                <a href="#" className="primary-btn">
                                    PROCEED TO CHECKOUT
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* Footer... (Bạn có thể tách riêng file Footer.jsx nếu đã có) */}
        </div>
    );
};

export default ShopingCart;