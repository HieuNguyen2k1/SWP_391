<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<style>
    .custom-container {
        width: 80%;
        margin-left: auto;
        margin-right: auto;
        font-size: 16px;
    }
</style>
<div class="custom-container">
    <div class="row">        
        <form class="ml-5 mr-5" action="" method="post">
            <input type="hidden" name="_method" value="get_date">
            <div class="row">
                <input value="${current_week}" required class="form-control m-2" name="week" style="width: 200px; height: 30px"
                       type="week">
                <button class="button-style">Hiển thị</button>
            </div>
        </form>
        <button class="ml-5mr-5 button-style" data-toggle="modal" data-target="#addModel">Thêm lịch</button>
        <p class="text-danger ml-5">${error}</p> <p class="text-success">${success}</p>
    </div>
    <br>
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
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Có hẹn</a></td>");
                                } else if (status.equals("canceled")) { // đã huỷ, đỏ
                                    style = "style='background-color: #FF515B'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Đã huỷ</a></td>");
                                } else if (status.equals("finished")) {
                                    style = "style='background-color: #84FF3C'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Đã khám</a></td>");
                                }
                            } else {
                                style = "style='background-color: #B8B6B4'";// xam
                                out.print("<td " + style + ">chưa có hẹn</td>");
                            }
                        } else {
                            if (table[i][j].startsWith("doctor schedule|")){ // có lịch chuwa có hẹn camm
                                style = "style='background-color: #FFA500';color : black;";
//                                        out.print("<td " + style + "><a href=''></a>chưa có hẹn</td>");
                                out.print("<td title='nhấn để xoá'" + style + "><a href='"+request.getContextPath()+"/doctor/delete-schedule?id="+table[i][j].split("\\|")[1]+"'>chưa có hẹn</a></td>");
//                                        out.print("<td " + style + "><a href='"+request.getContextPath()+"/doctor/delete-schedule?id="+ table[i][j].split("\\|")[3] +"'></a>chưa có hẹn</td>");
                            } else if (table[i][j].startsWith("patient app|")){ // có hẹn
                                String status = table[i][j].split("\\|")[2];
                                if (status.equals("not_yet")){// chưa khám xanh dương
                                    style = "style='background-color: #1FF0FF'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Chưa khám</a></td>");
                                } else if (status.equals("canceled")) { // đã huỷ, đỏ
                                    style = "style='background-color: #FF515B'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Đã huỷ</a></td>");
                                } else if (status.equals("finished")) {
                                    style = "style='background-color: #84FF3C'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Đã khám</a></td>");
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
    <tr>
        <td><span style="color: #FF515B; font-size: 70px">&#9632;</span></td>
        <td>Đã huỷ</td>
    </tr>
    <tr>
        <td><span style="color: #84FF3C; font-size: 70px">&#9632;</span></td>
        <td>Đã khám</td>
    </tr>
    <tr>
        <td><span style="color: #1FF0FF; font-size: 70px">&#9632;</span></td>
        <td>Chưa khám</td>
    </tr>
    <tr>
        <td><span style="color: #B8B6B4; font-size: 70px">&#9632;</span></td>
        <td>Quá giờ</td>
    </tr>
    <tr>
        <td><span style="color: #FFA500; font-size: 70px">&#9632;</span></td>
        <td>Chưa có lịch hẹn</td>
    </tr>
    <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="addModel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="" method="post">
                    <input type="hidden" name="_method" value="add_schedule">
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
                            <p><em>Để trống phần này nếu muốn chọn 1 ngày!</em></p>

                        </div>
                        <div class="custom-container form-group mt-1 mb-1">
                            <label for="from">Từ (8h-17h)</label>
                            <input class="form-control" min="8" max="17" step="0.5" required type="number" name="from" id="from">
                        </div>
                        <div class="custom-container form-group mt-1">
                            <label for="to">Đến (8h-17h, phải lớn hơn thời gian bắt đầu)</label>
                            <input class="form-control" min="8" max="17" step="0.5" required type="number" name="to" id="to">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="button-style" data-dismiss="modal">Đóng</button>
                        <button type="submit" class="button-style">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<style>
    .button-style {
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