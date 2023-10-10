<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<jsp:include page="../master/head.jsp"/>
<form action="${pageContext.request.contextPath}/admin/patients-control" method="post" class="bd-example">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputEmail4">Email</label>
            <input type="email" class="form-control" name="email" value="${x.email}" id="inputEmail4" placeholder="Email">
        </div>
        <div class="form-group col-md-6">
            <label for="inputPassword4">Mật khẩu</label>
            <input type="password" class="form-control" name="password" value="${x.password}" id="inputPassword4" placeholder="Password">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputName">Tên</label>
            <input type="text" class="form-control" name="name" value="${x.name}" id="inputName" placeholder="Name">
        </div>
        <div class="form-group col-md-6">
            <label for="inputPhone">Số điện thoại</label>
            <input type="text" class="form-control" name="phone" value="${x.phone}" id="inputPhone" placeholder="Phone">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputDoB">Ngày sinh</label>
            <input type="date" class="form-control" name="dob" value="${x.dob}" id="inputDoB">
        </div>
        <div class="form-group col-md-6">
            <label>Giới tính</label>
            <select id="inputGender" name="gender">
                <option ${x.gender == false ? "selected" : ""} value="0">Nữ</option>
                <option ${x.gender == true ? "selected" : ""} value="1">Nam</option>
            </select>
        </div>
    </div>
    <a role="button" class="btn btn-primary text-white" type="submit">Xác nhận</a>
    <a role="button" class="btn btn-warning text-white" type="reset">Hủy</a>
</form>
<jsp:include page="../master/foot.jsp"/>