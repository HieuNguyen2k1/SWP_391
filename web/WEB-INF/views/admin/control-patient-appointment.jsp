<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../master/head.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="custom-container">
    <table class="table" border="1" id="table">
        <thead>
        <tr>
         
            <th>Tên</th>
            <th>Email</th>
            <th colspan="2">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="item">
            <tr>
            
                <td>${item.name}</td>
                <td>${item.email}</td>
                <td><a href="${pageContext.request.contextPath}/admin/patient-appointment?user_id=${item.id}" style="color: black">Xem tất cả cuộc hẹn của bệnh nhân này</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="../master/foot.jsp" />
