<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>

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
        text-decoration: none; 
    }

   
    .table-bordered td,
    .table-bordered th {
        border: 2px solid #dee2e6;
    }

    .table-sm td,
    .table-sm th {
        padding: 10px; 
    }
</style>

<div class="custom-container" style="background-image: url('/img/heart-health.jpg'); margin-bottom: 100px; margin-top: 100px ">
    <div class="row">        
        <form class="ml-5 mr-5" action="" method="post">
            <input type="hidden" name="_method" value="get_date">
            <div class="row">
                <input value="${current_week}" required class="form-control m-2" name="week" style="width: 200px; height: 30px"
                       type="week">
                <button class="button-style" style="background-color:#007BFF">Hiển thị</button>
            </div>
        </form>
        <button style="background-color:#007BFF" class="ml-5mr-5 button-style" data-toggle="modal" data-target="#addModel">Thêm lịch</button>
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
                    if (table[i][j] != null){
                        if(compare > 0){
                            if (table[i][j].startsWith("patient app|")){ 
                                String status = table[i][j].split("\\|")[2];
                                if (status.equals("not_yet")){
                                    style = "style='background-color: #1FF0FF'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Có hẹn</a></td>");
                                } else if (status.equals("canceled")) { 
                                    style = "style='background-color: #FF515B'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Đã huỷ</a></td>");
                                } else if (status.equals("finished")) {
                                    style = "style='background-color: #84FF3C'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Đã khám</a></td>");
                                }
                            } else {
                                style = "style='background-color: #B8B6B4'";
                                out.print("<td " + style + ">chưa có hẹn</td>");
                            }
                        } else {
                            if (table[i][j].startsWith("doctor schedule|")){ 
                                style = "style='background-color: #FFA500';color : black;";
//                                        out.print("<td " + style + "><a href=''></a>chưa có hẹn</td>");
                                out.print("<td title='nhấn để xoá'" + style + "><a href='"+request.getContextPath()+"/doctor/delete-schedule?id="+table[i][j].split("\\|")[1]+"'>chưa có hẹn</a></td>");
//                                        out.print("<td " + style + "><a href='"+request.getContextPath()+"/doctor/delete-schedule?id="+ table[i][j].split("\\|")[3] +"'></a>chưa có hẹn</td>");

                            } else if (table[i][j].startsWith("patient app|")){
                                String status = table[i][j].split("\\|")[2];
                                if (status.equals("not_yet")){
                                    style = "style='background-color: #1FF0FF'";
                                    out.print("<td " + style + "><a title='Nhấn để xem' href='"+request.getContextPath()+"/doctor/appointment-detail?app_id="+table[i][j].split("\\|")[1]+"'>Chưa khám</a></td>");
                                } else if (status.equals("canceled")) { 
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
   
</div>

<!--<style>
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
</style>-->