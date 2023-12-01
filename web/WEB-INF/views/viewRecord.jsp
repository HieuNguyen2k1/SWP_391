<head>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="/include/navbar.jsp" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            margin-left: 20%;
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
        .information label{
            margin-left: 0px;
            margin-right: 0px;/*
            margin-bottom: 2%;*/
            display: block;
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

    </style>
</head>

<body >


    <div class="container containers" >
        <div class=" row ">
            <div class="col-md-6">
                <h1 class=" row justify-content-center"> Hồ sơ bệnh án</h1>


                <!--================================= Thông tin cơ bản ==================================-->
                <div class="row">
                    <div class=" information col-md-6">
                        <label for="patientName">Họ và tên bệnh nhân:  ${patient.getName()} </label>

                        <label class="height">Chiều cao: ${record.getHeight()}</label>

                        <label class="blood">Nhóm máu: ${patient.getGroup_blood()} </label>



                    </div>
                    <!--================================-->
                    <div class="information  col-md-6">

                        <label class="dob" for="patientDob">Ngày sinh:  ${patient.getDob()} </label>


                        <label class=" weight">Cân nặng: ${record.getWeight()} Kg</label>

                        <label class ="gender" for="gender">Giới tính:  
                            <c:if test="${patient.getGender()== false}">nữ</c:if>
                            <c:if test="${patient.getGender()== true}">nam</c:if>
                            </label>
                        </div>

                    </div>
                    <!--==================================-->



                    <div class="form-group">
                        <label for="patientName" name="id_patient">Thời gian khám: ${record.getTime()}</label>

                </div>
                <div class="form-group">
                    <label for="diagnosis">Chuẩn đoán của bác sĩ: ${record.getRecord_text()} </label>

                </div>
                <!--=================================================-->


                <!--=================== Nội dung khám ========================-->
                <div class="sss">

                    <c:if test="${record.getImageRecord()!= null}">
                        <div class="row">

                            <label for="file">Ảnh XQuang:</label>

                            <div class="col-6 md-0 left-align" >
                                <a class="d-inline-block" href="">                                        
                                    <img  width="200px" height="200px" class="card-img rounded-0" src="${pageContext.request.contextPath}/${record.getImageRecord()}" alt="" >    
                                </a> 
                            </div> 
                        </div>
                    </c:if>
                    <c:if test="${record.getFile() != null}">
                        <div class="row">

                            <label for="file">File khác:</label>

                            <div class="col-6 md-0 left-align" >
                                <a class="d-inline-block" href="DowloadFile?id_record=${record.getIdRecord()}"> tải xuống

                                </a> 
                            </div> 
                        </div>
                    </c:if>

                    <c:if test="${record.getFile() == null}">
                        <div class="row">


                        </div>
                    </c:if>

                </div>
            </div>

            <!--================ Đơn thuốc ====================-->
            <div class="col-md-6">
                <c:if test="${pre.getId() != null}">
                    <div>
                        <h1 class="row justify-content-center">Đơn thuốc</h1>
                        <c:forEach items="${medicine}" var="item" varStatus="loop">
                            <div class="ss">
                                <label class="labels">${loop.index + 1} Tên thuốc: ${item.getName()}</label>
                                <label class="">Liều lượng: ${item.getLieuluong()}</label>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
        <div >

        </div>     
    </div>



</body>
<%@include file="/include/footer.jsp" %>
<style>
    body {
        font-size: 19px; /* Set the desired font size */
    }
    .custom-container {
        width: 50%;
        margin-left: auto;
        margin-right: auto;
        font-size: 25px;
    }
    .button-style{
        background-color: #234821;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 7px;
        font-size: 15px;
        font-weight: bold;
        cursor: pointer;
    }
</style>

