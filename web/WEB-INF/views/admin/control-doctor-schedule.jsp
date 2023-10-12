<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="Model.Doctor" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="custom-container">
    <div class="row">
        <form action="">
            <input type="hidden" name="_method" value="choose_doctor">
            <div class="form-group">
                <div class="row ml-2">
                    <h4 class="mr-2 mt-4">Chuyên môn</h4>
                    <select class="ml-2" name="doctor_id" id="speciality_id">
                        <option value="0" selected>Chọn bác sĩ</option>
                        <c:forEach var="item" items="${doctors}">
                            <option value="${item.id}">${item.getName()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <button class="button-style">Xác nhận</button>
        </form>
    </div>
    <div class="row">
        <% Doctor doctor = null;%>
        <c:if test="${not empty doctor}" >
            <% doctor = (Doctor) request.getAttribute("doctor");%>
            <p>Bạn đang xem lịch của bác sĩ ${doctor.getName()}</p>
            <form class="ml-5 mr-5" action="">
                <input type="hidden" name="_method" value="get_date">
                <input type="hidden" name="doctor_id" value="${doctor.getId()}">
                <div class="row">
                    <input value="${current_week}" required class="form-control m-2" name="week" style="width: 200px; height: 30px"
                           type="week">
                    <button class="button-style">Hiển thị</button>
                </div>
            </form>
            <p class="text-danger ml-5">${error}</p> <p class="text-success">${success}</p>
        </c:if>
        <c:if test="${not empty table}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <% String[][] table = (String[][]) request.getAttribute("table"); %>
                        <% for (int i = 0; i < 8; i++) { %>
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
                                if(i>=2 && j >= 1){
                                    cell_date = simpleDateFormat.parse(table[1][j] + " " + table[i][0].split("->")[1]);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }%>
                        <%--                        <% String style = ""; style= table[i][j] != null ? (table[i][j].equals("doctor schedule") ? "style='background-color: #FF515B'" : "") : ""; %>--%>
                        <% String style = "";%>
                        <%
                            int compare = current_date.compareTo(cell_date);

                            if (j == 0 || i == 1){
                                out.print("<td>"+table[i][j].replaceAll("(\\d{1,2}:\\d{2}):\\d{2}->(\\d{1,2}:\\d{2}):\\d{2}", "$1-$2")+"</td>");
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
                    <% } %>
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
</style>
