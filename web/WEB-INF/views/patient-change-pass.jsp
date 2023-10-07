<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <img class="rounded-circle" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg">
                <span class="font-weight-bold">${sessionScope.acc.name}</span>
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
            <form action="" method="post">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right" style="font-size: 25px" >Đổi mật khẩu</h4>

                    </div>
                    <c:if test="${status == \"error\"}"><p class="text-danger" style="text-align: center">${message}</p><br></c:if>
                    <c:if test="${status == \"success\"}"><p class="text-success" style="text-align: center">${message}</p><br></c:if>    
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <form class="custom-form">
                                    <div class="form-group">
                                        <label class="labels">Nhập mật khẩu cũ</label>
                                        <input type="password" class="form-control" name="old-pass">
                                    </div>
                                    <div class="form-group">
                                        <label class="labels">Nhập mật khẩu mới</label>
                                        <input type="password" class="form-control" name="new-pass">
                                    </div>
                                    <div class="form-group">
                                        <label class="labels">Nhập lại mật khẩu mới</label>
                                        <input type="password" class="form-control" name="confirm-pass">
                                    </div>

                            </div>

                        </div>

                        <div class="d-flex justify-content-center">
                            <div class="mt-5 text-center">
                                <button class="btn btn-outline-dark rounded-pill py-3 px-5" type="submit">Lưu thay đổi</button>
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