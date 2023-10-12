<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="assets/css/login.css">
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
        <meta charset="utf-8">
        <!--<link rel="stylesheet" type="text/css" href="main.css">-->
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700;800&display=swap" rel="stylesheet">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">         <!--xóa .container with: 100%-->
    </head>

    <body>
        <div class="main">
            <div class="container a-container" id="a-container">
                <h1>${name}</h1>
                <form class="form" id="a-form" method="post" action="register">
                    <h2 class="form_title title">Tạo tài khoản</h2>
                    <c:if test="${status == \"error\"}"><p class="text-danger">${mess}</p><br></c:if>
                    <c:if test="${status == \"success\"}"><p class="text-success">${mess}</p><br></c:if>
                        <input class="form__input" type="text" placeholder="Họ và tên" name="name">
                        <input class="form__input" type="email" placeholder="Email" name="email">
                        <input class="form__input" type="password" placeholder="Mật khẩu" name="pass">
                        <input class="form__input" type="password" placeholder="Xác nhận mật khẩu" name="confirm">
                        <input class="form__input" type="number" placeholder="Số điện thoại" name="phone">
                        <input class="form__input" type="date" placeholder="Ngày sinh" name="dob">
                        <select class="form__input" name="gender">
                            <option value="1">Nam</option>
                            <option value="0">Nữ</option>
                        </select>
                        <input class="form__input" type="text" placeholder="Địa chỉ" name="address">
                        <button class="button-login">ĐĂNG KÍ</button>
                    </form>
                </div>
                <div class="container b-container" id="b-container">
                    <form class="form" id="b-form" method="post" action="login">
                        <h2 class="form_title title">Đăng nhập vào Website</h2>
                        <p class="text-danger">${login_mess}</p><br>
                    <input class="form__input" type="text" placeholder="Email" name="email">
                    <input class="form__input" type="password" placeholder="Password" name="password">
                    <label for="select">Đăng nhập bằng</label>
                    <select class="form__input" name="select" id="select">
                        <option value="0">Patients</option>
                        <option value="1">Doctors</option>
                    </select>
                    <i class="fas fa-key"></i>
                    <a class="form__link" href="forgot-password" style="text-decoration: none">Quên mật khẩu?</a>
                    <button class="button-login" >ĐĂNG NHẬP</button>
                </form>
            </div>
            <div class="switch" id="switch-cnt">
                <div class="switch__circle"></div>
                <div class="switch__circle switch__circle--t"></div>

                <div class="switch__container" id="switch-c1">
                    <a href="index.jsp" class="navbar-brand">
                        <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>Medinova</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <p class="switch__description description">Để giữ kết nối với chúng tôi, vui lòng đăng nhập bằng thông tin cá nhân của bạn</p>
                    <button class="switch__button button switch-btn" id="login">ĐĂNG NHẬP</button>
                </div>
                <div class="switch__container is-hidden" id="switch-c2">

                    <a href="index.jsp" class="navbar-brand">
                        <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>Medinova</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <p class="switch__description description">Nhập thông tin cá nhân của bạn và bắt đầu hành trình với chúng tôi</p>
                    <button class="switch__button button switch-btn">ĐĂNG KÍ</button>
                </div>
            </div>
        </div>

    </body>
</html>
<script src="./assets/js/login.js"></script>
<script>
    window.onload = function () {
        const show_login = ${show_login};
        console.log(show_login);
        if (show_login === 1) {
            var button = document.getElementById("login");
            button.click();
        }
    }
</script>