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


<div style="margin-top: 100px;margin-bottom: 100px" class="custom-container">
    <div>
        <a href="${pageContext.request.contextPath}/doctor/view-finished-appointment?method=finished" class="button-style custom-button">Cuộc hẹn đã khám</a>
        <a href="${pageContext.request.contextPath}/doctor/view-finished-appointment?method=not-yet" class="button-style custom-button">Cuộc hẹn chưa khám</a>
    </div>
    <div style="margin-top:30px;">
        <table class="table table-bordered" >
            <thead>
                <tr>

                    <th>Bệnh nhân</th>
                    <th>Lời nhắn</th>
                    <th>Thời gian bắt đầu</th>
                    <th>Thời gian kết thúc</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${appointments}">
                    <tr>

                        <td >
                            <a style="color: black" href="${pageContext.request.contextPath}/doctor/view-patient-info?user_id=${item.getPatient_id()}">
                                ${item.getUsername()}
                            </a>
                        </td>
                        <td>${item.getNote()}</td>
                        <td>${item.getStart()}</td>
                        <td>${item.getEnd()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/doctor/appointment-detail?app_id=${item.getId()}">
                                <button class="custom-button">
                                    <span class="bi bi-eye"></span> Xem chi tiết
                                </button>

                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<style>
    .custom-button {
        background-color: #5aac4e;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 4px;
        font-size: 14px;
    }

    .custom-button:hover {
        background-color: #0056b3;
    }

    .custom-button .bi {
        margin-right: 5px;
    }
</style>