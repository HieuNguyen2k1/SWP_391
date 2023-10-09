<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<jsp:include page="../master/head.jsp"/>
<form action="" method="post" class="bd-example">
    <div class="container">
    <div class="form-group col-md-6">
        <div class="form-group ">
            <label for="inputEmail4">Email</label>
            <input type="email" class="form-control" name="email" value="${x.email}" id="inputEmail4" placeholder="Email">
        </div>
        <div class="form-group">
            <label for="inputPassword4">Mật khẩu</label>
            <input type="password" class="form-control" name="password" value="${x.password}" id="inputPassword4" placeholder="Password">
        </div>
       
        <div class="form-group">
            <label for="inputName">Tên</label>
            <input type="text" class="form-control" name="name" value="${x.name}" id="inputName" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="inputPhone">Số điện thoại</label>
            <input type="text" class="form-control" name="phone" value="${x.phone}" id="inputPhone" placeholder="Phone">
        </div>
    </div>

    <div class="form-group col-md-6">
        <div class="form-group">
            <label for="inputDoB">Ngày sinh</label>
            <input type="date" class="form-control" name="dob" value="${x.getDob()}" id="inputDoB">
        </div>
        <div class="form-group">
            <label for="inputaddress">Địa chỉ</label>
            <input type="text" class="form-control" name="address" value="${x.getAddress()}" id="inputaddress">
        </div>
        <div class="form-group ">
            <label>Giới tính</label><br>
            <select id="gender" name="gender">
                <option ${x.gender == false ? "selected" : ""} value="0">Nữ</option>
                <option ${x.gender == true ? "selected" : ""} value="1">Nam</option>
            </select>
        </div>
        
    </div><br><br>
    
    <button class="button-style" >Cập nhật</button>
    </div>
</form>
<jsp:include page="../master/foot.jsp"/>
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