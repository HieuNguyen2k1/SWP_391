<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    </head>
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
    <div>
        <div class="container">
            <h1>Form Hồ sơ bệnh án</h1>

            <form action="MedicalRecord" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="patientName">Họ và tên:</label>
                    <input type="text" id="patientName" name="patientName" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="age">Tuổi:</label>
                    <input type="number" id="age" name="age" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="gender">Giới tính:</label>
                    <select id="gender" name="gender" class="form-control" required>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="symptoms">Triệu chứng:</label>
                    <textarea id="symptoms" name="trieuchung" rows="5" class="form-control" required></textarea>
                </div>

                <div class="form-group">
                    <label for="diagnosis">Chuẩn đoán:</label>
                    <textarea id="diagnosis" name="chuandoan" rows="5" class="form-control" required></textarea>
                </div>

                <div class="form-group">
                    <label for="fileUpload">Tệp tin:</label>
                    <input type="file" id="fileUpload" name="fileUpload" class="form-control-file">
                </div>

                <div class="form-group">
                    <label for="imageUpload">Hình ảnh:</label>
                    <input type="file" id="imageUpload" name="imageUpload" class="form-control-file">
                </div>

                <button type="submit" class="btn btn-primary">Gửi</button>
            </form>
        </div>
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
