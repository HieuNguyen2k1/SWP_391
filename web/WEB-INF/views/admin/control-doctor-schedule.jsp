<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="Model.Doctor" %>


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
</head>

<div class="custom-container">
    <div class="row">
        <form action="" >
            <input type="hidden" name="_method" value="choose_doctor">
            <div class="form-group">
                <div class="row ml-2">
                    <h4 class="mr-2 mt-4">Chọn bác sĩ</h4>
                    <select class="ml-2" name="doctor_id" id="speciality_id">
                        <option value="0" selected>Chọn bác sĩ</option>
                        <c:forEach var="item" items="${doctors}">
                            <option value="${item.id}">${item.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        <button class="button-style custom-button">Xác nhận</button>
<a href="${pageContext.request.contextPath}/admin" class="button-style custom-button">Quay lại</a>

        </form>
    </div>
    <div class="row">

     
        <c:if test="${not empty doctor}" >
            <% doctor = (Doctor) request.getAttribute("doctor");%>
            <p>Bạn đang xem lịch của bác sĩ ${doctor.getName()} </p>

            <form class="ml-5 mr-5" action="">
                <input type="hidden" name="_method" value="get_date">
                <input type="hidden" name="doctor_id" value="${doctor.getId()}">
                <div class="row">
                    <input value="${current_week}" required class="form-control m-2" name="week" style="width: 200px; height: 30px"
                           type="week">
                    <button style="background-color:#007BFF" class="button-style">Hiển thị</button>
                     
                </div>
                           
            </form>
                          
            <p class="text-danger ml-5">${error}</p> <p class="text-success">${success}</p>
             <button style="background-color:#007BFF; margin-bottom: 20px; margin-top: 0px; margin-left: 25%;" class="ml-5mr-5 button-style  custom-button" data-toggle="modal" data-target="#approveModel"  >Duyệt Lịch</button>
        </c:if>
            
        <c:if test="${not empty table}">
<!--             <button style="background-color:#007BFF; margin-bottom: 10px; margin-left: 25%;" class="ml-5mr-5 button-style  custom-button" data-toggle="modal" data-target="#approveModel"  >Duyệt Lịch</button>-->
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <% String[][] table = (String[][]) request.getAttribute("table"); %>
                        <% for (int i = 0; i < 8; i++) {%>
                        <th scope="col"><%= table[0][i]%>
                        </th>
                        <% } %>
                    </tr>
                </thead>
                <% SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");%>
                <% Date current_date = new Date(); %>
                <tbody>
                    <% for (int i = 1; i < 20; i++) { %>
                    <tr>
                        <% for (int j = 0; j < 8; j++) { %>

                        <%
                            Date cell_date = new Date();
                            try {
                                if (i >= 2 && j >= 1) {
                                    cell_date = simpleDateFormat.parse(table[1][j] + " " + table[i][0].split("->")[1]);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }%>
                        <%--                        <% String style = ""; style= table[i][j] != null ? (table[i][j].equals("doctor schedule") ? "style='background-color: #FF515B'" : "") : ""; %>--%>
                        <% String style = "";%>
                        <%
                            int compare = current_date.compareTo(cell_date);
                            if (j == 0 || i == 1) {
                                out.print("<td>" + table[i][j].replaceAll("(\\d{1,2}:\\d{2}):\\d{2}->(\\d{1,2}:\\d{2}):\\d{2}", "$1-$2") + "</td>");
                            }
                            if (table[i][j] != null) { //  date
                                if (compare > 0) {// nếu như là ngày của quá khứ!
                                    if (table[i][j].startsWith("patient app|")) { // có hẹn
                                        String status = table[i][j].split("\\|")[2];
                                        if (status.equals("not_yet")) {// chưa khám xanh dương
                                            style = "style='background-color: #1FF0FF'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='" + request.getContextPath() + "/doctor/appointment-detail?app_id=" + table[i][j].split("\\|")[1] + "'>Có hẹn</a></td>");
                                        } else if (status.equals("canceled")) { // đã huỷ, đỏ
                                            style = "style='background-color: #FF515B'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='" + request.getContextPath() + "/doctor/appointment-detail?app_id=" + table[i][j].split("\\|")[1] + "'>được nghỉ</a></td>");
                                        } else if (status.equals("finished")) {
                                            style = "style='background-color: #84FF3C'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='" + request.getContextPath() + "/doctor/appointment-detail?app_id=" + table[i][j].split("\\|")[1] + "'>Đã duyệt</a></td>");
                                        }
                                    } else {
                                        style = "style='background-color: #B8B6B4'";// xam
                                        out.print("<td " + style + ">chưa có hẹn</td>");
                                    }
                                } else {
                                    if (table[i][j].startsWith("doctor schedule|")) {
                                        String status_schedule = table[i][j].split("\\|")[2];
                                        if (status_schedule.equals("approve")) {
                                            style = "style='background-color: #84FF3C';color : black;";
//                                        out.print("<td " + style + "><a href=''></a>chưa có hẹn</td>");
                                            out.print("<td title='nhấn để xoá'" + style + "><a href='" + request.getContextPath() + "/admin/approve?doctor_id=" + table[i][j].split("\\|")[1] + "&name=yet"+table[i][j].split("\\|")[1] +"'>approved</a></td>");
//                                        out.print("<td " + style + "><a href='"+request.getContextPath()+"/doctor/delete-schedule?id="+ table[i][j].split("\\|")[3] +"'></a>chưa có hẹn</td>");
                                        } else {
                                            style = "style='background-color: #FFA500';color : black;";
//                                        out.print("<td " + style + "><a href=''></a>chưa có hẹn</td>");
                                            out.print("<td title='nhấn để duyệt'" + style + "><a href='" + request.getContextPath() + "/admin/approve?doctor_id=" + table[i][j].split("\\|")[1] + "'>awaiting approve</a></td>");
//                                        out.print("<td " + style + "><a href='"+request.getContextPath()+"/doctor/delete-schedule?id="+ table[i][j].split("\\|")[3] +"'></a>chưa có hẹn</td>");
                                        }
                                    } else if (table[i][j].startsWith("patient app|")) { // có hẹn
                                        String status = table[i][j].split("\\|")[2];
                                        if (status.equals("not_yet")) {// chưa khám xanh dương
                                            style = "style='background-color: #1FF0FF'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='" + request.getContextPath() + "/doctor/appointment-detail?app_id=" + table[i][j].split("\\|")[1] + "'>chưa duyệt</a></td>");
                                        } else if (status.equals("canceled")) { // đã huỷ, đỏ
                                            style = "style='background-color: #FF515B'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='" + request.getContextPath() + "/doctor/appointment-detail?app_id=" + table[i][j].split("\\|")[1] + "'>được nghỉ</a></td>");
                                        } else if (status.equals("approved")) {
                                            style = "style='background-color: #84FF3C'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='" + request.getContextPath() + "/doctor/appointment-detail?app_id=" + table[i][j].split("\\|")[1] + "'>Đã duyệt</a></td>");
                                        }
                                    }
                                }
                            } else {
                                out.print("<td title='Nhấn để đặt lịch.'></td>");
                            }
                        %>
                        <%--                        <td <%=style%> ><%=table[i][j] == null ? "" : table[i][j]%></td>--%>
                        <% } %>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <table>
                <tr>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">
                        <span style="color: #FF515B; font-size: 70px;">&#9632;</span>
                    </td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">Đã huỷ</td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">
                        <span style="color: #84FF3C; font-size: 70px;">&#9632;</span>
                    </td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">Đã khám</td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">
                        <span style="color: #1FF0FF; font-size: 70px;">&#9632;</span>
                    </td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">Chưa khám</td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">
                        <span style="color: #B8B6B4; font-size: 70px;">&#9632;</span>
                    </td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">Quá giờ</td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">
                        <span style="color: #FFA500; font-size: 70px;">&#9632;</span>
                    </td>
                    <td style="text-align: center; vertical-align: middle; padding: 10px;">Chưa có lịch hẹn</td>
                </tr>
            </table>

           

        </c:if>

           <!-- Phê duyệt Lịch -->
         <div class="modal fade" id="approveModel" tabindex="-1" role="dialog" aria-labelledby="approveModel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="approve" method="post">
                    <input type="hidden" name="_method" value="approve_schedule">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Duyệt lịch làm việc</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="custom-container form-group mb-1">
                            <label for="from_date">Từ ngày</label>
                            <input class="form-control" required type="date" name="from_date" id="from_date">
                        </div>
                        <div class="custom-container form-group mt-1 mb-1">
                            <label for="to_date">Đến ngày</label>
                            <input class="form-control" type="date" name="to_date" id="to_date">
                            <!--                            <p><em>Để trống phần này nếu muốn chọn 1 ngày!</em></p>-->

                        </div>
                        <div class="custom-container form-group mt-1 mb-1">
                            <label for="from">Từ (8h-17h)</label>
                            <input class="form-control" min="8" max="17" step="0.5" required type="number" name="from" id="from">
                        </div>
                        <div class="custom-container form-group mt-1">
                            <label for="to">Đến (8h-17h)</label>
                            <input class="form-control" min="8" max="17" step="0.5" required type="number" name="to" id="to">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" style="background-color:#007BFF" class="button-style" data-dismiss="modal">Đóng</button>
                        <button type="submit" style="background-color:#007BFF" class="button-style">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </div>
        
        
       

