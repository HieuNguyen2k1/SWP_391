<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="Model.News" %>
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

<div class="container rounded bg-white mt-5 mb-5">

    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle" width="180px" src="https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png">
                <span class="font-weight-bold">${listinfo.name}</span>
                <span class="text-black-50"></span>
            </div>
            <div class="profile-info">
                <!-- Thông tin hồ sơ -->
            </div>
            <br>

            <div class="custom-menu">
                <a href="${pageContext.request.contextPath}/admin/doctor-control"><i class="fas fa-users"></i> Quản lý bác sĩ</a><br>
                <a href="${pageContext.request.contextPath}/admin/patients-control"><i class="fas fa-users"></i> Quản lý bệnh nhân</a><br>
                <a href="${pageContext.request.contextPath}/admin/news-control"><i class="fas fa-users"></i> Quản lý tin tức</a><br>
                <a href="${pageContext.request.contextPath}/admin/doctor-schedule-control"><i class="fa fa-calendar" aria-hidden="true"></i> Quản lý lịch của bác sĩ</a><br>

            </div>
        </div>
<div class="custom-container">
    <form action="" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Ngày đăng</label>
                    <input class="form-control" type="text" id="day" name="day" placeholder="Nhập ngày đăng">
                </div>
                <div class="form-group">
                    <label for="mail">Tháng đăng</label>
                    <input class="form-control" type="text" id="month" name="month" placeholder="Nhập tháng đăng">
                </div>
                <div class="form-group">
                    <label for="password">Tiêu đề</label>
                    <input class="form-control" type="text" id="title" name="title" placeholder="Nhập tiêu đề">
                </div>
                <div class="form-group">
                    <label for="degree">Mô tả ngắn</label>
                    <input class="form-control" type="text" id="scirptshort" name="scriptshort" placeholder="Nhập mô tả ngắn">
                </div>
                <div class="form-group">
                    <label for="experience">Mô tả đầy đủ</label>
                    <input class="form-control" type="text" id="scirptfull" name="scriptfull" placeholder="Nhập mô tả đầy đủ">
                </div>
               
            </div>
            
            <div class="col-md-6">
                <div class="form-group">
                    <label for="file">Ảnh</label>
                    <input class="form-control" type="file" id="file" name="image">
                </div>                  
            </div>             
        </div>
            <input class="form-control" type="hidden" name="_method" value="">
            
            <button class="button-style" type="submit">Xác nhận</button>
    </form>
    <br>
    <table class="table" border="1">
        <tr>
            
            <th>Ngày đăng</th>
            <th>Tháng đăng</th>
            <th>Tiêu đề</th>
            <th>Mô tả ngắn</th>
            <th>Mô tả đầy đủ</th>
            <th style="max-width: 700px">Ảnh</th>
           
        </tr>
        <c:forEach var="item" items="${news_list}">
            <tr>
               
               
                <td>${item.getDay()}</td>
                <td>${item.getMonth()}</td>
                <td>${item.getTitle()}</td>
                <td>${item.getScriptShort()}</td>
                <td>${item.getScriptFull()}</td>
                <td><img src="${pageContext.request.contextPath}/${item.getImage()}" 
                         style="width: 300px; object-fit: cover" alt="${item.getImage()}"/></td>
             
                <th>
                    <form action="" method="post">
                        <input class="form-control" type="hidden" name="_method" value="DELETE">
                        <input class="form-control" type="hidden" name="id" value="${item.newsid}">
                        <button class="fas fa fa-trash" title="Xóa" style="color:red;margin-top: 60px;border:none;"> 
                                Xóa
                        </button>
                    </form>
                        <a href="${pageContext.request.contextPath}/admin/update-news?news_id=${item.newsid}">
                            <button class="fa fa-edit" title="Chỉnh sửa" 
                                   style="color:black; margin-top: 10px; border:none;"> Chỉnh sửa</button></a>
                </th>
            </tr>
        </c:forEach>
    </table>
</div>

<style>
    .button-style{
        background-color: #234821;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
    }
</style>
<jsp:include page="../master/foot.jsp"/>

