<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="/include/navbar.jsp" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>MEDINOVA - Hospital Website</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@400;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">  
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>

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
                                    <h1 data-animation="fadeInUp" >Tin Tức</h1>
                                    <p data-animation="fadeInUp">Trang tin tức Medinova</p>
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

                        <div class="news-detail">
                            <h2 style="color: #2d2d2d;">${detail.title}
                            </h2>
                            <p class="excert">
                                ${detail.scriptShort}
                            </p>
                            <div class="feature-img">
                                <img class="img-fluid" src="${detail.image}" alt="" width="500" height="400">
                            </div>

                            <p class="excert">
                                ${detail.scriptFull}
                            </p>
                        </div>
                        <c:forEach items="${news}" var="news">
                            <div class="news_item_img">
                                <img class="card-img rounded-0" src="${news.getImage()}" alt="" width="500" height="400">

                            </div>
                            <div class="news_details">
                                <a class="d-inline-block" href="news-detail?nid=${news.getNewsid()}">
                                    <h2 class="news-head" style="color: #2d2d2d;">${news.getTitle()}</h2>
                                </a>
                                <p>${news.getScriptShort()}</p>

                            </div>
                        </c:forEach>
                    </div>                    
                </div>
            </div>
        </div>
    </section>

</body>
<%@include file="/include/footer.jsp" %>
</html>
<style>
    body {
        font-size: 19px; /* Set the desired font size */
    }
    .custom-container {
        width: 50%;
        margin-left: auto;
        margin-right: auto;
        font-size: 25px;
    }
    .button-style{
        background-color: #234821;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 7px;
        font-size: 15px;
        font-weight: bold;
        cursor: pointer;
    }
    h2, .h2{
        margin-top: 30px;
    }
    p{
        margin-top: 5px;
    }

</style>

