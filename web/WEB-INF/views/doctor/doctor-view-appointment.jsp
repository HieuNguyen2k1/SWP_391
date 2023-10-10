<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="custom-container">
    <p class="text-danger">${error != null ? error : ""}</p>
    <p class="text-success">${success != null ? success : ""}</p>
    <ul>
        <li>Đây là cuộc hẹn với bệnh nhân <span class="patient-name">${patient.getName()}</span></li>
        <li>Vào ngày <span class="appointment-date" >${fn:split(time[0], ' ')[0]}</span> </li>
        <li>Từ <span class="start-time">${fn:split(time[0], ' ')[1]}</span> Đến <span class="end-time">${fn:split(time[1], ' ')[1]}</span></li>
        <li><span>Lời nhắn của bệnh nhân: ${app.getNote()}</span> </li>
        <li><span>Số điện thoại bệnh nhân: ${patient.getPhone()}</span></li>
        <li><span>Ngày sinh bệnh nhân: ${patient.getDob()}</span></li>
    </ul>

    <form action="" method="post">
        <div class="row ml-1">
            <li>Tình trạng</li>
            <select name="status" id="">
                <option value="finished" ${app.getStatus() == "finished" ? "selected" : ""}>Đã khám</option>
                <option value="canceled" ${app.getStatus() == "canceled" ? "selected" : ""}>Đã huỷ</option>
                <option value="not_yet" ${app.getStatus() == "not_yet" ? "selected" : ""}>Chưa khám</option>
            </select>
        </div>
        <button class="button">Cập nhật</button>
    </form>

</div>

<style>
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
