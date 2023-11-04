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
                    <a href="${pageContext.request.contextPath}/admin/NewsControl"><i class="fa fa-calendar" aria-hidden="true"></i> Quản tin tức của bác sĩ</a><br>
                </div>
            </div>
            <div class="col-md-8 border-right">
                <c:if test="${not empty error}">
                    <p class="text-danger">${error}</p>
                </c:if>
                <form action="" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${doctor.id}">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="name">Nhập tên bác sĩ</label>
                                <input class="form-control" type="text" id="name" name="update_name" placeholder="name" value="${doctor.name}">
                            </div>
                            <div class="form-group">
                                <label for="mail">Email</label>
                                <input class="form-control" type="email" id="mail" name="update_email" placeholder="email" value="${doctor.email}">
                            </div>

                            <div class="form-group">
                                <label for="degree">Bằng cấp</label>
                                <input class="form-control" type="text" id="degree" name="update_degree" placeholder="degree" value="${doctor.degree}">
                            </div>
                            <div class="form-group">
                                <label for="experience">Năm kinh nghiệm</label>
                                <input class="form-control" type="number" id="experience" name="update_experience" placeholder="experience" value="${doctor.experience}">
                            </div>
                            <div class="form-group">
                                <div class="row ml-2">
                                    <h4 class="mr-2 mt-4">Chuyên môn</h4>
                                    <div>
                                        <c:forEach var = "item" items="${speciality_lists}">
                                            <c:forEach var="item1" items="${speciality_doctor}">
                                               
                                                <c:if test="${item.getDoctor_id() eq item1.getId()}">
                                                    <input type="checkbox" id="myCheckbox" name="myCheckbox" value="${item.getDoctor_id()}">
                                                    <label for="myCheckbox">${item.getName_special()}</label><br>
                                                    <p>hello</p>
                                                </c:if>
                                                 <c:if test="${item.getDoctor_id()!= item1.getId()}">
                                                    <input type="checkbox" id="myCheckbox" name="myCheckbox" value="${item.getDoctor_id()}">
                                                    <label for="myCheckbox">${item.getName_special()}</label><br>
                                                     <p>hello</p>
                                                </c:if>
                          
<!--                                        <input type="checkbox" name="myCheckbox" ${item ? 'checked' : ''} />
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="1">
                                        <label for="myCheckbox">Khoa Tim Mạch</label><br>
                                         <input type="checkbox" name="myCheckbox" ${item ? 'checked' : ''} />
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="2">
                                        <label for="myCheckbox">Khoa Da Liễu</label><br>
                                         <input type="checkbox" name="myCheckbox" ${item ? 'checked' : ''} />
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="3">
                                        <label for="myCheckbox">Khoa Thần Kinh</label><br>
                                         <input type="checkbox" name="myCheckbox" ${item ? 'checked' : ''} />
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="4">
                                        <label for="myCheckbox">Khoa Tai Mũi Họng</label><br>
                                         <input type="checkbox" name="myCheckbox" ${item ? 'checked' : ''} />
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="5">
                                        <label for="myCheckbox">Khoa Sản</label><br>
                                         <input type="checkbox" name="myCheckbox" ${item ? 'checked' : ''} />
                                        <input type="checkbox" id="myCheckbox" name="myCheckbox" value="6">
                                        <label for="myCheckbox">Khoa Chỉnh Hình</label><br>-->
                                            </c:forEach>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone">Số điện thoại</label>
                                <input class="form-control" type="tel" id="phone" name="update_phone" placeholder="phone" value="${doctor.phone}">
                            </div>
                            <div class="form-group">
                                <label for="dob">Ngày sinh</label>
                                <input class="form-control" type="date" id="dob" name="update_dob" value="${doctor.dob}">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!--                                            <div class="form-group">
                                                                            <label for="file">Ảnh</label>
                                                                            <input class="form-control" type="file" id="file" name="update_image">
                                                                            <img style="max-width: 100%; object-fit: cover" src="${pageContext.request.contextPath}/${doctor.image}" alt="">
                                                                        </div>-->
                            <div class="form-group">
                                <div class="row ml-2">
                                    <h4 class="mr-2 mt-4">Chọn giới tính</h4>
                                    <select name="update_gender" class="ml-2" id="update_gender">
                                        <option ${doctor.gender == false ? "selected" : ""} value="0">Nữ</option>
                                        <option ${doctor.gender == true ? "selected" : ""} value="1">Nam</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address">Nhập địa chỉ</label>
                                <input class="form-control" type="text" id="address" name="update_address" placeholder="address" value="${doctor.address}">
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="button-style">Cập nhật</button>
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
    <%@ include file="/include/footer.jsp" %>