<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="/include/navbar.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

        <div id="doctorFormContainer" class="col-md-8 border-right " style="display: none;">
            <c:if test="${status == \"error\"}"><p class="text-danger">${message}</p><br></c:if>
            <c:if test="${status == \"error\"}"><p class="text-danger">${mess}</p><br></c:if>
            <c:if test="${status == \"success\"}"><p class="text-success">${mess}</p><br></c:if>
                <form action="addDoctor" method="post"">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="form-group">
                                <label for="name">Nhập tên bác sĩ</label>
                                <input class="form-control" type="text" id="name" name="name" placeholder="name">
                            </div>
                            <div class="form-group">
                                <label for="mail">Email</label>
                                <input class="form-control" type="email" id="mail" name="email" placeholder="email">
                            </div>
                            <div class="form-group">
                                <label for="password">Mật khẩu</label>
                                <input class="form-control" type="password" id="password" name="password" placeholder="password">
                            </div>
                            <div class="form-group">
                                <label for="degree">Bằng cấp</label>
                                <input class="form-control" type="text" id="degree" name="degree" placeholder="degree">
                            </div>
                            <div class="form-group">
                                <label for="experience">Năm kinh nghiệm</label>
                                <input class="form-control" type="number" id="experience" name="experience" placeholder="experience">
                            </div>
                            <div class="form-group">
                                <div class="row ml-2">
                                    <h4 class="mr-2 mt-4">Chuyên môn</h4>
                                    <div>
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="1">
                                        <label for="myCheckbox">Khoa Tim Mạch</label><br>
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="2">
                                        <label for="myCheckbox">Khoa Da Liễu</label><br>
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="3">
                                        <label for="myCheckbox">Khoa Thần Kinh</label><br>
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="4">
                                        <label for="myCheckbox">Khoa Tai Mũi Họng</label><br>
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="5">
                                        <label for="myCheckbox">Khoa Sản</label><br>
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="6">
                                        <label for="myCheckbox">Khoa Chỉnh Hình</label><br>


                                    </div>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!--                        <div class="form-group">
                                                        <label for="file">Ảnh</label>
                                                        <input class="form-control" type="file" id="file" name="image">
                                                    </div>-->
                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <input class="form-control" type="tel" id="phone" name="phone" placeholder="phone">
                            </div>
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
                    <input class="form-control" type="hidden" name="_method" value="">


                    <button class="button-style" type="submit">Xác nhận</button>
                </form>
                <button id="showOtherBlockButton">Hiển thị danh sách bác sĩ</button>

            </div>
            <!--        <div class="col-md-4">-->
            <div id="otherBlockContainer" class="col-md-8 border-right">
                <button id="showDoctorFormButton">Tạo mới bác sĩ</button>

                <div class="p-3 py-5">
                    <table class="table" border="1">
                        <tr>

                            <th>Tên</th>
                            <th>email</th>
                            <th>Bằng cấp</th>
                            <th>Năm kinh nghiệm</th>
                            <th>Chuyên khoa</th>
                            <th style="max-width: 700px">Ảnh</th>
                            <th>Điện thoại</th>
                            <th>Giới tính</th>
                            <th>Ngày sinh</th>
                            <th>Địa chỉ</th>
                            <th></th>
                        </tr>
                    <c:forEach var="item" items="${doctor_list}">

                        <tr>
                            <td>${item.getName()}</td>
                            <td>${item.getEmail()}</td>
                            <td>${item.getDegree()}</td>
                            <td>${item.getExperience()}</td>
                            <td>
                                <c:forEach var = "item1" items="${speciality_list}">
                                    <c:if test="${item.getId() eq item1.getDoctor_id()}" >
                                        ${item1.getName_special()} <br>
                                    </c:if>  

                                </c:forEach>

                            </td>
<!--                            <td style="max-width: 200px"><img style="max-width: 100%; object-fit: cover" src="${pageContext.request.contextPath}/${item.getImage()}"
                                                              alt=""></td>-->
                            <td>${item.getPhone()}</td>
                            <td>${item.isGender() == false ? "Nữ" : "Nam"}</td>
                            <td>${item.getDob()}</td>
                            <td>${item.getAddresses()}</td>
                            <th>
                                     <c:if test="${item.isStatus() eq true}" >
                                    <a style="color: green;" href="${pageContext.request.contextPath}/admin/StatusDoctor?doc_id=${item.getId()}&&status=${item.isStatus()}">
                                        <i id="name${item.getId()}"  class="fa fa-toggle-on"  "></i> Trạng Thái</a><br>
                                    </c:if>
                                    <c:if test="${item.isStatus() eq false}" >
                                    <a style="color: red;" href="${pageContext.request.contextPath}/admin/StatusDoctor?doc_id=${item.getId()}&&status=${item.isStatus()}">
                                        <i id="name${item.getId()}"  class="fa fa-toggle-off"  "></i> Trạng Thái</a><br>
                                    </c:if> 



                                <form action="" method="post">
                                    <!--<input class="form-control" type="hidden" name="_method" value="DELETE">-->
                                    <!--<input class="form-control" type="hidden" name="_method" value="STATUSD">-->
                                    <input class="form-control" type="hidden" name="id" value="${item.id}">

                                </form>
<!--                                <a href="${pageContext.request.contextPath}/admin/update-doctor?doc_id=${item.id}"><button class="fa fa-edit" title="Chỉnh sửa" 
                                                                                                                           style="color:black; margin-top: 10px; border:none;">
                                        Chỉnh sửa</button></a>-->
                            </th>
                        </tr>

                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="../../../assets/js/crudDoctor.js" type="text/javascript"></script>

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
