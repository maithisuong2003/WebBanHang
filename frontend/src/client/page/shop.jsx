import React, {useEffect, useState} from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import {API_BASE_URL} from "../service/ProductService";
import axios from "axios";

const ShopGrid = () => {
  const [products, setProducts] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [suppliers, setSuppliers] = useState([]);
  const [categories, setCategories] = useState([]);
  const [sortBy, setSortBy] = useState("id");
  const [sortDirection, setSortDirection] = useState("ASC");
  const [minPrice, setMinPrice] = useState();
  const [maxPrice, setMaxPrice] = useState();
  const [sortOption, setSortOption] = useState('id:asc');

  useEffect(() => {
    console.log({
      page: currentPage - 1,
      size: 4,
      minPrice,
      maxPrice,
      suppliers: suppliers.join(","),
      categories: categories.join(","),
      sortBy: sortOption.split(':')[0],
      sortDirection: sortOption.split(':')[1].toUpperCase()
    });
    axios.get(`${API_BASE_URL}/products`, {
      params: {
        page: currentPage - 1,
        size: 4,
        minPrice,
        maxPrice,
        suppliers: suppliers.join(","),
        categories: categories.join(","),
        sortBy: sortOption.split(':')[0],
        sortDirection: sortOption.split(':')[1].toUpperCase()
      }
    })
        .then(res => {
          setProducts(res.data.content);
          setTotalPages(res.data.totalPages);
        });
  }, [currentPage, minPrice, maxPrice, suppliers, categories, sortOption]);
  const handlePageChange = (page) => setCurrentPage(page);
  const handleSortChange = (e) => {
    setSortOption(e.target.value);
  };
  return (
  <>
       <Header />

    {/* Hero Section Begin */}
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
                <form>
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
    {/* Hero Section End */}

    {/* Breadcrumb Section Begin */}
    <section className="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
      <div className="container">
        <div className="row">
          <div className="col-lg-12 text-center">
            <div className="breadcrumb__text">
              <h2>Organi Shop</h2>
              <div className="breadcrumb__option">
                <a href="./index.html">Home</a>
                <span>Shop</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    {/* Breadcrumb Section End */}

    {/* Product Section Begin */}
    <section className="product spad">
      <div className="container">
        <div className="row">
          <div className="col-lg-3 col-md-5">
            <div className="sidebar">
              <div className="sidebar__item">
                <h4>Department</h4>
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
                </ul>
              </div>
              <div className="sidebar__item">
                <h4>Price</h4>
                <div className="price-range-wrap">
                  <div className="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                       data-min="10" data-max="540">
                    <div className="ui-slider-range ui-corner-all ui-widget-header"></div>
                    <span tabIndex="0" className="ui-slider-handle ui-corner-all ui-state-default"></span>
                    <span tabIndex="0" className="ui-slider-handle ui-corner-all ui-state-default"></span>
                  </div>
                  <div className="range-slider">
                    <div className="price-input">
                      <input type="text" id="minamount"/>
                      <input type="text" id="maxamount"/>
                    </div>
                  </div>
                </div>
              </div>
              <div className="sidebar__item sidebar__item__color--option">
                <h4>Colors</h4>
                <div className="sidebar__item__color sidebar__item__color--white">
                  <label htmlFor="white">
                    White
                    <input type="radio" id="white"/>
                  </label>
                </div>
                <div className="sidebar__item__color sidebar__item__color--gray">
                  <label htmlFor="gray">
                    Gray
                    <input type="radio" id="gray"/>
                  </label>
                </div>
                <div className="sidebar__item__color sidebar__item__color--red">
                  <label htmlFor="red">
                    Red
                    <input type="radio" id="red"/>
                  </label>
                </div>
                <div className="sidebar__item__color sidebar__item__color--black">
                  <label htmlFor="black">
                    Black
                    <input type="radio" id="black"/>
                  </label>
                </div>
                <div className="sidebar__item__color sidebar__item__color--blue">
                  <label htmlFor="blue">
                    Blue
                    <input type="radio" id="blue"/>
                  </label>
                </div>
                <div className="sidebar__item__color sidebar__item__color--green">
                  <label htmlFor="green">
                    Green
                    <input type="radio" id="green"/>
                  </label>
                </div>
              </div>
              <div className="sidebar__item">
                <h4>Popular Size</h4>
                <div className="sidebar__item__size">
                  <label htmlFor="large">
                    Large
                    <input type="radio" id="large"/>
                  </label>
                </div>
                <div className="sidebar__item__size">
                  <label htmlFor="medium">
                    Medium
                    <input type="radio" id="medium"/>
                  </label>
                </div>
                <div className="sidebar__item__size">
                  <label htmlFor="small">
                    Small
                    <input type="radio" id="small"/>
                  </label>
                </div>
                <div className="sidebar__item__size">
                  <label htmlFor="tiny">
                    Tiny
                    <input type="radio" id="tiny"/>
                  </label>
                </div>
              </div>
              <div className="sidebar__item">
                <div className="latest-product__text">
                  <h4>Latest Products</h4>
                </div>
              </div>
            </div>
          </div>
          <div className="col-lg-9 col-md-7">
            <div className="product__discount">
              <div className="section-title product__discount__title">
                <h2>Sale Off</h2>
              </div>
            </div>
            <div className="filter__item">
              <div className="row">
                <div className="col-lg-4 col-md-5">
                  <div className="filter__sort">
                    <span>Sort By</span>
                    <select className="content_ul" onChange={handleSortChange} value={sortOption}>
                      <option value="id:asc">Mặc định</option>
                      <option value="id:desc">Mới nhất</option>
                      <option value="nameProduct:asc">Tên A &rarr; Z</option>
                      <option value="nameProduct:desc">Tên Z &rarr; A</option>
                      <option value="price:asc">Giá tăng dần</option>
                      <option value="price:desc">Giá giảm dần</option>
                    </select>

                  </div>
                </div>
                <div className="col-lg-4 col-md-4">
                  <div className="filter__found">
                    <h6><span>{products.length}</span> Products found</h6>
                  </div>
                </div>
                <div className="col-lg-4 col-md-3">
                  <div className="filter__option">
                    <span className="icon_grid-2x2"></span>
                    <span className="icon_ul"></span>
                  </div>
                </div>
              </div>
            </div>
            <div className="row">
              {products.map(product => (
                  <div className="col-lg-4 col-md-6 col-sm-6" key={product.id}>
                  <div className="product__item">
                      <div className="product__item__pic set-bg" data-setbg="">
                        <img src={product.image} alt={product.name} style={{width: "100%"}}/>
                        <ul className="product__item__pic__hover">
                          <li><a href="#"><i className="fa fa-heart"></i></a></li>
                          <li><a href="#"><i className="fa fa-retweet"></i></a></li>
                          <li><a href="#"><i className="fa fa-shopping-cart"></i></a></li>
                        </ul>
                      </div>
                      <div className="product__item__text">
                        <h6><a href="#">{product.nameProduct}</a></h6>
                        <h5>{product.price}</h5>
                      </div>
                    </div>
                  </div>
              ))}
            </div>
            <div className="product__pagination">
              {Array.from({length: totalPages}, (_, i) => (
                  <a
                      href="#"
                      key={i}
                      className={currentPage === i + 1 ? "active" : ""}
                      onClick={e => {
                        e.preventDefault(); // CHẶN reload
                        handlePageChange(i + 1);
                      }}
                  >
                    {i + 1}
                  </a>
              ))}
            </div>
          </div>
        </div>
      </div>
    </section>
    <Footer/>
  </>
  )
};

export default ShopGrid;