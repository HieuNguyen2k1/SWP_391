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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <div id="1" class="custom-container" style="display:block;">
        <p class="text-danger" style="text-align: center">${mess}</p>
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
                <select name="status" id="select">
                    <option value="finished" ${ (Update == 'update' || app.getStatus() == "finished") ? "selected" : ""}>Đã khám</option>
                    <option id="canceled" value="canceled" ${app.getStatus() == "canceled" ? "selected" : ""}>Đã huỷ</option>
                    <option value="not_yet" ${app.getStatus() == "not_yet" && Update == 'null'  ? "selected" : ""}>Chưa khám</option>
                </select>
            </div>
            <c:if test="${app.getStatus() eq 'not_yet' || app.getStatus() eq 'canceled'}">
                <a class="btn btn-primary" id="record" href="${pageContext.request.getContextPath()}/doctor/MedicalRecord?id_patient=${patient.getId()}&id_app=${app.getId()}&id_doctor=${id_doctor}">Tạo bệnh án</a>

                <button type="submit" id="submit" class="button"  onclick="handleButtonClick()" >Cập nhật</button>
            </c:if>
            <c:if test="${app.getStatus() eq 'finished'}">
                <a class="btn btn-primary" id="record" href="${pageContext.request.getContextPath()}/PatientViewRecord?id_patient=${patient.getId()}&id_app=${app.getId()}&id_doctor=${id_doctor}&id_pre=${id_pre}">Xem bệnh án</a>

            </c:if>
        </form>


    </div>


    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var button = document.getElementById("submit");
            var optioncancel = document.getElementById("select");
            var the_a = document.getElementById("record");
            var update1 = '${Update}';
            console.log(update1);
            if (update1 === "null") {
                button.disabled = true;
            } else if (update1 === "update") {
                button.disabled = false;
                //============== click button cần tương tác từ người dùng
                
               document.getElementById("submit").click();

                // kích hoạt nút update

//                window.onload = function () {
//                    var xhr = new XMLHttpRequest();
//                    xhr.open("POST", "appointment-details", true);
//                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//                    xhr.send();
//                };

//                $(document).ready(function () {
//                    $.ajax({
//                        url: "appointment-details",
//                        type: "POST",
//                        success: function (response) {
//                            console.log("Button activated!");
//                        }
//                    });
//                });

            }
            //========================
            function handleButtonClick() {
                // Gửi yêu cầu đến servlet
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "appointment-details", true);
                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhr.send();
            }

            //===ẩn nút cập nhật
            the_a.classList.add("disabled");
            the_a.setAttribute("aria-disabled", "true");
            the_a.removeAttribute("href");

            optioncancel.addEventListener("click", function () {

                var selectElement = document.getElementById("select");
                var selectedValue = selectElement.value;



                if (selectedValue === "canceled") {
                    button.disabled = false;
                    the_a.classList.add("disabled");
                    the_a.setAttribute("aria-disabled", "true");
                    the_a.removeAttribute("href");
                } else if (selectedValue === "finished") {
                    button.disabled = true;
                    the_a.classList.remove("disabled");
                    the_a.removeAttribute("aria-disabled");
                    the_a.setAttribute("href", "${pageContext.request.getContextPath()}/doctor/MedicalRecord?id_patient=${patient.getId()}&id_app=${app.getId()}&id_doctor=${id_doctor}");
                                } else if (selectedValue === "not_yet") {
                                    button.disabled = true;
                                    the_a.classList.add("disabled");
                                    the_a.setAttribute("aria-disabled", "true");
                                    the_a.removeAttribute("href");
                                } else if (selectedValue === "finished" && update1 === "update") {
                                    button.disabled = false;
                                    the_a.classList.add("disabled");
                                    the_a.setAttribute("aria-disabled", "true");
                                    the_a.removeAttribute("href");
                                }
                            });




                        });
    </script>

    <style>
        a.disabled {
            pointer-events: none;
            cursor: not-allowed;
            color: gray;
            text-decoration: none;
        }
        .button[disabled] {
            opacity: 0.5; /* Đặt độ mờ */
            pointer-events: none; /* Vô hiệu hóa tương tác */
            cursor: not-allowed; /* Thay đổi kiểu con trỏ */
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
