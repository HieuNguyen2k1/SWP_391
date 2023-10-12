<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="header-search"> <!-- Added a container for search form -->
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
<br> <br> <br>
<div class="container">
    <h2>Chuyên khoa</h2>
</div>
<br>
<div class="specialty-list">
    <form action="" method="post">
        <button style="border: none; background: none; color: transparent; outline: none; cursor: pointer;">
            <input type="hidden" name="name" value="">
            <input type="hidden" name="speciality" value="0">
            <div class="specialty-item">
                <a class="h3 ${current_spe_id == 0 ? "text-warning" : "text-dark"} text-decoration-none mr-3"
                   id="0">Tất cả</a>
            </div>
        </button>
    </form>

    <c:forEach items="${speciality_list}" var="item">
        <form action="" method="post">
            <button style="border: none; background: none; color: transparent; outline: none; cursor: pointer;">
                <input type="hidden" name="name" value="">
                <input type="hidden" name="speciality" value="${item.id}">
                <div class="specialty-item">
                    <a class="h3 ${current_spe_id == item.id ? "text-warning" : "text-dark"} text-decoration-none mr-3"
                       id="${item.id}">
                        ${item.getName()}
                    </a>
                </div>
            </button>
        </form>
    </c:forEach>
</div>
<br> <br> <br>
<div class="container">
    <h2>Chọn bác sĩ</h2>
</div>
<br> <br> <br>
<div class="container">
    <div class="row">
        <c:forEach items="${showalldoctor}" var="item">
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
                                    <a href="${pageContext.request.contextPath}/patient/make-appointment?doc_id=${item.getId()}">
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
        const itemsList = document.getElementsByClassName("text-decoration-none mr-3")
        for (var i = 0; i < itemsList.length; i++) {
            if (itemsList[i].id == id) {
                itemsList[i].className = "h3 text-warning text-decoration-none mr-3"
            } else {
                itemsList[i].className = "h3 text-dark text-decoration-none mr-3"
            }
        }
    }
</script>
