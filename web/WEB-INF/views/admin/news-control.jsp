<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../master/head.jsp"/>
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


        <button class="btn">submit</button>
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
                <td><img src="${pageContext.request.contextPath}/${item.getImage()}" style="width: 300px; object-fit: cover" alt="${item.getImage()}"/></td>
             
                <th>
                    <form action="" method="post">
                        <input class="form-control" type="hidden" name="_method" value="DELETE">
                        <input class="form-control" type="hidden" name="id" value="${item.newsid}">
                        <button class="fas fa fa-trash" title="Xóa" style="color:red;margin-top: 60px;border:none;"> Xóa </button>
                    </form>
                        <a href="${pageContext.request.contextPath}/admin/update-news?news_id=${item.newsid}">
                            <button class="fa fa-edit" title="Chỉnh sửa" 
                                                              style="color:black; margin-top: 10px; border:none;"> Chỉnh sửa</button></a>
                </th>
            </tr>
        </c:forEach>
    </table>
</div>
<jsp:include page="../master/foot.jsp"/>
