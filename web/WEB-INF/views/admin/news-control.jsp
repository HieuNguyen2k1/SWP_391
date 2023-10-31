<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="Model.News" %>
    <%@include file="/include/navbar.jsp" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>MEDINOVA - Hospital Website</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
</head>

<div class="container rounded bg-white mt-5 mb-5">

    <div class="row">
        <div class="col-md-4 border-right">
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
                    <div class="col-md-5">
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
                    </div>
                    <div class="col-md-5">
                        <div class="form-group">
                            <label for="degree">Mô tả ngắn</label>
                            <input class="form-control" type="text" id="scirptshort" name="scriptshort" placeholder="Nhập mô tả ngắn">
                        </div>
                        <div class="form-group">
                            <label for="experience">Mô tả đầy đủ</label>
                            <input class="form-control" type="text" id="scirptfull" name="scriptfull" placeholder="Nhập mô tả đầy đủ">
                        </div>
                        <div class="form-group">
                            <label for="file">Ảnh</label>
                            <input class="form-control" type="file" id="file" name="image">
                        </div> 
                    </div>
                </div>
                <input class="form-control" type="hidden" name="_method" value="">
                <button class="button-style" type="submit">Xác Nhận</button>
            </form>

            <br>
            <table class="table" border="1">

                <tr>

                    <th>Ngày đăng</th>
                    <th>Tháng đăng</th>
                    <th>Tiêu đề</th>
                    <th>Mô tả ngắn</th>
                    <th>Mô tả đầy đủ</th>
                    <th style="max-width: 600px">Ảnh</th>

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
                            <a role="button" class="fa fa-edit" title="Chỉnh sửa" 
                               style="color:black; margin-left: 10px;margin-top: 10px; border:none;"
                               href="${pageContext.request.contextPath}/admin/update-news?newsid=${item.newsid}">
                                Chỉnh sửa</a>
                            <form action="" method="post">
                                <input class="form-control" type="hidden" name="_method" value="DELETE">
                                <input class="form-control" type="hidden" name="id" value="${item.newsid}">
                                <button class="fas fa fa-trash" title="Xóa" style="color:red;margin-top: 30px;border:none;"> 
                                    Xóa
                                </button>                                
                            </form>                            
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
<style>
    body {
        font-size: 18px; /* Set the desired font size */
    }
    .custom-container {
        width: 50%;
        margin-left: auto;
        margin-right: auto;
        font-size: 16px;
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


