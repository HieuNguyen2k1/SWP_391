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
            <form action="newscontrol" method="post" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-5">
                                            
                        <div class="form-group">
                            <label for="password">Tiêu đề</label>
                            <input class="form-control" type="text" id="title" name="title" placeholder="Nhập tiêu đề">
                        </div>
                        <div class="form-group">
                            <label for="degree">Preview</label>
                            <textarea class="form-control" type="text" id="scirptshort" name="scriptshort" style="
    width: 726px;
    height: 100px;"></textarea>
                        </div>
                    </div>
                    <div class="col-md-5">

                      
                        <div class="form-group">
                            <label for="file">Ảnh đính kèm</label>
                            <input class="form-control" type="file" id="file" name="image">
                        </div> 
                    </div>
                       <div class="col-md-12">
        <div class="form-group ">
            <label for="experience">Nội dung:</label>
            <textarea  class="form-control " id="scriptfull" name="scriptfull" placeholder="Nhập nội dung muốn đăng"   style="
    width: 726px;
    height: 314px;"
  ></textarea>
        </div>
    </div>
                </div>
                <input class="form-control" type="hidden" name="_method" value="">
                <button class="button-style" style="background-color:#007BFF" type="submit">Xác Nhận</button>
            </form>

            <br>
          <div class="news-list">
    <c:forEach var="item" items="${news_list}">
        <div class="news-item">
            <div class="time">${item.getTime()}</div>
            <div class="title">${item.getTitle()}</div>
            <div class="image">
                <img src="${pageContext.request.contextPath}/${item.getImage()}" 
                     style="width: 300px; object-fit: cover" alt="${item.getImage()}"/>
            </div>
            <div class="actions">
                <a role="button" class="fa fa-edit" title="Chỉnh sửa" 
                   style="color:black; margin-left: 10px;margin-top: 10px; border:none;"
                   href="${pageContext.request.contextPath}/admin/UpdateNews?newsid=${item.newsid}">
                    Chỉnh sửa
                </a>
                <form action="" method="post">
                    <input class="form-control" type="hidden" name="_method" value="DELETE">
                    <input class="form-control" type="hidden" name="id" value="${item.newsid}">
                    <button class="fas fa fa-trash" title="Xóa" style="color:red;margin-top: 30px;border:none;"> 
                        Xóa
                    </button>                                
                </form>                            
            </div>
        </div>
    </c:forEach>
</div>

        </div>
    </div>

</div>
<style>
    body {
        font-size: 19px; /* Set the desired font size */
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



