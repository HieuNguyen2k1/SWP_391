<%@ page import="Model.User" %>
<%@ page import="Model.Doctor" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html class="no-js" lang="zxx">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>An Tâm</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="manifest" href="site.webmanifest">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.css" />

        <!-- CSS here -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/gijgo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animated-headline.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/slick.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <style>
            body {
                font-size: 18px; /* Set the desired font size */
            }
            .custom-container {
                width: 80%;
                margin-left: auto;
                margin-right: auto;
                font-size: 16px;
            }
        </style>
    </head>
    <body>
        <!-- ? Preloader Start -->
        <div id="preloader-active">
            <div class="preloader d-flex align-items-center justify-content-center">
                <div class="preloader-inner position-relative">
                    <div class="preloader-circle"></div>
                    <div class="preloader-img pere-text">
                        <img src="${pageContext.request.contextPath}/assets/img/logo/logo_3.png" alt="">
                    </div>
                </div>
            </div>
        </div>
        <!-- Preloader Start -->
        <header>
            <!--? Header Start -->
            <div class="header-area">
                <div class="main-header header-sticky">
                    <div class="container-fluid">
                        <div class="row align-items-center">
                            <!-- Logo -->
                            <div class="col-xl-2 col-lg-2 col-md-1">
                                <div class="logo">
                                    <a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/assets/img/logo/logo_5.png"
                                                                                      alt=""></a>
                                </div>
                            </div>
                            <div class="col-xl-10 col-lg-10 col-md-10">
                                <div class="menu-main d-flex align-items-center justify-content-end">
                                    <!-- Main-menu -->
                                    <div class="main-menu f-right d-none d-lg-block">
                                        <nav>
                                            <ul id="navigation">
                                                <% if (session.getAttribute("admin") != null) { %>
                                                <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
                                                    <% } else {
                                        } %>

                                                <% if (session.getAttribute("doctor") != null) { %>
                                                <li><a href="${pageContext.request.contextPath}/doctor/schedule">Lịch làm việc</a>
                                                </li>
                                                <% } else {
                                        } %>

                                                <li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>                                  
                                                <li><a>Thông tin</a>
                                                    <ul class="submenu">
                                                        <li><a href="${pageContext.request.contextPath}/about">Phòng khám</a></li>
                                                        <li><a href="${pageContext.request.contextPath}/search-doctor">Bác sĩ</a></li>

                                                    </ul>
                                                </li>
                                                <li><a href="${pageContext.request.contextPath}/show-blog">Cẩm nang</a></li>
                                            </ul>
                                        </nav>
                                    </div>

                                    <% User user = (User) session.getAttribute("acc"); %>
                                    <% Doctor doctor = (Doctor) session.getAttribute("doctor"); %>
                                    <% if (user != null) { %>
                                    <div class="main-menu f-right d-none d-lg-block">
                                        <ul>
                                            <li><a> <i class="fa fa-user" aria-hidden="true"> Hello <%= user.getName() %>
                                                    </i> </a>
                                                <ul class="submenu">
                                                    <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                                                    <li><a href="${pageContext.request.contextPath}/patient/profile">Trang cá nhân</a></li>
                                                        <% if (session.getAttribute("acc") != null) { %>
                                                    <li><a href="${pageContext.request.contextPath}/patient/view-appointments">Lịch sử đặt lịch</a>

                                                    </li>
                                                    <li><a href="${pageContext.request.contextPath}/patient-change-pass">Đổi mật khẩu</a></li>
                                                        <% } else {
                                        } %>
                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                    <% } else if (doctor != null) {%>
                                    <div class="main-menu f-right d-none d-lg-block">
                                        <ul>
                                            <li><a> <i class="fa fa-user" aria-hidden="true"> Hello Bác
                                                        sĩ <%= doctor.getName() %>
                                                    </i> </a>
                                                <ul class="submenu">
                                                    <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                                                    <li><a href="${pageContext.request.contextPath}/doctor/profile">Trang cá
                                                            nhân</a></li>
                                                    <li><a href="${pageContext.request.contextPath}/doctor-change-pass">Đổi mật khẩu</a></li>

                                                </ul>
                                            </li>
                                        </ul>
                                    </div>
                                    <% } else { %>
                                    <div class="header-right-btn f-right d-none d-lg-block ml-15">
                                        <a href="login" class="btn header-btn">Đăng nhập/Đăng kí</a>
                                    </div>
                                    <% } %>
                                    <%--<div class="header-right-btn f-right d-none d-lg-block ml-15">
                                        <a href="register" class="btn header-btn">Đăng nhập/Đăng kí</a>
                                    </div>--%>
                                </div>
                            </div>
                            <!-- Mobile Menu -->
                            <div class="col-12">
                                <div class="mobile_menu d-block d-lg-none"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Header End -->
        </header>
        <main>