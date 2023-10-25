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
<div class="container">
    <table class="table" border="1" id="table">
        <thead>
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
                <td><a href="${pageContext.request.contextPath}/admin/appointment-detail?app_id=${item.id}&doc_id=${item.getDoctor_id()}" style="color: black">Nhấn để xem lịch đã đặt</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="../master/foot.jsp"/>
