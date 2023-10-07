<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>${name}</h1>
<form action="" method="post">
    <form method="post" action="${pageContext.request.contextPath}/test">
        <h2 class="form_title title">Tạo tài khoản</h2>
        <input type="text" placeholder="Họ và tên" name="name">
        <input type="email" placeholder="Email" name="email">
        <input type="password" placeholder="Mật khẩu" name="pass">
        <input type="password" placeholder="Xác nhận mật khẩu" name="confirm">
        <input type="number" placeholder="Số điện thoại" name="phone">
        <input type="date" placeholder="Ngày sinh" name="dob">
        <select name="gender">
            <option value="1">Nam</option>
            <option value="0">Nữ</option>
        </select>
        <input type="text" placeholder="Địa chỉ" name="address">
        <button class="button-login">ĐĂNG KÍ</button>
    </form>
</form>
</body>
</html>