<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../master/head.jsp"/>
<div class="custom-container">
    <h1>Đây là cuộc hẹn với bệnh nhân ${patient.getName()} với ${doctor.getName()}</h1>
    <h1>Vào ngày ${fn:split(time[0], ' ')[0]}</h1>
    <h1>Từ ${fn:split(time[0], ' ')[1]} Đến ${fn:split(time[1], ' ')[1]}</h1>
    <h1>Lời nhắn của bệnh nhân : ${app.getNote()}</h1>
    <form action="" method="post">
        <div class="row ml-1">
            <h1>Tình trạng</h1>
            <select name="status" id="">
                <option value="finished" ${app.getStatus() == "finished" ? "selected" : ""}>Đã khám</option>
                <option value="canceled" ${app.getStatus() == "canceled" ? "selected" : ""}>Đã huỷ</option>
                <option value="not_yet" ${app.getStatus() == "not_yet" ? "selected" : ""}>Chưa khám</option>
            </select>
        </div>
        <button class="btn">Cập nhật</button>
    </form>

</div>
<jsp:include page="../master/foot.jsp"/>
