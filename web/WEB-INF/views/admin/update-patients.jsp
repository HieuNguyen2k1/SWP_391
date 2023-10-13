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
                    <a href="${pageContext.request.contextPath}/admin/doctor-schedule-control"><i class="fa fa-calendar" aria-hidden="true"></i> Quản lý lịch của bác sĩ</a><br>
                </div>
            </div>
            <div class="col-md-8 border-right">
                <form action="" method="post" class="bd-example">
                    <div class="container">
                        <div class="form-group col-md-6">
                            <div class="form-group ">
                                <label for="inputEmail4">Email</label>
                                <input type="email" class="form-control" name="email" value="${x.email}" id="inputEmail4" placeholder="Email" readonly="">
                            </div>
                            <div class="form-group">
                                <label for="inputPassword4">Mật khẩu</label>
                                <input type="password" class="form-control" name="password" value="${x.password}" id="inputPassword4" placeholder="Password" readonly="">
                            </div>

                            <div class="form-group">
                                <label for="inputName">Tên</label>
                                <input type="text" class="form-control" name="name" value="${x.name}" id="inputName" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <label for="inputPhone">Số điện thoại</label>
                                <input type="text" class="form-control" name="phone" value="${x.phone}" id="inputPhone" placeholder="Phone">
                            </div>
                        </div>

                        <div class="form-group col-md-6">
                            <div class="form-group">
                                <label for="inputDoB">Ngày sinh</label>
                                <input type="date" class="form-control" name="dob" value="${x.getDob()}" id="inputDoB">
                            </div>
                            <div class="form-group">
                                <label for="inputaddress">Địa chỉ</label>
                                <input type="text" class="form-control" name="address" value="${x.getAddress()}" id="inputaddress">
                            </div>
                            <div class="form-group ">
                                <label>Giới tính</label><br>
                                <select id="gender" name="gender">
                                    <option ${x.gender == false ? "selected" : ""} value="0">Nữ</option>
                                    <option ${x.gender == true ? "selected" : ""} value="1">Nam</option>
                                </select>
                            </div>

                        </div><br><br>

                        <button class="button-style" >Cập nhật</button>
                    </div>
                </form>
            </div>
        </div>
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