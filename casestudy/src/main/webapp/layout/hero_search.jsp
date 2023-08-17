<div class="hero__search">
  <div class="hero__search__form">
    <form action="${pageContext.request.contextPath}" class="row">
      <div class="hero__search__categories">
        All Categories
        <span class="arrow_carrot-down"></span>
      </div>
      <input type="search" name="search" value="${pageable.search}"
             class="form-control">
      <button style="width: auto" type="submit" class="site-btn">SEARCH</button>
    </form>
  </div>
  <div class="hero__search__phone">
    <div class="hero__search__phone__icon">
      <i class="fa fa-phone"></i>
    </div>
    <div class="hero__search__phone__text">
      <h5>+65 11.188.888</h5>
      <span>support 24/7 time</span>
    </div>
  </div>
</div>