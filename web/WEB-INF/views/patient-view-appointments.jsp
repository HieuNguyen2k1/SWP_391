<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="custom-container">
    ${message}
    <c:if test="${not empty error}">
        <p style="color: red">${error}</p>
    </c:if>
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
                    <td class="button-container">
                        <a href="${pageContext.request.contextPath}/patient/appointment-detail?app_id=${item.id}&doc_id=${item.getDoctor_id()}">Nhấn để xem chi tiết</a>
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