<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Xác thực tài khoản</title>
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
            color: #333; /*Thay đổi màu chữ của tiêu đề*/
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
</head>
<body>
    <div class="container">
        <h1 class="animated-heading">${message}</h1>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.0-beta3/js/bootstrap.bundle.min.js"></script>
</body>
</html>