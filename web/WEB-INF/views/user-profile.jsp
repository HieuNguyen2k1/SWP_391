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
    <script>
        if (window.performance && window.performance.navigation.type === 2) {
            // Trang được chuyển hướng từ servlet LogoutServlet
            history.replaceState(null, '', 'login.jsp');
            window.addEventListener('popstate', function (event) {
                window.location.href = 'login.jsp';
            });
        }
    </script>
</head>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle" width="150px" src="https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_1280.png">
                <span class="font-weight-bold">${listinfo.name}</span>
                <span class="text-black-50"></span>
            </div>
            <div class="profile-info">
                <!-- Thông tin hồ sơ -->
            </div>
            <br>
            <div class="custom-menu">
                <a href="${pageContext.request.contextPath}/patient/profile"><i class="fas fa-user"></i> Trang cá nhân</a><br>
                <a href="${pageContext.request.contextPath}/patient/view-appointments"><i class="fas fa-history"></i> Lịch sử đặt lịch</a><br>
                <a href="${pageContext.request.contextPath}/patient/patient-change-pass"><i class="fas fa-key"></i> Thay đổi mật khẩu</a><br>
                <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Thoát</a>
            </div>
        </div>

        <div class="col-md-8 border-right">
            <form action="${pageContext.request.contextPath}/patient/profile" method="post">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right" style="font-size: 25px">Chỉnh sửa trang cá nhân</h4>


                    </div>
                    <c:if test="${status eq 'error'}">
                        <p class="text-danger" style="text-align: center">${mess}</p>
                        <br>
                    </c:if>
                    <c:if test="${status eq 'success'}">
                        <p class="text-success" style="text-align: center">${mess}</p>
                        <br>
                    </c:if>
                    <%
                        session.removeAttribute("mess");
                        session.removeAttribute("status");
                    %>
                    <div class="row mt-3" style="font-size: 25px">
                        <div class="col-md-12">
                            <label class="labels">Họ và tên</label>
                            <input type="text" class="form-control" value="${listinfo.name}" name="name">
                        </div>

                        <div class="col-md-12">
                            <label class="labels">Ngày sinh</label>
                            <input type="date" class="form-control" value="${listinfo.dob}" name="DOB" >
                        </div>
                        <br><br>
                        <div class="col-md-12">
                            <label class="labels">Giới tính</label><br>
                            <select class="form-control" name="gender">
                                <option value="1" <c:if test="${listinfo.gender}">selected</c:if>>Nam</option>
                                <option value="0" <c:if test="${!listinfo.gender}">selected</c:if>>Nữ</option>
                                </select>
                            </div>


                            <div class="col-md-12">
                                <label class="labels">Địa chỉ</label>
                                <input type="text" class="form-control" value="${listinfo.address}" name="address">
                        </div>

                        <div class="col-md-12">
                            <label class="labels">Email</label>
                            <input type="text" class="form-control" value="${listinfo.email}" name="email" readonly="">
                        </div>
                        <div class="col-md-12">
                            <label class="labels">Password</label>
                            <input type="password" class="form-control" value="${listinfo.password}" name="password" readonly="">
                        </div>

                        <div class="col-md-12">
                            <label class="labels">Số điện thoại</label>
                            <input type="number" class="form-control" placeholder="" value="${listinfo.phone}" name="phone">
                        </div>

                    </div>

                    <div class="d-flex justify-content-center">
                        <div class="mt-5 text-center">
                            <button class=" button-style" type="submit">Lưu thay đổi</button>
                        </div>                      
                    </div>

                </div>
            </form>
        </div>
        <div class="col-md-4">
            <div class="p-3 py-5">

            </div>
        </div>
    </div>
</div>
<%@ include file="/include/footer.jsp" %>

<style>.custom-menu {

        padding: 10px;
        border-radius: 5px;
    }

    .custom-menu a {
        color: #333;
        text-decoration: none;
        display: block;
    }

    .custom-menu a:hover {
        background-color: #5aac4e;
    }
    .form-control{
        font-size: 12px;
    }
    .button-style {
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