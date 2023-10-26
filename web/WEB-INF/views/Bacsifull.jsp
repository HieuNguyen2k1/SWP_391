
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>

    <%@include file="/include/navbar.jsp" %>
    <title>MEDINOVA - Hospital Website</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@400;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">  
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script>
        if (window.performance && window.performance.navigation.type === 2) {
            // Trang được chuyển hướng từ servlet LogoutServlet
            history.replaceState(null, '', 'login.jsp');
            window.addEventListener('popstate', function (event) {
                window.location.href = 'login.jsp';
            });
        }
    </script>
    
</head>
<!--? Slider Area Start-->
<!--<div class="slider-area slider-area2">
    <div class="slider-active dot-style">
         Slider Single 
        <div class="single-slider  d-flex align-items-center slider-height2">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-7 col-lg-8 col-md-10 ">
                        <div class="hero-wrapper">
                            <div class="hero__caption">
                                <h1 data-animation="fadeInUp" data-delay=".3s" style="font-family: Arial">Đội Ngũ Bác
                                    Sĩ</h1>
                                <p data-animation="fadeInUp" data-delay=".6s" style="font-family: Arial">Chuyên nghiệp,
                                    tận tâm<br></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="header-search">  Added a container for search form 
    <form action="" method="post">
        <input type="hidden" value="0" name="speciality">
        <c:if test="${not empty search_name}">
            <input type="text" value="${search_name}" name="name">
        </c:if>
        <c:if test="${empty search_name}">
            <input type="text" placeholder="Tìm kiếm ..." name="name">
        </c:if>
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>
</div>
<br><br>
<div class="header-search" style="font-size: 2.0em; color: red">${none}</div>
<br> <br> <br>-->
<!----------------------------------------------------------------------->



<div class="slider-area slider-area2 justify-content-center align-items-center"  style=" margin-top: 50px ">
    <div class="slider-active dot-style">
        <!-- Slider Single -->
        <div class="single-slider ">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-7 col-lg-8 col-md-10"  style=" width: 100% ">
                        <div class="hero-wrapper">
                            <div class="hero__caption text-center">
                                <h1 data-animation="fadeInUp" data-delay=".3s" style="font-family: Arial">Đội Ngũ Bác Sĩ</h1>
                                <p data-animation="fadeInUp" data-delay=".6s" style="font-family: Arial">Chuyên nghiệp, tận tâm</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="header-search d-flex justify-content-center align-items-center">
    <form action="" method="post" class="d-flex">
        <input type="hidden" value="0" name="speciality">
        <div class="form-group mb-0 mr-2">
            <c:if test="${not empty search_name}">
                <input type="text" value="${search_name}" name="name" class="form-control">
            </c:if>
            <c:if test="${empty search_name}">
                <input type="text" placeholder="Tìm kiếm ..." name="name" class="form-control">
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
    </form>
</div>

<br><br>
<div class="header-search text-center" style="font-size: 2.0em; color: red">${none}</div>
<br> <br> <br>


<!----------------------------------------------------------------------->
<div class="slider-area slider-area2 justify-content-center align-items-center"  style=" margin-top: 50px ">
    <div class="slider-active dot-style">
        <!-- Slider Single -->
        <div class="single-slider ">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-7 col-lg-8 col-md-10"  style=" width: 100% ">
   <div class="col-lg-8 col-md-6 col-sm-12">
<div class=" chonbacsi container ">
    <h2>Chọn bác sĩ</h2>
</div>
<br> <br> <br>

<!--/////////////////////////////////////////////////////////////////////////////////-->
<!--            <div class="cacbacsi">
                <div class="row">
                     Nội dung của cacbacsi 
                    <c:forEach items="${showalldoctor}" var="item" varStatus="loop">
                        <div class="col-lg-4 col-md-6 col-sm-12">
                            <div class="doctor-card">
                                <div class="blog-img-cap">
                                    <div class="blog-img">
                                        <img style="border-radius: 50%;width: 100%; height: 100%; object-fit: cover"
                                             src="${pageContext.request.contextPath}/${item.getImage()}" alt="">
                                        <div class="ratings">
                                            <span class="rating"></span>
                                            <i class="fas fa-star"></i>
                                        </div>
                                    </div>
                                    <div class="blog-cap">
                                        <h3 class="doctor-name">${item.name}</h3>
                                        <div class="degree">${item.degree}</div>
                                        <p class="specialization">
                                            ${item.getSpeciality_name()}
                                        </p>
                                        <button class="btn appointment-button">
                                            <i class="fas fa-calendar-alt" style="color: white;"></i>
                                            <span>
                                                <a href="${pageContext.request.contextPath}/patient/make-appointment?doc_id=${item.getId()}" 
                                                   class="btn btn-primary rounded-pill py-md-3 px-md-5 mx-2"
                                                    >
                                                    Đặt khám
                                                </a>
                                            </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                      
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>        -->


<div class="cacbacsi">
    <div class="flex-container">
        <!-- Nội dung của cacbacsi -->
        <c:forEach items="${showalldoctor}" var="item" varStatus="loop">
            <div class="flex-item">
                <div class="doctor-card">
                    <div class="blog-img-cap">
                        <div class="blog-img">
<!--                            <img style="border-radius: 50%;width: 100%; height: 100%; object-fit: cover"
                                 src="${pageContext.request.contextPath}/${item.getImage()}" alt="">-->
                            
                                             <img class="rounded-circle" width="150px" src="https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png">

                            <div class="ratings">
                                <span class="rating"></span>
                                <i class="fas fa-star"></i>
                            </div>
                        </div>
                        <div class="blog-cap">
                            <h3 class="doctor-name">${item.name}</h3>
                            <div class="degree">${item.degree}</div>
                            <p class="specialization">
                                ${item.getSpeciality_name()}
                            </p>
                            <button class="btn appointment-button">
                                <i class="fas fa-calendar-alt" style="color: white;"></i>
                                <span>
                                    <a href="${pageContext.request.contextPath}/patient/make-appointment?doc_id=${item.getId()}" 
                                       class="btn btn-primary rounded-pill py-md-3 px-md-5 mx-2"
                                        >
                                        Đặt khám
                                    </a>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>
    function setSpeciality(id) {
        const hiddenInput = document.getElementById("speciality");
        hiddenInput.value = id;
        const itemsList = document.getElementsByClassName("text-decoration-none mr-3");
        for (var i = 0; i < itemsList.length; i++) {
            if (itemsList[i].id === id) {
                itemsList[i].className = "h3 text-warning text-decoration-none mr-3";
            } else {
                itemsList[i].className = "h3 text-dark text-decoration-none mr-3";
            }
        }
    }
</script>



<style>
    .smaller-button {
    font-size: 80%; /* Hoặc bất kỳ giá trị phù hợp nào bạn muốn */
}

/* Định dạng cho flex container */
.flex-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between; /* Để các phần tử sắp xếp theo chiều ngang */
}

/* Định dạng cho flex item (mỗi card bác sĩ) */
.flex-item {
    flex: 0 1 calc(33.33% - 20px); /* Sử dụng 33.33% của width, với khoảng cách ngang giữa các item là 20px */
    margin-bottom: 20px; /* Khoảng cách dưới của mỗi item */
}

/* Thêm margin-top cho những flex-item từ thứ 4 trở đi */
.flex-item:nth-child(n+4) {
    margin-top: 200px;
}


    </style>