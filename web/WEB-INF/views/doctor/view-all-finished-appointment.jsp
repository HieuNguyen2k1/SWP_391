<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<div class="custom-container">
    <table class="table table-bordered">
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