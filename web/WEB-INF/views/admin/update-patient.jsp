<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@include file="/include/navbar.jsp" %>
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
<!--    </head>
<form action="${pageContext.request.contextPath}/admin/patients-control" method="post" class="bd-example">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputEmail4">Email</label>
            <input type="email" class="form-control" name="email" value="${x.email}" id="inputEmail4" placeholder="Email">
        </div>
        <div class="form-group col-md-6">
            <label for="inputPassword4">Mật khẩu</label>
            <input type="password" class="form-control" name="password" value="${x.password}" id="inputPassword4" placeholder="Password">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputName">Tên</label>
            <input type="text" class="form-control" name="name" value="${x.name}" id="inputName" placeholder="Name">
        </div>
        <div class="form-group col-md-6">
            <label for="inputPhone">Số điện thoại</label>
            <input type="text" class="form-control" name="phone" value="${x.phone}" id="inputPhone" placeholder="Phone">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputDoB">Ngày sinh</label>
            <input type="date" class="form-control" name="dob" value="${x.dob}" id="inputDoB">
        </div>
        <div class="form-group col-md-6">
            <label>Giới tính</label>
            <select id="inputGender" name="gender">
                <option ${x.gender == false ? "selected" : ""} value="0">Nữ</option>
                <option ${x.gender == true ? "selected" : ""} value="1">Nam</option>
            </select>
        </div>
    </div>
    <a role="button" class="btn btn-primary text-white" type="submit">Xác nhận</a>
    <a role="button" class="btn btn-warning text-white" type="reset">Hủy</a>
</form>-->