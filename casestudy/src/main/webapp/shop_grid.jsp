<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ogani | Template</title>

    <jsp:include page="/layout/head_css.jsp"></jsp:include>

</head>

<body>
<!-- Page Preloder -->
<%--<div id="preloder">--%>
<%--  <div class="loader"></div>--%>
<%--</div>--%>

<!-- Humberger Begin -->
<%--<jsp:include page="/layout/humberger.jsp"></jsp:include>--%>
<!-- Humberger End -->

<!-- Header Section Begin -->
<jsp:include page="/layout/header_section.jsp"></jsp:include>
<!-- Header Section End -->

<!-- Hero Section Begin -->
<section class="hero hero-normal">
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <jsp:include page="/layout/hero_categories.jsp"></jsp:include>
            </div>
            <div class="col-lg-9">
                <jsp:include page="/layout/hero_search.jsp"></jsp:include>
            </div>
        </div>
    </div>
</section>
<!-- Hero Section End -->

<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="/dashboard/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Organi Shop</h2>
                    <div class="breadcrumb__option">
                        <a href="/homes">Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Section Begin -->
<section class="product spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-5">
                <div class="sidebar">
                    <div class="sidebar__item">
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
                    <div class="sidebar__item">
                        <h4>Price</h4>
                        <div class="price-range-wrap">
                            <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                 data-min="10" data-max="540">
                                <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
                                <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                            </div>
                            <div class="range-slider">
                                <div class="price-input">
                                    <input type="text" id="minamount">
                                    <input type="text" id="maxamount">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar__item sidebar__item__color--option">
                        <h4>Colors</h4>
                        <div class="sidebar__item__color sidebar__item__color--white">
                            <label for="white">
                                White
                                <input type="radio" id="white">
                            </label>
                        </div>
                        <div class="sidebar__item__color sidebar__item__color--gray">
                            <label for="gray">
                                Gray
                                <input type="radio" id="gray">
                            </label>
                        </div>
                        <div class="sidebar__item__color sidebar__item__color--red">
                            <label for="red">
                                Red
                                <input type="radio" id="red">
                            </label>
                        </div>
                        <div class="sidebar__item__color sidebar__item__color--black">
                            <label for="black">
                                Black
                                <input type="radio" id="black">
                            </label>
                        </div>
                        <div class="sidebar__item__color sidebar__item__color--blue">
                            <label for="blue">
                                Blue
                                <input type="radio" id="blue">
                            </label>
                        </div>
                        <div class="sidebar__item__color sidebar__item__color--green">
                            <label for="green">
                                Green
                                <input type="radio" id="green">
                            </label>
                        </div>
                    </div>
                    <div class="sidebar__item">
                        <h4>Popular Size</h4>
                        <div class="sidebar__item__size">
                            <label for="large">
                                Large
                                <input type="radio" id="large">
                            </label>
                        </div>
                        <div class="sidebar__item__size">
                            <label for="medium">
                                Medium
                                <input type="radio" id="medium">
                            </label>
                        </div>
                        <div class="sidebar__item__size">
                            <label for="small">
                                Small
                                <input type="radio" id="small">
                            </label>
                        </div>
                        <div class="sidebar__item__size">
                            <label for="tiny">
                                Tiny
                                <input type="radio" id="tiny">
                            </label>
                        </div>
                    </div>
                    <div class="sidebar__item">
                        <div class="latest-product__text">
                            <h4>Latest Products</h4>
                            <div class="latest-product__slider owl-carousel">
                                <div class="latest-prdouct__slider__item">
                                    <c:forEach items="${requestScope.products}" var="p">
                                        <a href="/product?id=${p.id}" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="${p.getAvatar()}" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${p.getName()}</h6>
                                                <span>${p.getPrice()}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                    <%--                                ``    <a href="#" class="latest-product__item">--%>
                                    <%--                                        <div class="latest-product__item__pic">--%>
                                    <%--                                            <img src="/dashboard/img/latest-product/lp-2.jpg" alt="">--%>
                                    <%--                                        </div>--%>
                                    <%--                                        <div class="latest-product__item__text">--%>
                                    <%--                                            <h6>Crab Pool Security</h6>--%>
                                    <%--                                            <span>$30.00</span>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </a>--%>
                                    <%--                                    <a href="#" class="latest-product__item">--%>
                                    <%--                                        <div class="latest-product__item__pic">--%>
                                    <%--                                            <img src="/dashboard/img/latest-product/lp-3.jpg" alt="">--%>
                                    <%--                                        </div>--%>
                                    <%--                                        <div class="latest-product__item__text">--%>
                                    <%--                                            <h6>Crab Pool Security</h6>--%>
                                    <%--                                            <span>$30.00</span>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </a>--%>
                                </div>
                                <div class="latest-prdouct__slider__item">
                                    <c:forEach items="${requestScope.products}" var="p">
                                        <a href="/product?id=${p.id}" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="${p.getAvatar()}" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>${p.getName()}</h6>
                                                <span>${p.getPrice()}</span>
                                            </div>
                                        </a>
                                    </c:forEach>
                                    <%--                                    <a href="#" class="latest-product__item">--%>
                                    <%--                                        <div class="latest-product__item__pic">--%>
                                    <%--                                            <img src="/dashboard/img/latest-product/lp-2.jpg" alt="">--%>
                                    <%--                                        </div>--%>
                                    <%--                                        <div class="latest-product__item__text">--%>
                                    <%--                                            <h6>Crab Pool Security</h6>--%>
                                    <%--                                            <span>$30.00</span>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </a>--%>
                                    <%--                                    <a href="#" class="latest-product__item">--%>
                                    <%--                                        <div class="latest-product__item__pic">--%>
                                    <%--                                            <img src="/dashboard/img/latest-product/lp-3.jpg" alt="">--%>
                                    <%--                                        </div>--%>
                                    <%--                                        <div class="latest-product__item__text">--%>
                                    <%--                                            <h6>Crab Pool Security</h6>--%>
                                    <%--                                            <span>$30.00</span>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </a>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-9 col-md-7">
                <div class="product__discount">
                    <div class="section-title product__discount__title">
                        <h2>Sale Off</h2>
                    </div>
                    <div class="row">
                        <div class="product__discount__slider owl-carousel">
                            <c:forEach items="${requestScope.products}" var="p">
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                             data-setbg="${p.getAvatar()}">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="/product?id=${p.id}"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Dried Fruit</span>
                                            <h5><a href="/product?id=${p.id}">${p.getName()}</a></h5>
                                            <div class="product__item__price">${p.getPrice()}</div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <%--                            <div class="col-lg-4">--%>
                            <%--                                <div class="product__discount__item">--%>
                            <%--                                    <div class="product__discount__item__pic set-bg"--%>
                            <%--                                         data-setbg="/dashboard/img/product/discount/pd-2.jpg">--%>
                            <%--                                        <div class="product__discount__percent">-20%</div>--%>
                            <%--                                        <ul class="product__item__pic__hover">--%>
                            <%--                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                            <%--                                        </ul>--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="product__discount__item__text">--%>
                            <%--                                        <span>Vegetables</span>--%>
                            <%--                                        <h5><a href="#">Vegetables’package</a></h5>--%>
                            <%--                                        <div class="product__item__price">$30.00 <span>$36.00</span></div>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="col-lg-4">--%>
                            <%--                                <div class="product__discount__item">--%>
                            <%--                                    <div class="product__discount__item__pic set-bg"--%>
                            <%--                                         data-setbg="/dashboard/img/product/discount/pd-3.jpg">--%>
                            <%--                                        <div class="product__discount__percent">-20%</div>--%>
                            <%--                                        <ul class="product__item__pic__hover">--%>
                            <%--                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                            <%--                                        </ul>--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="product__discount__item__text">--%>
                            <%--                                        <span>Dried Fruit</span>--%>
                            <%--                                        <h5><a href="#">Mixed Fruitss</a></h5>--%>
                            <%--                                        <div class="product__item__price">$30.00 <span>$36.00</span></div>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="col-lg-4">--%>
                            <%--                                <div class="product__discount__item">--%>
                            <%--                                    <div class="product__discount__item__pic set-bg"--%>
                            <%--                                         data-setbg="/dashboard/img/product/discount/pd-4.jpg">--%>
                            <%--                                        <div class="product__discount__percent">-20%</div>--%>
                            <%--                                        <ul class="product__item__pic__hover">--%>
                            <%--                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                            <%--                                        </ul>--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="product__discount__item__text">--%>
                            <%--                                        <span>Dried Fruit</span>--%>
                            <%--                                        <h5><a href="#">Raisin’n’nuts</a></h5>--%>
                            <%--                                        <div class="product__item__price">$30.00 <span>$36.00</span></div>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="col-lg-4">--%>
                            <%--                                <div class="product__discount__item">--%>
                            <%--                                    <div class="product__discount__item__pic set-bg"--%>
                            <%--                                         data-setbg="/dashboard/img/product/discount/pd-5.jpg">--%>
                            <%--                                        <div class="product__discount__percent">-20%</div>--%>
                            <%--                                        <ul class="product__item__pic__hover">--%>
                            <%--                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                            <%--                                        </ul>--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="product__discount__item__text">--%>
                            <%--                                        <span>Dried Fruit</span>--%>
                            <%--                                        <h5><a href="#">Raisin’n’nuts</a></h5>--%>
                            <%--                                        <div class="product__item__price">$30.00 <span>$36.00</span></div>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                            <%--                            <div class="col-lg-4">--%>
                            <%--                                <div class="product__discount__item">--%>
                            <%--                                    <div class="product__discount__item__pic set-bg"--%>
                            <%--                                         data-setbg="/dashboard/img/product/discount/pd-6.jpg">--%>
                            <%--                                        <div class="product__discount__percent">-20%</div>--%>
                            <%--                                        <ul class="product__item__pic__hover">--%>
                            <%--                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                            <%--                                            <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                            <%--                                        </ul>--%>
                            <%--                                    </div>--%>
                            <%--                                    <div class="product__discount__item__text">--%>
                            <%--                                        <span>Dried Fruit</span>--%>
                            <%--                                        <h5><a href="#">Raisin’n’nuts</a></h5>--%>
                            <%--                                        <div class="product__item__price">$30.00 <span>$36.00</span></div>--%>
                            <%--                                    </div>--%>
                            <%--                                </div>--%>
                            <%--                            </div>--%>
                        </div>
                    </div>
                </div>
                <div class="filter__item">
                    <div class="row">
                        <div class="col-lg-4 col-md-5">
                            <div class="filter__sort">
                                <span>Sort By</span>
                                <select>
                                    <option value="0">Default</option>
                                    <option value="0">Default</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4">
                            <div class="filter__found">
                                <h6><span>16</span> Products found</h6>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-3">
                            <div class="filter__option">
                                <span class="icon_grid-2x2"></span>
                                <span class="icon_ul"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${requestScope.products}" var="p">
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="${p.getAvatar()}">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                        <li><a href="/product?id=${p.id}"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="/product?id=${p.id}">${p.getName()}</a></h6>
                                    <h5>${p.getPrice()}</h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-2.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-3.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-4.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-5.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-6.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-7.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-8.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-9.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-10.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-11.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="col-lg-4 col-md-6 col-sm-6">--%>
                    <%--                        <div class="product__item">--%>
                    <%--                            <div class="product__item__pic set-bg" data-setbg="/dashboard/img/product/product-12.jpg">--%>
                    <%--                                <ul class="product__item__pic__hover">--%>
                    <%--                                    <li><a href="#"><i class="fa fa-heart"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-retweet"></i></a></li>--%>
                    <%--                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>--%>
                    <%--                                </ul>--%>
                    <%--                            </div>--%>
                    <%--                            <div class="product__item__text">--%>
                    <%--                                <h6><a href="#">Crab Pool Security</a></h6>--%>
                    <%--                                <h5>$30.00</h5>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                </div>
                <div class="d-flex justify-content-end">
                    <nav aria-label="...">
                        <ul class="pagination">
                            <li class="page-item
                    <c:if test="${pageable.page == 1}"> disabled </c:if> ">
                                <a class="page-link" <c:if test="${pageable.page > 1}">
                                    href="/shopgrid?page=${pageable.page - 1}&search=${pageable.search}&sortType=${pageable.sortType}&sortField=${pageable.sortField}" </c:if>
                                >Previous</a>
                            </li>
                            <%--                neu page > 3   > 0--%>
                            <c:forEach begin="1" end="${pageable.totalPage}" var="num">
                                <c:if test="${pageable.page == num}">
                                    <li class="page-item active" aria-current="page">
                                        <a class="page-link"
                                           href="/shopgrid?page=${num}&search=${pageable.search}&sortType=${pageable.sortType}&sortField=${pageable.sortField}"
                                        >${num}</a>
                                    </li>
                                </c:if>
                                <c:if test="${pageable.page != num}">
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/shopgrid?page=${num}&search=${pageable.search}&sortType=${pageable.sortType}&sortField=${pageable.sortField}"
                                        >${num}</a>
                                    </li>
                                </c:if>
                            </c:forEach>

                            <li class="page-item
                    <c:if test="${pageable.page == pageable.totalPage}"> disabled </c:if> "
                            >
                                <a class="page-link" <c:if test="${pageable.page < pageable.totalPage}">
                                    href="/shopgrid?page=${pageable.page + 1}&search=${pageable.search}&sortType=${pageable.sortType}&sortField=${pageable.sortField}" </c:if>
                                >Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Section End -->

<!-- Footer Section Begin -->
<jsp:include page="/layout/footer_section.jsp"></jsp:include>

<jsp:include page="/layout/java_script.jsp"></jsp:include>

</body>

</html>