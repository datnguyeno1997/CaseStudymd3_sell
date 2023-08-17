<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
  <div class="humberger__menu__logo">
    <a href="#"><img src="/dashboard/img/logo.png" alt=""></a>
  </div>
  <div class="humberger__menu__cart">
    <ul>
      <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
      <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
    </ul>
    <div class="header__cart__price">item: <span>$150.00</span></div>
  </div>
  <div class="humberger__menu__widget">
    <div class="header__top__right__language">
      <img src="/dashboard/img/language.png" alt="">
      <div>English</div>
      <span class="arrow_carrot-down"></span>
      <ul>
        <li><a href="#">Spanis</a></li>
        <li><a href="#">English</a></li>
      </ul>
    </div>
    <div class="header__top__right__auth">
      <a href="/auths"><i class="fa fa-user"></i> Login</a>
      <a href="/auths"><i class="fa fa-user"></i> logout</a>
      <form class="d-flex">
        <% if (session.getAttribute("login") != null) { %>
        <div class="right-items">
          <li><a href="/login">${sessionScope.login.getUsername()}</a></li>
        </div>
        <div class="right-items">
          <li><a href="/logout">Logout</a></li>
        </div>
        <% } else {%>
        <div class="right-items">
          <li><a href="/login">Login</a></li>
        </div>
  <% } %>
      </form>
    </div>
  </div>
  <nav class="humberger__menu__nav mobile-menu">
    <ul>
      <li class="active"><a href="./index.jsp">Home</a></li>
      <li><a href="./shop_grid.jsp">Shop</a></li>
      <li><a href="#">Pages</a>
        <ul class="header__menu__dropdown">
          <li><a href="./shop_details.jsp">Shop Details</a></li>
          <li><a href="./shoping_cart.jsp">Shoping Cart</a></li>
          <li><a href="./checkout.jsp">Check Out</a></li>
          <li><a href="./blog_details.jsp">Blog Details</a></li>
        </ul>
      </li>
      <li><a href="./blog.jsp">Blog</a></li>
    </ul>
  </nav>
  <div id="mobile-menu-wrap"></div>
  <div class="header__top__right__social">
    <a href="#"><i class="fa fa-facebook"></i></a>
    <a href="#"><i class="fa fa-twitter"></i></a>
    <a href="#"><i class="fa fa-linkedin"></i></a>
    <a href="#"><i class="fa fa-pinterest-p"></i></a>
  </div>
  <div class="humberger__menu__contact">
    <ul>
      <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
      <li>Free Shipping for all Order of $99</li>
    </ul>
  </div>
</div>