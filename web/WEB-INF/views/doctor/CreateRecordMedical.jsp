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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <style>
            .ss {
                display: flex;
                align-items: center;
                margin-bottom: 1%;
            }
            .labels {
                margin-right: 10px;
                width: 40%;
                margin-left: 10px;
            }
            .containers{
                margin-top: 3%;
            }
            .text-center{
                margin-top: 1%;
            }
            #btn{
                width: 133.35px;
                height: 37.6px;
            }
            .information{
                margin-left: 10px;
                margin-bottom: 2%;
            }
            .dob{
                margin-left: 10%;
            }
            .gender{
                margin-left: 10%;
            }
            .heightweight{
                display: flex;
                align-items: center;
                margin-bottom: 1%;
            }
            .heightweight input {
                width: 15%;

            }
            .heightweight label{
                width: 10%;

            }
            #chuandoan{
                width: 100% !important;
            }
            .sss{
                display: flex;
                align-items: center;
                margin-bottom: 1%;
            }
            .sss label{
                width: 20%;

            }
            .date{
                margin-bottom: 1%;
            }
        </style>
    </head>

    <body>
        <!-- Tạo hồ sơ bệnh án -->
        <div >
            <div class="container containers">
                <div class="col-md-9 offset-md-1">
                    <h1 class=" row justify-content-center"> Hồ sơ bệnh án</h1>

                    <form action="MedicalRecord" method="post" enctype="multipart/form-data">
                        <!--================================================================================-->
                        <input type="hidden" value="${id_doctor}" name="id_doctor" class="form-control" >
                        <input type="hidden" value="${id_pre}" name="id_pre" class="form-control" >
                        <input type="hidden" value="${patient.getId()}" name="id_patient" class="form-control" >
                        <input type="hidden" value="${app.getId()}" name="id_appointment" class="form-control" >
                        <!--=================================================-->
                        <div class="text-center">
                            <div class="form-group information ">
                                <label for="patientName">Họ và tên bệnh nhân:  ${patient.getName()} </label>

                                <label class="dob" for="patientDob">Ngày sinh:  ${patient.getDob()} </label>

                                <label class ="gender" for="gender">Giới tính:  
                                    <c:if test="${patient.getGender()== false}">nữ</c:if>
                                    <c:if test="${patient.getGender()== true}">nam</c:if>
                                    </label>
                                </div>
                            </div>
                            <!--=================================================-->
                            <div class="heightweight">

                                <label class="labels">Chiều cao :</label>
                                <input  type="text" class="form-control" value="" name="height" required>
                                <label class="labels">Cân nặng :</label>
                                <input type="Number" class="form-control" value="" name="weight" required>

                            <c:if test="${patient.getGroup_blood()==null}">

                                <label class="labels">Nhóm máu :</label>
                                <input type="text" class="form-control" value="" name="group_blood">

                            </c:if>
                            <c:if test="${patient.getGroup_blood()!=null}">

                                <input type="hidden" class="form-control" value="${patient.getGroup_blood()}" name="group_blood">

                            </c:if>
                        </div>
                        <!--=================================================-->
                        <div class="form-group">
                            <label for="symptoms">Triệu chứng: ${app.getNote()} </label>
                        </div>
                        <!--=================================================-->
                        <div id="chuandoan"class="form-group" id="chuandoan">
                            <label for="diagnosis">Chuẩn đoán:</label>
                            <textarea id="diagnosis" name="chuandoan" rows="5" class="form-control" required></textarea>
                        </div>
                        <!--=================================================-->
                        <div class="sss">

                            <label for="file">Tệp tin:</label>
                            <input type="file" id="fileUpload" name="fileUpload" class="form-control" onchange="checkFileExtension(this)">

                            <label class="ml-3" for="file">Hình ảnh:</label>
                            <input type="file" id="imageUpload" name="imageUpload" class="form-control" onchange="checkFileExtension1(this)">

                        </div>
                        <!--=================================================-->
                        <h1 class=" row justify-content-center"> Đơn thuốc </h1>

                        <div class="date">
                            <label class="labels"> Số lượng ngày dùng :</label>
                            <input type="Number" class="form-control" value="" id="ngaydung" name="ngaydung">
                        </div>
                        <div class="ss">

                            <label class="labels">1 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc1" name="tenthuoc1" oninput="addRequiredAttribute()">
                            <label class="labels">Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong1" name="lieuluong1"oninput="addRequiredAttribute()">
                        </div>
                        <div class="ss">

                            <label class="labels">2 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc2" name="tenthuoc2"oninput="addRequiredAttribute2()">
                            <label class="labels"> Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong2" name="lieuluong2"oninput="addRequiredAttribute2()">
                        </div>
                        <div class="ss">

                            <label class="labels">3 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc3" name="tenthuoc3"oninput="addRequiredAttribute3()">
                            <label class="labels"> Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong3" name="lieuluong3"oninput="addRequiredAttribute3()">
                        </div>
                        <div class="ss">

                            <label class="labels">4 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc4" name="tenthuoc4"oninput="addRequiredAttribute4()">
                            <label class="labels"> Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong4" name="lieuluong4"oninput="addRequiredAttribute4()">
                        </div>
                        <div class="ss">

                            <label class="labels">5 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc5" name="tenthuoc5"oninput="addRequiredAttribute5()">
                            <label class="labels"> Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong5" name="lieuluong5"oninput="addRequiredAttribute5()">
                        </div>
                        <div class="ss">

                            <label class="labels">6 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc6" name="tenthuoc6"oninput="addRequiredAttribute6()">
                            <label class="labels"> Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong6" name="lieuluong6"oninput="addRequiredAttribute6()">
                        </div>
                        <div class="ss">

                            <label class="labels">7 Tên thuốc : </label>
                            <input type="text" class="form-control" value="" id="tenthuoc7" name="tenthuoc7"oninput="addRequiredAttribute7()">
                            <label class="labels"> Liều lượng : </label>
                            <input type="text" class="form-control" value="" id="lieuluong7" name="lieuluong7"oninput="addRequiredAttribute7()">
                        </div>
                        <!--=================================================-->

                        <input class="form-control" type="hidden" name="_method" value="">
                        <div class="text-center">


                            <button id="btn" type="submit" class="btn btn-primary">Gửi</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </body>

    <script>

        function checkFileExtension(input) {
            var fileName = input.value;
            var fileExtension = fileName.split('.').pop().toLowerCase();

            // Kiểm tra phần mở rộng của tệp tin
            if (fileExtension !== 'txt' && fileExtension !== 'docx'&& fileExtension !== 'doc') {
                alert('Đuôi file không hợp lệ. Vui lòng chọn tệp tin có đuôi .txt, .docx.');
                input.value = ''; // Xoá giá trị của input file
            }

        }
        function checkFileExtension1(input) {
            var fileName = input.value;
            var fileExtension = fileName.split('.').pop().toLowerCase();

            // Kiểm tra phần mở rộng của tệp tin
            if (fileExtension !== 'png' && fileExtension !== 'jpg') {
                alert('Đuôi file không hợp lệ. Vui lòng chọn tệp tin có đuôi .png, .jpg.');
                input.value = ''; // Xoá giá trị của input file
            }

            //==========================================================

        }
        var ngaydungField = document.getElementById("ngaydung");
        function addRequiredAttribute() {
            const tenthuoc1Value = document.getElementById("tenthuoc1").value;
            const lieuluong1Value = document.getElementById("lieuluong1").value;
            const lieuluong1 = document.getElementById("lieuluong1");
            const tenthuoc1 = document.getElementById("tenthuoc1");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }
        function addRequiredAttribute2() {
            const tenthuoc1Value = document.getElementById("tenthuoc2").value;
            const lieuluong1Value = document.getElementById("lieuluong2").value;
            const lieuluong1 = document.getElementById("lieuluong2");
            const tenthuoc1 = document.getElementById("tenthuoc2");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }
        function addRequiredAttribute3() {
           const tenthuoc1Value = document.getElementById("tenthuoc3").value;
            const lieuluong1Value = document.getElementById("lieuluong3").value;
            const lieuluong1 = document.getElementById("lieuluong3");
            const tenthuoc1 = document.getElementById("tenthuoc3");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }
        function addRequiredAttribute4() {
            const tenthuoc1Value = document.getElementById("tenthuoc4").value;
            const lieuluong1Value = document.getElementById("lieuluong4").value;
            const lieuluong1 = document.getElementById("lieuluong4");
            const tenthuoc1 = document.getElementById("tenthuoc4");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }
        function addRequiredAttribute5() {
            const tenthuoc1Value = document.getElementById("tenthuoc5").value;
            const lieuluong1Value = document.getElementById("lieuluong5").value;
            const lieuluong1 = document.getElementById("lieuluong5");
            const tenthuoc1 = document.getElementById("tenthuoc5");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }
        function addRequiredAttribute6() {
            const tenthuoc1Value = document.getElementById("tenthuoc6").value;
            const lieuluong1Value = document.getElementById("lieuluong6").value;
            const lieuluong1 = document.getElementById("lieuluong6");
            const tenthuoc1 = document.getElementById("tenthuoc6");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }
        function addRequiredAttribute7() {
            const tenthuoc1Value = document.getElementById("tenthuoc7").value;
            const lieuluong1Value = document.getElementById("lieuluong7").value;
            const lieuluong1 = document.getElementById("lieuluong7");
            const tenthuoc1 = document.getElementById("tenthuoc7");
            if (tenthuoc1Value.trim() !== "" || lieuluong1Value.trim() !== "") {
                console.log("Trường 'tenthuoc1' có giá trị.=================");
                ngaydungField.setAttribute("required", "required");
                lieuluong1.setAttribute("required", "required");
                tenthuoc1.setAttribute("required", "required");

            } else {
                ngaydungField.removeAttribute("required");
                lieuluong1.removeAttribute("required");
                tenthuoc1.removeAttribute("required");
            }
        }

    </script>

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

        #gender{
            padding-right: 15%;
        }
        #chuandoan{
            width: 60%;
        }

    </style>
