<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../master/head.jsp"/>
<div class="custom-container">
    ${message}
    <div class="row">
        <div class="col-md-4">
            <form action="" method="post">
                <input type="hidden" name="_method" value="">
                <label for="name">Nhập tên chuyên khoa để thêm</label>
                <input class="form-control custom-input" id="name" type="text" name="name" style="border: 1px solid #ced4da; border-radius: 6px; padding: 5px 10px;">
                <br>
                <button class="button-style">Xác nhận</button>
            </form>
        </div>
        <div class="col-md-8">
            <table class="table" border="1">
                <tr>

                    <th>Tên chuyên khoa</th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${speciality_list}">
                    <tr>

                        <td> ${item.getName()}</td>
                        <td>
                            <div  class="row ml-5">
                                <form action="" method="POST">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" value="${item.id}" name="id">
                                    <button class="fas fa fa-trash" title="Xóa"style="color:red; margin-top: 10px; border:none;">  
                                        Xóa</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
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
