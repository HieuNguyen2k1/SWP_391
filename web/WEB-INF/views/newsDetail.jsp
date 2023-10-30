
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="master/head.jsp" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html class="no-js" lang="zxx">

            <!--? Slider Area Start-->
            <div class="slider-area slider-area2">
                <div class="slider-active dot-style">
                    <!-- Slider Single -->
                    <div class="single-slider  d-flex align-items-center slider-height2">
                        <div class="container">
                            <div class="row align-items-center">
                                <div class="col-xl-7 col-lg-8 col-md-10 ">
                                    <div class="hero-wrapper">
                                        <div class="hero__caption">
                                            <h1 data-animation="fadeInUp" data-delay=".3s">Cẩm Nang Y Tế</h1>
                                            <p data-animation="fadeInUp" data-delay=".6s">Kiến thức y tế - Kho báu <br> quý giá giúp bạn sống khỏe và hạnh phúc.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>    
                </div>
            </div>
            <!-- Slider Area End -->
            <!--? News Area Start -->
            <section class="news_area single-post-area section-padding">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 posts-list">
                            <div class="single-post">
                                <div class="feature-img">
                                    <img class="img-fluid" src="${detail.image}" alt="">
                                </div>
                                <div class="news_details">
                                    <h2 style="color: #2d2d2d;">${detail.title}
                                    </h2>

                                    <p class="excert">
                                        ${detail.scriptShort}
                                    </p>
                                    <p class="excert">
                                        ${detail.scriptFull}
                                    </p>

                                </div>
                            </div>

                            <div class="news-author">
                                <div class="media align-items-center">
                                    <img src="assets/img/news/author.png" alt="">
                                    <div class="media-body">
                                        <a>
                                            <h4>Harvard milan</h4>
                                        </a>
                                        <p>Second divided from form fish beast made. Every of seas all gathered use saying you're, he
                                            our dominion twon Second divided from</p>
                                    </div>
                                </div>
                            </div>


                        </div>





                    </div>
                </div>
                </div>
                </div>
            </section>
            <!-- News Area End -->
            <!--? About Law Start-->
            <section class="about-low-area mt-30">
                <div class="container">
                    <div class="about-cap-wrapper">
                        <div class="row">
                            <div class="col-xl-5  col-lg-6 col-md-10 offset-xl-1">
                                <div class="about-caption mb-50">
                                    <!-- Section Tittle -->
                                    <div class="section-tittle mb-35">
                                        <br><br>
                                        <h2 style="font-family: Arial">100% Mang đến sự hài lòng cho bạn.</h2>
                                    </div>
                                    <p style="font-family: Arial;" >Hãy đến với chúng tôi. Phòng khám An Tâm - Yên tâm trong từng tế bào.</p>
                                   

                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12">
                                <!-- about-img -->
                                <div class="about-img">
                                    <div class="about-font-img">
                                        <img src="assets/img/gallery/about2.png" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- About Law End-->
          <jsp:include page="master/foot.jsp" />  
        <!-- Scroll Up -->
        <div id="back-top" >
            <a title="Go to Top" href="#"> <i class="fas fa-level-up-alt"></i></a>
        </div>

        <!-- JS here -->

        <script src="./assets/js/vendor/modernizr-3.5.0.min.js"></script>
        <!-- Jquery, Popper, Bootstrap -->
        <script src="./assets/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="./assets/js/popper.min.js"></script>
        <script src="./assets/js/bootstrap.min.js"></script>
        <!-- Jquery Mobile Menu -->
        <script src="./assets/js/jquery.slicknav.min.js"></script>

        <!-- Jquery Slick , Owl-Carousel Plugins -->
        <script src="./assets/js/owl.carousel.min.js"></script>
        <script src="./assets/js/slick.min.js"></script>

        <!-- One Page, Animated-HeadLin -->
        <script src="./assets/js/wow.min.js"></script>
        <script src="./assets/js/animated.headline.js"></script>
        <script src="./assets/js/jquery.magnific-popup.js"></script>

        <!-- Nice-select, sticky -->
        <script src="./assets/js/jquery.nice-select.min.js"></script>
        <script src="./assets/js/jquery.sticky.js"></script>

        <!-- contact js -->
        <script src="./assets/js/contact.js"></script>
        <script src="./assets/js/jquery.form.js"></script>
        <script src="./assets/js/jquery.validate.min.js"></script>
        <script src="./assets/js/mail-script.js"></script>
        <script src="./assets/js/jquery.ajaxchimp.min.js"></script>

        <!-- Jquery Plugins, main Jquery -->	
        <script src="./assets/js/plugins.js"></script>
        <script src="./assets/js/main.js"></script>

    </body>
</html>
