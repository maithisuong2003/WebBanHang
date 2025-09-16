import React, {useEffect, useState} from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import {useNavigate, useParams} from "react-router-dom";
import {API_BASE_URL} from "../service/AuthService";
import axios from "axios";
import Swal from "sweetalert2";

const ShopDetails = () => {
    const { id } = useParams();
    const [product, setProduct] = useState({});
    const token = localStorage.getItem('token');
    const user = JSON.parse(localStorage.getItem('user'));
    const navigate = useNavigate();
    const [quantity, setQuantity] = useState(1);
    useEffect(() => {
        axios.get(`${API_BASE_URL}/products/${id}`)
            .then(res => setProduct(res.data.result))
            .catch(err => console.error(err));
    }, [id]);
    if (!product) return <div>Loading...</div>;

    const handleAddToCart = (product) => {
        if (!user || !token) {
            navigate('/login');
            return;
        }

        axios.post(`${API_BASE_URL}/carts/add-item`,
            {
                productId: product.id,
                quantity: quantity,
                userId: user.id
            },
            { headers: { Authorization: `Bearer ${token}` } }
        )
            .then(res => {
                Swal.fire('Thành công!', 'Sản phẩm đã được thêm vào giỏ hàng.', 'success');
            })
            .catch(err => {
                console.error('Thêm vào giỏ hàng lỗi:', err);
                Swal.fire('Lỗi!', 'Vui lòng đăng nhập hoặc thử lại.', 'error');
            });
    };    return (
        <>
            <Header/>

            <section className="hero hero-normal">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-3">
                            <div className="hero__categories">
                                <div className="hero__categories__all">
                                    <i className="fa fa-bars"></i>
                                    <span>All departments</span>
                                </div>
                                <ul>
                                    <li><a href="#">Fresh Meat</a></li>
                                    <li><a href="#">Vegetables</a></li>
                                    <li><a href="#">Fruit & Nut Gifts</a></li>
                                    <li><a href="#">Fresh Berries</a></li>
                                    <li><a href="#">Ocean Foods</a></li>
                                    <li><a href="#">Butter & Eggs</a></li>
                                    <li><a href="#">Fastfood</a></li>
                                    <li><a href="#">Fresh Onion</a></li>
                                    <li><a href="#">Papayaya & Crisps</a></li>
                                    <li><a href="#">Oatmeal</a></li>
                                    <li><a href="#">Fresh Bananas</a></li>
                                </ul>
                            </div>
                        </div>
                        <div className="col-lg-9">
                            <div className="hero__search">
                                <div className="hero__search__form">
                                    <form action="#">
                                        <div className="hero__search__categories">
                                            All Categories
                                            <span className="arrow_carrot-down"></span>
                                        </div>
                                        <input type="text" placeholder="What do you need?" />
                                        <button type="submit" className="site-btn">SEARCH</button>
                                    </form>
                                </div>
                                <div className="hero__search__phone">
                                    <div className="hero__search__phone__icon">
                                        <i className="fa fa-phone"></i>
                                    </div>
                                    <div className="hero__search__phone__text">
                                        <h5>+65 11.188.888</h5>
                                        <span>support 24/7 time</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="breadcrumb-section set-bg" style={{ backgroundImage: `url(img/breadcrumb.jpg)` }}>
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12 text-center">
                            <div className="breadcrumb__text">
                                <h2>Vegetable’s Package</h2>
                                <div className="breadcrumb__option">
                                    <a href="./index.html">Home</a>
                                    <a href="./index.html">Vegetables</a>
                                    <span>Vegetable’s Package</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="product-details spad">
                <div className="container">
                    <div className="row" key={product.id}>
                        <div className="col-lg-6 col-md-6">
                            <div className="product__details__pic">
                                <div className="product__details__pic__item">
                                    <img className="product__details__pic__item--large"
                                         src={product.imageUrl} alt="" />
                                </div>
                                <div className="product__details__pic__slider owl-carousel">
                                    <img data-imgbigurl="img/product/details/product-details-2.jpg"
                                         src="img/product/details/thumb-1.jpg" alt="" />
                                    <img data-imgbigurl="img/product/details/product-details-3.jpg"
                                         src="img/product/details/thumb-2.jpg" alt="" />
                                    <img data-imgbigurl="img/product/details/product-details-5.jpg"
                                         src="img/product/details/thumb-3.jpg" alt="" />
                                    <img data-imgbigurl="img/product/details/product-details-4.jpg"
                                         src="img/product/details/thumb-4.jpg" alt="" />
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 col-md-6">
                            <div className="product__details__text">
                                <h3>{product.nameProduct}</h3>
                                <div className="product__details__rating">
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star"></i>
                                    <i className="fa fa-star-half-o"></i>
                                    <span>(18 reviews)</span>
                                </div>
                                <div className="product__details__price">{product.price}đ</div>
                                <p>
                                    {product.description}
                                </p>
                                <div style={{
                                    display: 'flex',
                                    alignItems: 'center',
                                    gap: '16px' // khoảng cách giữa các nút, có thể chỉnh lại
                                }}>
                                    {/* Số lượng */}
                                    <div style={{
                                        background: "#f5f5f5",
                                        borderRadius: "8px",
                                        padding: "8px 16px",
                                        display: "flex",
                                        alignItems: "center",
                                        gap: "8px"
                                    }}>
                                        <button
                                            type="button"
                                            style={{
                                                width: 36,
                                                height: 36,
                                                borderRadius: "50%",
                                                border: "1px solid #ccc",
                                                background: "white",
                                                fontSize: 20,
                                                cursor: "pointer"
                                            }}
                                            onClick={() => setQuantity(q => Math.max(1, q - 1))}
                                        >–
                                        </button>

                                        <input
                                            type="number"
                                            min="1"
                                            value={quantity}
                                            readOnly
                                            style={{
                                                width: 40,
                                                textAlign: "center",
                                                border: "1px solid #ccc",
                                                borderRadius: 4,
                                                background: "white",
                                                fontWeight: "bold"
                                            }}
                                        />

                                        <button
                                            type="button"
                                            style={{
                                                width: 36,
                                                height: 36,
                                                borderRadius: "50%",
                                                border: "1px solid #ccc",
                                                background: "white",
                                                fontSize: 20,
                                                cursor: "pointer"
                                            }}
                                            onClick={() => setQuantity(q => q + 1)}
                                        >+
                                        </button>
                                    </div>

                                    {/* Nút mua ngay */}
                                    <button
                                        className="btn"
                                        style={{
                                            minWidth: 150,
                                            height: 48,
                                            backgroundColor: '#7fad39',
                                            color: 'white',
                                            fontWeight: "bold",
                                            fontSize: "20px",
                                            display: "flex",
                                            alignItems: "center",
                                            justifyContent: "center",
                                            border: 'none',
                                            borderRadius: 8,
                                            gap: 8
                                        }}
                                        onClick={() => handleAddToCart(product)}
                                    >
                                        <i className="fa fa-shopping-cart"></i> Mua ngay
                                    </button>

                                    <a
                                        href="#"
                                        className="heart-icon"
                                        style={{
                                            background: "#f5f5f5",
                                            borderRadius: "8px",
                                            width: 48,
                                            height: 48,
                                            display: "flex",
                                            alignItems: "center",
                                            justifyContent: "center"
                                        }}
                                    >
                                        <span className="icon_heart_alt"></span>
                                    </a>
                                </div>

                                <ul>
                                    <li><b>Availability</b> <span>In Stock</span></li>
                                    <li><b>Shipping</b> <span>01 day shipping. <samp>Free pickup today</samp></span>
                                    </li>
                                    <li><b>Weight</b> <span>0.5 kg</span></li>
                                    <li><b>Share on</b>
                                        <div className="share">
                                            <a href="#"><i className="fa fa-facebook"></i></a>
                                            <a href="#"><i className="fa fa-twitter"></i></a>
                                            <a href="#"><i className="fa fa-instagram"></i></a>
                                            <a href="#"><i className="fa fa-pinterest"></i></a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div className="col-lg-12">
                            <div className="product__details__tab">
                                <ul className="nav nav-tabs" role="tablist">
                                    <li className="nav-item">
                                        <a className="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                                           aria-selected="true">Description</a>
                                    </li>
                                    <li className="nav-item">
                                        <a className="nav-link" data-toggle="tab" href="#tabs-2" role="tab"
                                           aria-selected="false">Information</a>
                                    </li>
                                    <li className="nav-item">
                                        <a className="nav-link" data-toggle="tab" href="#tabs-3" role="tab"
                                           aria-selected="false">Reviews <span>(1)</span></a>
                                    </li>
                                </ul>
                                <div className="tab-content">
                                    <div className="tab-pane active" id="tabs-1" role="tabpanel">
                                        <div className="product__details__tab__desc">
                                            <h6>Products Information</h6>
                                            <p>Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui.
                                                Pellentesque in ipsum id orci porta dapibus...</p>
                                        </div>
                                    </div>
                                    <div className="tab-pane" id="tabs-2" role="tabpanel">
                                        <div className="product__details__tab__desc">
                                            <h6>Products Information</h6>
                                            <p>Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui...</p>
                                        </div>
                                    </div>
                                    <div className="tab-pane" id="tabs-3" role="tabpanel">
                                        <div className="product__details__tab__desc">
                                            <h6>Products Information</h6>
                                            <p>Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui...</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section className="related-product">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="section-title related__product__title">
                                <h2>Related Product</h2>
                            </div>
                        </div>
                    </div>
                    <div className="row">
                        {/* You can map related products here */}
                        <div className="col-lg-3 col-md-4 col-sm-6">
                            <div className="product__item">
                                <div className="product__item__pic set-bg">
                                    <img src="img/product/product-1.jpg" alt="" />
                                    <ul className="product__item__pic__hover">
                                        <li><a href="#"><i className="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i className="fa fa-retweet"></i></a></li>
                                        <li><a href="#"><i className="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div className="product__item__text">
                                    <h6><a href="#">Crab Pool Security</a></h6>
                                    <h5>$30.00</h5>
                                </div>
                            </div>
                        </div>
                        {/* Repeat for other related products */}
                    </div>
                </div>
            </section>

            <Footer/>
        </>
    );
};

export default ShopDetails;

