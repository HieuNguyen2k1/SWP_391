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
                <a href="${pageContext.request.contextPath}/admin/NewsControl"><i class="fa fa-calendar" aria-hidden="true"></i> Quản lý tin tức</a><br>
            </div>
        </div>

        <div id="doctorFormContainer" class="col-md-8 border-right " style="display: none;">
            <button id="showOtherBlockButton" class=" button-style">Hiển thị danh sách bệnh nhân</button>

            <c:if test="${status == \"error\"}"><p class="text-danger">${mess}</p><br></c:if>
            <c:if test="${status == \"success\"}"><p class="text-success">${mess}</p><br></c:if>
            <%--        Form Start--%>

            <form action="patients-control" method="post"">
                <div class="row">
                    <div class="col-md-5">
                        <div>
                            <div class="form-group">
                                <label for="name">Nhập tên bệnh nhân</label>
                                <input class="form-control" type="text" id="name" name="name" placeholder="name">
                            </div>
                            <div class="form-group">
                                <label for="mail">Email</label>
                                <input class="form-control" type="email" id="email" name="email" placeholder="email">
                            </div>
                            <div class="form-group">
                                <label for="password">Mật khẩu</label>
                                <input class="form-control" type="password" id="password" name="password" placeholder="password">
                            </div>
                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <input class="form-control" type="tel" id="phone" name="phone" placeholder="phone">
                            </div>
                        </div>
                        <div>
                            <div class="form-group">
                                <label for="dob">Ngày sinh</label>
                                <input class="form-control" type="date" id="dob" name="dob">
                            </div>
                            <div class="form-group">
                                <label for="address">Nhập địa chỉ</label>
                                <input class="form-control" type="text" id="address" name="address" placeholder="address">
                            </div>

                            <div class="form-group">
                                <div class="row ml-2">
                                    <h4 class="mr-2 mt-4">Chọn giới tính</h4>
                                    <select name="gender" class="ml-2" id="gender">
                                        <option value="0">nữ</option>
                                        <option value="1">nam</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <input class="form-control" type="hidden" name="_method" value="">


                <button style="background-color:#095c68" class="button-style" type="submit">Xác nhận</button>
            </form>


        </div>




        <div id="otherBlockContainer" class="col-md-8 border-right">
            <button id="showDoctorFormButton" class=" button-style">Tạo mới bệnh nhân</button>

            <div class="p-3 py-5">
                <table class="table" border="1" id="table">
                    <thead>
                        <tr>
                            <th></th>

                            <th>Tên</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th>Giới tính</th>
                            <th>Ngày sinh</th>
                            <th>Địa chỉ</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="x" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${x.name}</td>
                                <td>${x.email}</td>
                                <td>${x.phone}</td>
                                <td>${x.gender ? "Nam" : "Nữ"} </td>
                                <td>${x.dob}</td>
                                <td>${x.address}</td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--        table end--%>
    </div>
</div>

<!--<style>
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
</style>-->

<style>

    .custom-menu a {
        color: #333;
        text-decoration: none;
        display: block;
    }
    .custom-menu a:hover {
        background-color: #13C5DD;
        border-radius: 5px;
        padding: 2px 2px;
    }
    .form-control{
        font-size: 12px;
    }
    .button-style {
        background-color: #0f9bae;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
    }
</style>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Get references to the containers and buttons
        const doctorFormContainer = document.getElementById("doctorFormContainer");
        const otherBlockContainer = document.getElementById("otherBlockContainer");
        const showDoctorFormButton = document.getElementById("showDoctorFormButton");
        const showOtherBlockButton = document.getElementById("showOtherBlockButton");

        // Add a click event listener to the "Create New Doctor" button
        showDoctorFormButton.addEventListener("click", function () {
            // Show the doctor form container and hide the other block container
            doctorFormContainer.style.display = "block";
            otherBlockContainer.style.display = "none";
        });

        // Add a click event listener to the "Show Other Block" button
        showOtherBlockButton.addEventListener("click", function () {
            // Show the other block container and hide the doctor form container
            otherBlockContainer.style.display = "block";
            doctorFormContainer.style.display = "none";
        });
        const errorMessage = document.querySelector(".text-danger");
        if (errorMessage) {
            // Keep the doctor form container visible
            doctorFormContainer.style.display = "block";
        }
    });
</script>
<%@ include file="/include/footer.jsp" %>
