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
            <button class=" button-style" >Xác nhận</button>
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
                    <button class=" button-style">Hiển thị</button>

                </div>

            </form>

            <p class="text-danger ml-5">${error}</p> <p class="text-success">${success}</p>
            <div id="function" style="display:block;">
                <button class=" button-style"  class="ml-5mr-5 button-style" data-toggle="modal" data-target="#addModel">Thêm lịch</button>

            </div>
        </c:if>

        <!-- schedule table -->
        <div style="display: block;" id="schedule">
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
                                 if (table[i][j] != null){ //  date
                                if(compare > 0){// nếu như là ngày của quá khứ!
                                    if (table[i][j].startsWith("patient app|")){ // có hẹn
                                        String status = table[i][j].split("\\|")[2];
                                        if (status.equals("not_yet")){// chưa khám xanh dương
                                            style = "style='background-color: #1FF0FF'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/admin/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"&doc_id="+doctor.id+"'>Có hẹn</a></td>");
                                        } else if (status.equals("canceled")) { // đã huỷ, đỏ
                                            style = "style='background-color: #FF515B'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/admin/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"&doc_id="+doctor.id+"'>Đã huỷ</a></td>");
                                        } else if (status.equals("finished")) {
                                            style = "style='background-color: #84FF3C'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/admin/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"&doc_id="+doctor.id+"'>Đã khám</a></td>");
                                        }
                                    } else {
                                        style = "style='background-color: #B8B6B4'";
                                        out.print("<td " + style + ">chưa có hẹn</td>");
                                    }
                                } else {
                                    if (table[i][j].startsWith("doctor schedule|")){ // có lịch chuwa có hẹn camm
                                        style = "style='background-color: #FFA500';color : black;";
                                        out.print("<td title='Nhấn để xoá' " + style + "><a href='"+request.getContextPath()+"/admin/delete-doctor-schedule?id="+ table[i][j].split("\\|")[1] +"&url="+request.getAttribute("url")+"'>chưa có hẹn</a></td>");
                                    } else if (table[i][j].startsWith("patient app|")){ // có hẹn
                                        String status = table[i][j].split("\\|")[2];
                                        if (status.equals("not_yet")){// chưa khám xanh dương
                                            style = "style='background-color: #1FF0FF'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/admin/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"&doc_id="+doctor.id+"'>Chưa khám</a></td>");
                                        } else if (status.equals("canceled")) { // đã huỷ, đỏ
                                            style = "style='background-color: #FF515B'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/admin/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"&doc_id="+doctor.id+"'>Đã huỷ</a></td>");
                                        } else if (status.equals("finished")) {
                                            style = "style='background-color: #84FF3C'";
                                            out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/admin/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"&doc_id="+doctor.id+"'>Đã khám</a></td>");
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
        </div>

        <!-- modal Thêm duyệt Lịch -->
        <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="addModel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="addschedule" method="post">
                        <input type="hidden" name="_method" value="add_schedule">
                        <input type="hidden" name="id" value="add_schedule">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm lịch làm việc</h5>
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
                            <button type="button" class=" button-style" data-dismiss="modal">Đóng</button>
                            <button type="submit" class=" button-style">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </div> 


</div>


<style>

    .custom-container {
        width: 80%;
        margin-left: auto;
        margin-right: auto;
        font-size: 16px;
    }
    .custom-menu a {
        color: #333;
        text-decoration: none;
        display: block;
    }
    .custom-menu a:hover {
        background-color: #13C5DD;
        border-radius: 5px;
        padding: 2px 2px;
    }
    .form-control{
        font-size: 12px;
    }
    .button-style {
        background-color: #0f9bae;
        color: #ffffff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
    }
</style>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        const schedule = document.getElementById("schedule");
        const approve = document.getElementById("approve");
        const func = document.getElementById("function");
        const func1 = document.getElementById("functionConform");
        const btnapprove1 = document.getElementById("btnapprove");
//        btnapprove1.addEventListener("click", function () {
//            schedule.style.display = "none";
//            approve.style.display = "block";
//            func.style.display = "none";
//            func1.style.display = "block";
//        });
//        var cells = document.getElementsByTagName("td");
//        var listapprove = [];
//        for (var i = 0; i < cells.length; i++) {
//            var cell = cells[i];
//            cell.addEventListener("click", function () {
//                listapprove.push(this.id);
////                 var index = listapprove .indexOf(cell);
////                if (index === -1) {
////                    // Nếu chưa được chọn, thêm cellId vào danh sách
////                    listapprove.push(this.id);
////                } else {
////                    // Nếu đã được chọn, xóa cellId khỏi danh sách
////                    listapprove.splice(index, 1);
////                }
//                var myInput = document.getElementById("listcellids");
//                myInput.value = listapprove.join(",");
//            });
//        }


    });

//   

</script>

<%@ include file="/include/footer.jsp" %>
