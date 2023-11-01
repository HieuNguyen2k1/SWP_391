
<head>     
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="Model.News" %> 
    <title>MEDINOVA - Hospital Website</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Condensed:wght@400;700&family=Roboto:wght@400;700&display=swap" rel="stylesheet">  
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<div class="custom-container">
    <c:if test="${not empty error}">
        <p class="text-danger">${error}</p>
    </c:if>
    <form action="" method="post" enctype="multipart/form-data">
        <input type="hidden" name="newsid" value="${news.newsid}">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Thời gian đăng</label>
                    <input class="form-control" type="text" id="time" name="update_time" placeholder="Thời gian đăng" value="${news.time}">
                </div>
                
                <div class="form-group">
                    <label for="password">Tiêu đề</label>
                    <input class="form-control" type="text" id="title" name="update_title" placeholder="Tiêu đề" value="${news.title}">
                </div>
                <div class="form-group">
                    <label for="degree">Mô tả ngắn</label>
                    <input class="form-control" type="text" id="scriptshort" name="update_scriptshort" placeholder="Mô tả ngắn" value="${news.scriptShort}">
                </div>
            </div>
            <div class="col-md-6">
                
                <div class="form-group">
                    <label for="experience">Mô tả đầy đủ</label>
                    <input class="form-control" type="text" id="scriptfull" name="update_scriptfull" placeholder="Mô tả đầy đủ" value="${news.scriptFull}">
                </div>
                <div class="form-group">
                    <label for="file">Ảnh</label>
                    <input class="form-control" type="file" id="file" name="update_image">
                    <img style="max-width: 100%; object-fit: cover" src="${pageContext.request.contextPath}/${news.image}" alt="">
                </div>
            </div>
        </div>
        <input class="form-control" type="hidden" name="_method" value="">
        <button class="button-style" type="submit">Cập Nhật</button>
    </form>

</div>
<style>
    body {
        font-size: 20px; /* Set the desired font size */
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
</style>
