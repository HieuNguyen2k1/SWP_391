<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="custom-container">
    <c:if test="${not empty error}">
        <p class="text-danger">${error}</p>
    </c:if>
    <form action="" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${doctor.id}">
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="name">Nhập tên bác sĩ</label>
                    <input class="form-control" type="text" id="name" name="update_name" placeholder="name" value="${doctor.name}">
                </div>
                <div class="form-group">
                    <label for="mail">Email</label>
                    <input class="form-control" type="email" id="mail" name="update_email" placeholder="email" value="${doctor.email}">
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input class="form-control" type="password" id="password" name="update_password" placeholder="password" value="${doctor.password}">
                </div>
                <div class="form-group">
                    <label for="degree">Bằng cấp</label>
                    <input class="form-control" type="text" id="degree" name="update_degree" placeholder="degree" value="${doctor.degree}">
                </div>
                <div class="form-group">
                    <label for="experience">Năm kinh nghiệm</label>
                    <input class="form-control" type="number" id="experience" name="update_experience" placeholder="experience" value="${doctor.experience}">
                </div>
                <div class="form-group">
                    <div class="row ml-2">
                        <h4 class="mr-2 mt-4">Chuyên môn</h4>
                        <select class="ml-2" name="update_speciality_id" id="speciality_id">
                            <option value="0" selected>Chuyên môn</option>
                            <c:forEach var="item" items="${speciality_list}">
                                <option ${item.id == doctor.speciality_id ? "selected" : ""} value="${item.id}">${item.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input class="form-control" type="tel" id="phone" name="update_phone" placeholder="phone" value="${doctor.phone}">
                </div>
                <div class="form-group">
                    <label for="dob">Ngày sinh</label>
                    <input class="form-control" type="date" id="dob" name="update_dob" value="${doctor.dob}">
                </div>
            </div>
            <div class="col-md-6">
<!--                <div class="form-group">
                    <label for="file">Ảnh</label>
                    <input class="form-control" type="file" id="file" name="update_image">
                    <img style="max-width: 100%; object-fit: cover" src="${pageContext.request.contextPath}/${doctor.image}" alt="">
                </div>-->
                <div class="form-group">
                    <div class="row ml-2">
                        <h4 class="mr-2 mt-4">Chọn giới tính</h4>
                        <select name="update_gender" class="ml-2" id="update_gender">
                            <option ${doctor.gender == false ? "selected" : ""} value="0">Nữ</option>
                            <option ${doctor.gender == true ? "selected" : ""} value="1">Nam</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="address">Nhập địa chỉ</label>
                    <input class="form-control" type="text" id="address" name="update_address" placeholder="address" value="${doctor.address}">
                </div>
            </div>
        </div>
        <button type="submit" class="button-style">Cập nhật</button>
    </form>

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
