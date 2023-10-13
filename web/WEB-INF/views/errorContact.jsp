<%-- 
    Document   : errorContact
    Created on : Oct 4, 2023, 9:13:07 PM
    Author     : quang
--%>

<%-- 
    Document   : confirmationContact
    Created on : Oct 4, 2023, 9:12:44 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    body {
        background-color: #f2f2f2;
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 20px;
    }

    .container {
        max-width: 800px;
        margin: 0 auto;
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        padding: 20px;
        border-radius: 10px; /*Thêm viền bo tròn cho container*/
    }

    .animated-heading {
        text-align: center;
        animation: fadeInDown 1s ease-in-out;
        color: red; /*Thay đổi màu chữ của tiêu đề*/
        font-size: 32px; /*Thay đổi kích thước chữ của tiêu đề*/
        margin-top: 40px; /*Tạo khoảng cách giữa tiêu đề và container*/
    }

    @keyframes fadeInDown {
        0% {
            opacity: 0;
            transform: translateY(-20px);
        }
        100% {
            opacity: 1;
            transform: translateY(0);
        }
    }
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.0-beta3/css/bootstrap.min.css">
<html>
    <head>
        <title>Confirmation</title>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <!-- Centered Brand Logo -->
                <div class="mx-auto">
                    <a href="index.jsp" class="navbar-brand">
                        <h1 class="m-0 text-uppercase text-primary"><i class="fa fa-clinic-medical me-2"></i>Medinova</h1>
                    </a>
                </div>

                <!-- Navbar Toggler Button -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </nav>
            <h1 class="animated-heading">${errorMessage}</h1>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.0-beta3/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
