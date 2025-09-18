import React, { useState, useRef, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";

const Header = () => {
  const { user, logout } = useAuth();
  const [showMenu, setShowMenu] = useState(false);
  const menuRef = useRef(null);

  // Đóng menu khi click ra ngoài
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (menuRef.current && !menuRef.current.contains(event.target)) {
        setShowMenu(false);
      }
    };
    if (showMenu) {
      document.addEventListener("mousedown", handleClickOutside);
    } else {
      document.removeEventListener("mousedown", handleClickOutside);
    }
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [showMenu]);

  return (
      <>
        <header className="header">
          <div className="header__top">
            <div className="container">
              <div className="row">
                <div className="col-lg-6 col-md-6">
                  <div className="header__top__left">
                    <ul>
                      <li>
                        <i className="fa fa-envelope"></i> hello@colorlib.com
                      </li>
                      <li>Free Shipping for all Order of $99</li>
                    </ul>
                  </div>
                </div>
                <div className="col-lg-6 col-md-6">
                  <div className="header__top__right">
                    <div className="header__top__right__social">
                      <a href="#"><i className="fa fa-facebook"></i></a>
                      <a href="#"><i className="fa fa-twitter"></i></a>
                      <a href="#"><i className="fa fa-linkedin"></i></a>
                      <a href="#"><i className="fa fa-pinterest-p"></i></a>
                    </div>
                    <div className="header__top__right__language">
                      <img src="img/language.png" alt="" />
                      <div>English</div>
                      <span className="arrow_carrot-down"></span>
                      <ul>
                        <li><a href="#">Spanis</a></li>
                        <li><a href="#">English</a></li>
                      </ul>
                    </div>
                    <div
                        className="header__top__right__auth"
                        style={{ position: "relative", display: "inline-block" }}
                        ref={menuRef}
                    >
                      <div>
                        {user ? (
                            <>
                          <span
                              style={{ cursor: "pointer" }}
                              onClick={() => setShowMenu((prev) => !prev)}
                          >
                            <i className="fa fa-user"></i> {user.userName || user.username || "Đăng nhập"}
                          </span>
                              {showMenu && (
                                  <ul className="dropdown-menu-user">
                                      {user.roles && user.roles.includes("ADMIN")  && (
                                          <li>
                                            <Link
                                                to="/homeAdmin"
                                                onClick={() => setShowMenu(false)}
                                                className="dropdown-item-user"
                                            >
                                              Admin
                                            </Link>
                                          </li>
                                      )}
                                    <li>
                                      <Link to="/myinfor" onClick={() => setShowMenu(false)} className="dropdown-item-user">
                                        MyInfor
                                      </Link>
                                    </li>
                                    <li>
                                      <Link to="/order" onClick={() => setShowMenu(false)} className="dropdown-item-user">
                                        Ordered
                                      </Link>
                                    </li>
                                    <li>
                                      <button
                                          onClick={() => { logout(); setShowMenu(false); }}
                                          className="dropdown-item-user"
                                          type="button"
                                      >
                                        Logout
                                      </button>
                                    </li>
                                  </ul>
                              )}
                            </>
                        ) : (
                            <a href="/login"><i className="fa fa-user"></i> Login</a>
                        )}
                      </div>
                      <style>{`
                      .dropdown-menu-user {
                        position: absolute;
                        top: 120%;
                        right: 0;
                        background: #fff;
                        border: 1px solid #eee;
                        border-radius: 4px;
                        box-shadow: 0 2px 8px rgba(0,0,0,0.08);
                        min-width: 140px;
                        z-index: 1000;
                        list-style: none;
                        padding: 6px 0;
                        margin: 0;
                      }
                      .dropdown-menu-user li {
                        padding: 0;
                      }
                      .dropdown-menu-user a, .dropdown-menu-user button {
                        display: block;
                        width: 100%;
                        padding: 8px 16px;
                        color: #333;
                        text-decoration: none;
                        background: none;
                        border: none;
                        text-align: left;
                      }
                      .dropdown-menu-user a:hover, .dropdown-menu-user button:hover {
                        background: #f2f2f2;
                      }
                    `}</style>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="container">
            <div className="row">
              <div className="col-lg-3">
                <div className="header__logo">
                  <a href="./index.html"><img src="img/logo.png" alt="" /></a>
                </div>
              </div>
              <div className="col-lg-6">
                <nav className="header__menu">
                  <ul>
                    <li className="active"><Link to="/">Home</Link></li>
                    <li><Link to="/shop">Shop</Link></li>
                    <li>
                      <a href="#">Pages</a>
                      <ul className="header__menu__dropdown">
                        <li><Link to="/shop-details">Shop Details</Link></li>
                        <li><Link to="/shopping-cart">Shopping Cart</Link></li>
                        <li><Link to="/checkout">Check Out</Link></li>
                        <li><Link to="/blog-details">Blog Details</Link></li>
                      </ul>
                    </li>
                    <li><Link to="/blog">Blog</Link></li>
                    <li><Link to="/contact">Contact</Link></li>
                  </ul>
                </nav>
              </div>
              <div className="col-lg-3">
                <div className="header__cart">
                  <ul>
                    <li><a href="#"><i className="fa fa-heart"></i> <span>1</span></a></li>
                    <Link to="/cart">
                      <i className="fa fa-shopping-bag"></i> <span>3</span>
                    </Link>
                  </ul>
                  <div className="header__cart__price">
                    item: <span>$150.00</span>
                  </div>
                </div>
              </div>
            </div>
            <div className="humberger__open">
              <i className="fa fa-bars"></i>
            </div>
          </div>
        </header>
      </>
  );
};

export default Header;