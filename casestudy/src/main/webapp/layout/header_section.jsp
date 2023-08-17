<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.enums.ERole" %>
<%@ page import="model.User" %>
<%@ page import="model.Login" %>
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                            <li>Free Shipping for all Order of $99</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
                        <div class="header__top__right__language">
                            <img style="height:auto; width: auto" src="/dashboard/img/language.png" alt="">
                            <div>English</div>
                            <span class="arrow_carrot-down"></span>
                            <ul>
                                <li><a href="#">Spanis</a></li>
                                <li><a href="#">English</a></li>
                            </ul>
                        </div>
                        <div class="header__top__right__auth">

                            <form class="d-flex">
                                <% if (session.getAttribute("login") != null) { %>
                                <div class="right-items">
                                    <% if (((Login) session.getAttribute("login")).getRole().equals(ERole.USER)){%>
                                        <a href="/users">${sessionScope.login.getUsername()}</a>
                                    <% } else {%>
                                        <a href="/products">${sessionScope.login.getUsername()}</a>
                                    <% } %>
                                </div>
                                <div class="right-items">
                                    <a href="/logout">logout</a>
                                </div>
                                <% } else {%>
                                <div class="right-items">
                                    <a href="/auths">Login</a>
                                </div>
                                <% } %>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="/homes"><img src="/dashboard/img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="/homes">Home</a></li>
                        <li><a href="/shop">Shop</a></li>
                        <li><a>Pages</a>
                            <ul class="header__menu__dropdown">
                                <li><a href="/cart">Shoping Cart</a></li>
                                <li><a href="/checkout">Check Out</a></li>
                                <li><a href="/blog_details.jsp">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="/blog.jsp">Blog</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                        <li><a href="/cart"><i class="fa fa-shopping-bag"></i> <span>${cart.cartItems.size()}</span></a></li>
                    </ul>
                    <div class="header__cart__price">item: <span>$${cart.total}</span></div>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
