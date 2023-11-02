
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
<body>
    
</body>
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