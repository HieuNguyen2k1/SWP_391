<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<div class="custom-container">
    <c:if test="${not empty error}">
        <p class="text-danger">${error}</p>
    </c:if>
    <form action="" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${upda.newsid}">

        <div class="row">
            
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Thời gian đăng</label><br>
                    <input class="form-control" value="${upda.time}" type="text" id="time" name="update_time"  placeholder="Thời gian đăng" readonly="">                    
                </div>

                <div class="form-group">
                    <label for="password">Tiêu đề</label>
                    <input class="form-control" value="${upda.title}" type="text" id="title" name="update_title" placeholder="Tiêu đề" >
                </div>
                <div class="form-group">
                    <label for="degree">Mô tả ngắn</label>
                    <input class="form-control" value="${upda.scriptShort}" type="text" id="scriptshort" name="update_scriptshort" placeholder="Mô tả ngắn" >
                </div>
            </div>
            <div class="col-md-6">

                <div class="form-group">
                    <label for="experience">Mô tả đầy đủ</label>
                    <input class="form-control" value="${upda.scriptFull}" type="text" id="scriptfull" name="update_scriptfull" placeholder="Mô tả đầy đủ" >
                </div>
                <div class="form-group">
                    <label for="file">Ảnh</label>
                    <input class="form-control" type="file" id="file" name="update_image">
                    <img src="${pageContext.request.contextPath}/${upda.image}" alt="">
                </div>
                
            </div>
        </div>
        <input class="form-control" type="hidden" name="_method" value="">
        
        <button class="button-style" type="submit">Cập Nhật</button>
        <button class="button-style" onclick="history.back()" >Hủy</button>
    </form>

</div>
<%@include file="/include/footer.jsp" %>  
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
        margin-left: 30px;
        margin-right: 100px;
    }
    .row{
        margin-top: 25px;
    }
    img{    
        margin-top: 10px;
        width:400px;
        height: 100px;
    }
</style>
