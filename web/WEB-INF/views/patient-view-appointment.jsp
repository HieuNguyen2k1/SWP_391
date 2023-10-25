
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<style>
    @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');
    .rating input{
        display: none;
    }

    .rating label{
        font-size: 15px;
        padding-top: 4px;
        padding: 2px 0 2px 2px;
        color: #444;
        float: right;
        transition: all 0.2s ease;
    }
    input:not(:checked) ~ label:hover,
    input:not(:checked) ~ label:hover ~ label{
        color: #fd4;
    }
    input:checked ~ label{
        color: #fd4;
    }
    input#rate-5:checked ~ label{
        color: #fe7;
        text-shadow: 0 0 5px;
    }
    input:checked ~ form{
        display: block;
    }


    .custom-container {
        background-color: #f5f5f5;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    h1 {
        font-size: 28px;
        margin: 10px 0;
    }

    ul {
        font-size: 20px;
        list-style-type: none;
        padding: 0;
        margin: 0;
    }

    li {
        margin-bottom: 10px;
    }

    .patient-name,
    .doctor-name,
    .appointment-date,
    .start-time,
    .end-time,
    .patient-note {
        font-size: 22px;
        font-weight: bold;
    }

    .row {
        display: flex;
        align-items: center;
        margin-left: 5px;
    }

    select {
        width: 150px;
        height: 30px;
        margin-left: 10px;
    }

    .button {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        cursor: pointer;
        font-size: 18px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        transition: background-color 0.3s ease;
        margin-top: 20px;
    }


</style>
<div class="container">

    <ul>
        <li>Đây là cuộc hẹn với bệnh nhân <span class="patient-name">${patient.getName()}</span> với <span class="doctor-name">${doctor.getName()}</span></li>
        <li>Vào ngày <span class="appointment-date">${fn:split(time[0], ' ')[0]}</span></li>
        <li>Từ <span class="start-time">${fn:split(time[0], ' ')[1]}</span> Đến <span class="end-time">${fn:split(time[1], ' ')[1]}</span></li>
        <li>Lời nhắn của bệnh nhân: <span class="patient-note">${app.getNote()}</span></li>
    </ul>

    <div class="row ml-1">
        <li>Trạng thái</li>
        <select name="status" disabled id="">
            <option value="finished" ${app.getStatus() == "finished" ? "selected" : ""}>Đã khám</option>
            <option value="canceled" ${app.getStatus() == "canceled" ? "selected" : ""}>Đã huỷ</option>
            <option value="not_yet" ${app.getStatus() == "not_yet" ? "selected" : ""}>Chưa khám</option>
        </select>
    </div>
    <c:if test="${app.getStatus() == 'not_yet'}">
        <a href="${pageContext.request.contextPath}/patient/cancel-appointment?app_id=${app.getId()}">
            <button class="button">Huỷ lịch</button>
        </a>
    </c:if>
    <br><br>
    <c:if test="${app.getStatus() == 'finished'}">
        <form action="review" method="">
            <div class="form-group">
                <label>Để lại đánh giá</label>
                <div class="rating">
                    <div class="form-check form-check-inline">
                        <input value="5" type="radio" name="rate" id="rate-5" class="form-check-input">
                        <label for="rate-5" class="bi bi-star-fill form-check-label"></label>
                        <input value="4" type="radio" name="rate" id="rate-4" class="form-check-input">
                        <label for="rate-4" class="bi bi-star-fill form-check-label"></label>
                        <input value="3" type="radio" name="rate" id="rate-3" class="form-check-input">
                        <label for="rate-3" class="bi bi-star-fill form-check-label"></label>
                        <input value="2" type="radio" name="rate" id="rate-2" class="form-check-input">
                        <label for="rate-2" class="bi bi-star-fill form-check-label"></label>
                        <input value="1" type="radio" name="rate" id="rate-1" class="form-check-input">
                        <label for="rate-1" class="bi bi-star-fill form-check-label"></label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="cmt">Bình luận:</label>
                <textarea class="form-control" id="cmt" name="cmt" rows="10" placeholder="Bình luận ..." style="font-size: 15px;resize: none;"></textarea>


            </div>
            <input type="hidden" name="iddoctor" value="${doctor.id}">
            <button class="btn btn-primary" type="submit">Bình luận</button>
        </form>
    </c:if>


</div>


</div>
<%@ include file="/include/footer.jsp" %>