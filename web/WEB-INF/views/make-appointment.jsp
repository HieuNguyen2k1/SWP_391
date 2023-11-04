
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
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
<div class="parent">
<div class="son container"
     >
    <div class="alert alert-warning d-none" id="myAlert">
        Bạn chưa chọn thời gian.
    </div>
    <c:if test="${not empty success}">
        <div class="alert alert-success">
            ${success}
        </div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">
            ${error}
        </div>
    </c:if>
    <form action="" method="post" id="form">
        <div class="row" style="margin-top:60px">
            <div class="col-md-5" >
                <div class="row">
                    <div class="col-md-4" style="position: relative;">
                        <img style="border-radius: 50%;width: 200px; height: 200px; object-fit: cover"
                             src="${pageContext.request.contextPath}/${doctor.image}" alt="">
                    </div>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-12 mt-3">
                                <h2 class="font-weight-bold">${doctor.name}</h2>
                                <h4>Chuyên khoa: ${doctor.speciality_name}</h4>
                                <h4>Trình độ : ${doctor.degree}</h4>
                                <h4>Kinh nghiệm : ${doctor.experience} năm</h4>
                                <h4>Liên hệ : ${doctor.phone}</h4>
                            </div>
                        </div>
                    </div>
                </div>
                            <br><br>
                    <div >
                        <c:forEach items="${review}" var="item">
                            <c:forEach begin="1" end="${item.rating}">
                                <i class="bi bi-star-fill" style="color: #ffcc00"></i>
                            </c:forEach><br>
                            <label><strong>${item.namePatient}</strong></label>
                            <label>${item.dateUp}</label><br>
                            <label style="word-wrap: break-word;">${item.comment}</label><br>
                        </c:forEach>
                    </div>
            </div>
            <div class="col-md-7" >
                <div class="row">
                                    
                    <div class="col-md-10 text-left">
                        <label for="note">Mô tả triệu chứng</label><br>
                        <textarea required class="form-control" name="note" id="note" rows="5" style="resize: none; border-radius: 16px; outline: none; font-size: 18px; padding: 12px; height: 150px;"></textarea>
                        <br><br>
                        <div class="form-group" style="display: none;">
                            <label style="display: block; margin-bottom: 10px; font-size: 18px;">Thời gian bạn chọn</label><br>
                            Từ
                            <input class="form-control" readonly type="datetime-local" id="selected_time_start" style="display: block; width: 100%; padding: 10px; font-size: 16px; line-height: 1.5; color: #495057; background-color: #f8f9fa; border: 1px solid #ced4da; border-radius: 4px; transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); font-weight: bold;" readonly>
                            <br>  Đến
                            <input class="form-control" readonly type="datetime-local" id="selected_time_end" style="display: block; width: 100%; padding: 10px; font-size: 16px; line-height: 1.5; color: #495057; background-color: #f8f9fa; border: 1px solid #ced4da; border-radius: 4px; transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; box-shadow: 0 0 5px rgba(0, 0, 0, 0.1); font-weight: bold;" readonly>
                        </div>





                        <input type="hidden" name="doctor_schedule_id" id="doctor_schedule_id">
                       
                    </div>
                </div>
                    <div style="width: 40%" class="m-2">
                        <div class="form-group ">
                            <label for="dateInput">Chọn khum giờ khám bệnh</label>
                            <input type="date" id="dateInput">
                        </div>
                        <div id="available">

                        </div>
                    </div>
                 <button class="btn btn-primary btn-block mt-2" style="max-width: 650px;">Xác nhận</button>


                </div>
            </div>
        </div>
    </form>
    <%--<button class="btn btn-info" data-toggle="modal" data-target="#addModel">Thêm lịch</button>
    <div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="addModel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form action="" method="post">
                    <input type="hidden" name="_method" value="add_appointment">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="custom-container form-group mb-1">
                            <label for="date">Ngày</label>
                            <input class="form-control" required type="date" name="date" id="date">
                        </div>
                        <div class="custom-container form-group mt-1 mb-1">
                            <label for="from">Từ (8-17)</label>
                            <input class="form-control" min="8" max="16" required type="number" name="from" id="from">
                            <p class="text-warning">LƯU Ý : Thời gian đặt lịch khám mặc định là 1h</p>
                        </div>
                        <div class="custom-container form-group mb-1">
                            <label for="note">Ghi chú</label>
                            <textarea name="note" id="note" cols="45" rows="10"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>--%>
</div>
</div>
<script>
    function getTimeString(dateString) {
        let timeString = dateString.split(' ')[1];
        let hour = timeString.split(':')[0];
        let minute = timeString.split(':')[1];
        if (parseInt(hour) < 10) {
            hour = parseInt(hour).toString();
        }
        return hour + ":" + minute;
    }

    let list = ${available};
    let filteredList = null;
    let buttons = "";
    var dateInput = document.getElementById("dateInput");
    dateInput.addEventListener("change", function (event) {
        var selectedDate = event.target.value;
        filteredList = list.filter(function (obj) {
            var startDate = obj.start;
            return startDate.split(" ")[0] === selectedDate;
        });
        buttons = "";
        for (let i = 0; i < filteredList.length; i++) {
            buttons += "<style>.custom-button:active, .custom-button:focus { font-weight: bold; }</style>";
            buttons += "<button type='button' onclick=\"changeTime('" + filteredList[i].id + "', '" + filteredList[i].start + "', '" + filteredList[i].end + "')\" class='custom-button' style='padding: 13px 16px ; font-size: 15px; border-radius: 7px; transition: none; background-color: #13C5DD; margin-bottom: 5px; margin-left:23px; border: none; display: inline-flex; justify-content: center; align-items: center;'>"
                    + getTimeString(filteredList[i].start) + " - " + getTimeString(filteredList[i].end) + "</button>";



        }
        if (buttons === "") {
            buttons = "<h3>Bác sĩ không có lịch trong ngày này.</h3>";
        }
        document.getElementById("available").innerHTML = buttons;
    });

    function changeTime(id, start, end) {
        document.getElementById("selected_time_start").value = start;
        document.getElementById("selected_time_end").value = end;
        document.getElementById("doctor_schedule_id").value = id;
    }

    const myform = document.getElementById("form");
    form.addEventListener('submit', function (event) {
        const id = document.getElementById('doctor_schedule_id').value;
        if (id === "") {
            event.preventDefault(); // Prevent the default form submission
            document.getElementById('myAlert').classList.remove('d-none');
        }
    });
</script>
<style>
 
.parent {
    height: 100%; /* Chiếm toàn bộ chiều cao của màn hình */
}

.son {
/*    height: 100%;  Chiếm toàn bộ chiều cao của phần tử cha (parent) 
    text-align: center;

    padding: 20px;*/}
.son {
    max-width: 90%; /* Đặt chiều rộng tối đa bạn mong muốn */
    margin: 0 auto; /* Để căng giữa theo chiều ngang */
}
    </style>
    
    <%@ include file="/include/footer.jsp" %>