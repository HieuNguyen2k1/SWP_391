// File contact.js
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("sendEmailButton").addEventListener("click", function () {
        var fullName = document.getElementById("full_name").value;
        var phoneNumber = document.getElementById("phone_number").value;
        var userEmail = document.getElementById("email").value;
        var userAddress = document.getElementById("address").value;
        var userQuestion = document.getElementById("question").value;

        // Tạo đối tượng dữ liệu email
        var emailData = {
            fullName: fullName,
            phoneNumber: phoneNumber,
            userEmail: userEmail,
            userAddress: userAddress,
            userQuestion: userQuestion,
        };

        // Gửi email bằng Email.js
        emailjs.send("YOUR_SERVICE_ID", "YOUR_TEMPLATE_ID", emailData)
            .then(function (response) {
                console.log("Email sent successfully:", response);
                // Xử lý logic sau khi email đã được gửi thành công
                alert("Email của bạn đã được gửi thành công!");
            })
            .catch(function (error) {
                console.log("Email sent error:", error);
                // Xử lý logic sau khi gửi email bị lỗi
                alert("Có lỗi xảy ra khi gửi email. Vui lòng thử lại sau.");
            });
    });
});
