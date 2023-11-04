
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>


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


<div class="custom-container" style="margin-bottom: 200px; margin-top: 200px">
    ${message}


    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
    <div>
        <a href="${pageContext.request.contextPath}/patient/view-appointments?method=finished" class="button-style custom-button">Lịch sử khám</a>
        <a href="${pageContext.request.contextPath}/patient/view-appointments?method=not-yet" class="button-style custom-button">Cuộc hẹn chưa khám</a>
    </div>
    <table class="table table-bordered table-striped" id="table">
        <thead class="thead-dark">
            <tr>
                <th>Trạng thái</th>
                <th>Lời nhắn</th>
                <th>Thời gian bắt đầu</th>
                <th>Thời gian kết thúc</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${appointments}" var="item">
                <tr>
                    <td>${item.getStatus()}</td>
                    <td>${item.getNote()}</td>
                    <td>${item.getStart()}</td>
                    <td>${item.getEnd()}</td>
                    <td class="button-container">
                        <a href="${pageContext.request.contextPath}/patient/appointment-detail?app_id=${item.id}&doc_id=${item.getDoctor_id()}" class="btn btn-primary">Nhấn để xem chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>



<style>
    .table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }

    .table th,
    .table td {
        padding: 12px;
        border: 1px solid #ccc;
        text-align: center;
    }

    .table th {
        background-color: #f5f5f5;
        font-weight: bold;
    }

    .table tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .table a {
        color: black;
        text-decoration: none;
    }

    .table a:hover {
        text-decoration: underline;
    }

    .button-container {
        display: flex;
        justify-content: center;
    }

    .button-container a {
        padding: 8px 16px;
        background-color: #007bff;
        color: #fff;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }

    .button-container a:hover {
        background-color: #0056b3;
    }


</style>
<%@ include file="/include/footer.jsp" %>