<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../master/head.jsp"/>
<div class="custom-container">
  <h1>Thông tin bệnh nhân</h1>
  <h3>Tên: <span>${user.getName()}</span></h3>
  <h3>Email: <span>${user.getEmail()}</span></h3>
  <h3>Số điện thoại: <span>${user.getPhone()}</span></h3>
  <h3>Địa chỉ: <span>${user.getAddress()}</span></h3>
  <h3>Ngày sinh: <span>${user.getDob()}</span></h3>
  <h3>Giới tính: <span>${user.isGender() == true ? "Nam" : "Nữ"}</span></h3>
</div>

<jsp:include page="../master/foot.jsp"/>
<style>
  .custom-container {
    background-color: #ffffff;
    border-radius: 10px;
    padding: 30px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    width: fit-content;
    margin: 20px auto;
    font-family: Arial, sans-serif;
  }

  .custom-container h1 {
    font-size: 32px;
    margin-bottom: 20px;
    color: #333333;
    text-transform: uppercase;
    letter-spacing: 2px;
  }

  .custom-container h3 {
    font-size: 20px;
    margin-bottom: 10px;
    color: #555555;
  }

  .custom-container h3 span {
    font-weight: bold;
    color: #777777;
  }

  .custom-container p {
    margin-top: 25px;
    color: #888888;
  }

  .custom-container p a {
    color: #333333;
    text-decoration: none;
    font-weight: bold;
  }

  .custom-container p a:hover {
    text-decoration: underline;
  }
</style>


