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
<link href="../style.css" rel="stylesheet"/>
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
<section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2 style="color: black">Shopping Cart</h2>
                    <div class="breadcrumb__option">
                        <a style="color: black" href="/homes">Home</a>
                        <span style="color: black">Shopping Cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shoping Cart Section Begin -->
<section class="shoping-cart spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__table">
                    <table>
                        <thead>
                        <tr>
                            <th class="shoping__product">Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.cart.getCartItems()}" var="cartItem">
                            <tr>
                                <td class="shoping__cart__item">
                                    <img  src="${cartItem.product.avatar}" alt="">
                                    <h5>${cartItem.product.name}</h5>
                                </td>
                                <td class="shoping__cart__price">
                                        ${cartItem.price}
                                </td>
                                <td class="shoping__cart__quantity">
                                    <div class="quantity">
                                            <input onchange="handleQuantityChange(this,${cartItem.product.id})" min="1"
                                                   max="100" type="number" name="" class="form-control" id="test"
                                                   value="${cartItem.quantity}">
                                    </div>
                                </td>
                                <td class="shoping__cart__total">
                                        ${cartItem.price * cartItem.quantity}
                                </td>
                                <td class="shoping__cart__item__close">
                                    <span class="icon_close" onclick="handleDeleteCartItem(${cartItem.getId()})"></span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6"></div>
            <div class="col-lg-6">
                <div class="shoping__checkout">
                    <h5>Cart Total</h5>
                    <ul>
                        <li>Total <span>$${cart.total}</span></li>
                    </ul>
                    <form method="get" action="/checkout" >
                    <button class="primary-btn">PROCEED TO CHECKOUT</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Shoping Cart Section End -->

<!-- Footer Section Begin -->
<jsp:include page="/layout/footer_section.jsp"></jsp:include>

<jsp:include page="/layout/java_script.jsp"></jsp:include>

</body>
<script>

    function handleQuantityChange(eQuantity, idProduct) {
        let url = `/cart?action=update&id=\${idProduct}&quantity=\${eQuantity.value}`;
        window.location.assign(url);
    }

    function handleDeleteCartItem(id) {
        let url = `/cart?action=delete&id=\${id}`;
        window.location.assign(url);
    }
</script>


</html>