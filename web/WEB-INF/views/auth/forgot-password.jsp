<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="assets/css/login.css">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="main.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700;800&display=swap" rel="stylesheet">
</head>


    <body>
        <div class="main">
            <div class="container a-container" id="a-container">
               <form class="form" id="b-form" action="" method="post">
                    <h2 class="form_title title">Quên mật khẩu</h2>
                    <p class="text-success">${success}</p><br>
                    <p class="text-danger">${warning}</p><br>
                    <input class="form__input" type="email" placeholder="Nhập email" name="email" id="email">
                    <input class="form__input" type="tel"  placeholder="Nhập số điện thoại "name="tel" id="tel">
                    <button class="button-login" id="forgot-password-btn">Xác nhận</button>
                </form>

            </div>
            <div class="switch" id="switch-cnt">
                <div class="switch__circle"></div>
                <div class="switch__circle switch__circle--t"></div>
                <div class="switch__container" id="switch-c1">
                    <h2 class="switch__title title">Chào bạn!</h2>
                    <p class="switch__description description">Đăng nhập - Sức khỏe của bạn, chúng tôi lo</p>
                    <button class="button-login "><a href="${pageContext.request.contextPath}/login" style="text-decoration: none; color: #f9f9f9;">ĐĂNG NHẬP</a></button>
                </div>
            </div>
        </div>

    </body>

</html>
<script src="./assets/js/login.js"></script>

