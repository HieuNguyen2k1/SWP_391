
<head>     
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@ page import="Model.News" %> 
    <title>MEDINOVA - Hospital Website</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <label for="name">Ngày đăng</label>
                    <input class="form-control" type="text" id="day" name="update_day" placeholder="Ngày đăng" value="${news.day}">
                </div>
                <div class="form-group">
                    <label for="mail">Tháng đăng</label>
                    <input class="form-control" type="text" id="month" name="update_month" placeholder="Tháng đăng" value="${news.month}">
                </div>
                <div class="form-group">
                    <label for="password">Tiêu đề</label>
                    <input class="form-control" type="text" id="title" name="update_title" placeholder="Tiêu đề" value="${news.title}">
                </div>

            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="degree">Mô tả ngắn</label>
                    <input class="form-control" type="text" id="scriptshort" name="update_scriptshort" placeholder="Mô tả ngắn" value="${news.scriptShort}">
                </div>
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
        <button type="submit" class="btn">Cập Nhật</button>
    </form>

</div>
