<%-- 
    Document   : navbar
    Created on : Jun 26, 2023, 10:44:08 PM
    Author     : ASUS
--%>
<%@ page import="Model.User" %>
<%@ page import="Model.Doctor" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container-fluid py-2 border-bottom d-none d-lg-block">
    <div class="container">
        <div class="row">
            <div class="col-md-6 text-center text-lg-start mb-2 mb-lg-0">
                <div class="d-inline-flex align-items-center">
                    <a class="text-decoration-none text-body pe-3" href=""><i class="bi bi-telephone me-2"></i>+012 345 6789</a>
                    <span class="text-body">|</span>
                    <a class="text-decoration-none text-body px-3" href=""><i class="bi bi-envelope me-2"></i>benhvienmedinova@gmail.com</a>
                </div>
            </div>
            <div class="col-md-6 text-center text-lg-end">
                <div class="d-inline-flex align-items-center">
                    <a class="text-body px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-body px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-body px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-body px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-body ps-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid sticky-top bg-white shadow-sm">
    <div class="container">
        <nav class="navbar navbar-expand-lg bg-white navbar-light py-3 py-lg-0">
            <a href="${pageContext.request.contextPath}" class="navbar-brand">
                <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>Medinova</h1>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto py-0">
                    <a href="index.jsp" class="nav-item nav-link active">Trang Chủ</a>
                    <a href="about.jsp" class="nav-item nav-link">Chúng tôi</a>
                    <a href="service.jsp" class="nav-item nav-link">Dịch vụ</a>

                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu m-0">

                            <a href="appointment.jsp" class="dropdown-item">Appointment</a>
                            <a href="search.jsp" class="dropdown-item">Tìm kiếm</a>
                        </div>
                    </div>
                    <a href="contact.jsp" class="nav-item nav-link">Liên Hệ</a>
                    <!--<a class="btn btn-primary ml-lg-3 nav-item nav-link small-btn" href="login.jsp">ÄÄng nháº­p/ÄÄng kÃ­</a>-->
                    <div class="main-menu f-right d-none d-lg-block">
                        <nav>
                            <ul id="navigation">
                                <% if (session.getAttribute("admin") != null) { %>
                                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                                    <% } else {
                                        } %>

                                <% if (session.getAttribute("doctor") != null) { %>
                                <li><a href="${pageContext.request.contextPath}/doctor/schedule">Lịch Làm Việc</a>
                                </li>
                                <% } else {
                                    } %>
                                <% User user = (User) session.getAttribute("acc"); %>
                                <% Doctor doctor = (Doctor) session.getAttribute("doctor"); %>
                                <!--<li><a href="${pageContext.request.contextPath}">Trang chá»§</a></li>-->                                  
                                <!--                                                <li><a>ThÃ´ng tin</a>
                                -->                                                    <ul class="submenu">
                                <!--                                                        <li><a href="${pageContext.request.contextPath}/about">PhÃ²ng khÃ¡m</a></li>
                                                                                        <li><a href="${pageContext.request.contextPath}/search-doctor">BÃ¡c sÄ©</a></li>-->
                                    <% if (doctor != null) {%>
                                    <li><a href="${pageContext.request.contextPath}/doctor/view-finished-appointment">Xem các cuộc hẹn đã khám</a></li>
                                        <% } %>
                                </ul><!--
                            </li>-->
                            <!--<li><a href="${pageContext.request.contextPath}/show-blog">Cáº©m nang</a></li>-->
                            </ul>
                        </nav>
                    </div>
                    <% if (user != null) {%>
                    <div class="main-menu f-right d-none d-lg-block">
                        <ul>
                            <li><a> <i class="fa fa-user" aria-hidden="true"> <%= user.getName()%>
                                    </i> </a>
                                <ul class="submenu">
                                    <li><a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a></li>
                                    <li><a href="${pageContext.request.contextPath}/patient/profile">Trang Cá Nhân</a></li>
                                        <% if (session.getAttribute("acc") != null) { %>
                                    <li><a href="${pageContext.request.contextPath}/patient/view-appointments">Danh sách lịch Khám</a>

                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/patient/patient-change-pass">Đổi mật khẩu</a></li>
                                        <% } else {
                                            } %>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <% } else if (doctor != null) {%>
                    <div class="main-menu f-right d-none d-lg-block">
                        <ul>
                            <li><a> <i class="fa fa-user" aria-hidden="true"> 
                                        <%= doctor.getName()%>
                                    </i> </a>
                                <ul class="submenu">
                                    <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                                    <li><a href="${pageContext.request.contextPath}/doctor/profile">Trang Cá Nhân</a></li>
                                    <li><a href="${pageContext.request.contextPath}/doctor/view-appointments">Danh Sách Lịch Khám</a>
                                    <li><a href="${pageContext.request.contextPath}/doctor-view-rating">Xem Đánh Giá</a></li>

                                </ul>
                            </li>
                        </ul>
                    </div>
                    <% } else { %>
                    <a href="login" class="nav-item nav-link">Đăng Nhập/Đăng Ký­</a>
                    <% }%>

                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->