</div>
      

<style>
    .button-style{
        background-color: #234821;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
    }


    .custom-container {
        width: 80%;
        margin-left: auto;
        margin-right: auto;
        font-size: 16px;
    }

   
    a {
        text-decoration: none;
    }

  
    .button-style {
        background-color: #234821;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        text-decoration: none; /* Loại bỏ gạch chân cho nút */
    }

    /* Định dạng bảng */
    .table-bordered td,
    .table-bordered th {
        border: 2px solid #dee2e6;
    }

    .table-sm td,
    .table-sm th {
        padding: 10px; /* Đặt khoảng cách lớn hơn giữa các ô */
    }

    .custom-button {
    display: inline-block;
    text-decoration: none;
    color: white;
    background-color: #007BFF;
    padding: 8px 16px; /* Thiết lập padding cho các nút */
    border: none;
    border-radius: 5px; /* Kích thước viền cong */
    margin: 5px; /* Khoảng cách giữa các nút (nếu cần) */
    box-sizing: border-box; /* Đảm bảo padding và border không làm thay đổi kích thước */
}

</style>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const approve = document.getElementById("approve");
        const option = document.getElementById("optionApprove");
        const btnApprove = document.getElementById("approve3");
        btnApprove.addEventListener("click", function () {
            approve.style.display = "none";
            option.style.display = "block";
        })

    });



</script>

