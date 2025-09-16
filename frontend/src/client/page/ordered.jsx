import React, { useEffect, useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import axios from "axios";
import { API_BASE_URL } from "../service/AuthService";
import { useNavigate } from "react-router-dom";

const Ordered = () => {
    const [orders, setOrders] = useState([]);
    const token = localStorage.getItem('token');
    const navigate = useNavigate();

    useEffect(() => {
        if (!token) {
            navigate("/login");
            return;
        }
        axios.get(`${API_BASE_URL}/orders/my-order`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(res => {
                if (res.data && res.data.result) {
                    setOrders(res.data.result);
                }
            })
            .catch(err => {
                console.error("Error fetching orders:", err);
            });
    }, []);

    return (
        <div>
            <Header />

            <section
                className="breadcrumb-section set-bg"
                style={{ backgroundImage: `url(img/breadcrumb.jpg)` }}
            >
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12 text-center">
                            <div className="breadcrumb__text">
                                <h2>My Orders</h2>
                                <div className="breadcrumb__option">
                                    <a href="/">Home</a>
                                    <span>Orders</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            {/* Ordered List Section */}
            <section className="shoping-cart spad">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="shoping__cart__table">
                                <table>
                                    <thead>
                                    <tr>
                                        <th>Mã đơn</th>
                                        <th>Ngày đặt</th>
                                        <th>Trạng thái</th>
                                        <th>Thanh toán</th>
                                        <th>Ghi chú</th>
                                        <th>Tổng tiền</th>
                                        <th>Chi tiết</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {orders && orders.length > 0 ? orders.map(order => (
                                        <tr key={order.id}>
                                            <td>{order.id}</td>
                                            <td>{new Date(order.createAt).toLocaleString()}</td>
                                            <td>{order.status}</td>
                                            <td>{order.statusPay}</td>
                                            <td>{order.note}</td>
                                            <td>{order.totalPrice?.toLocaleString()} đ</td>
                                            <td>
                                                {/* Xem chi tiết đơn hàng, tuỳ thuộc bạn có trang chi tiết không */}
                                                <a href={`/order/${order.id}`}>View</a>
                                            </td>
                                        </tr>
                                    )) : (
                                        <tr>
                                            <td colSpan={7}>Bạn chưa có đơn hàng nào.</td>
                                        </tr>
                                    )}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <Footer />
        </div>
    );
};

export default Ordered;