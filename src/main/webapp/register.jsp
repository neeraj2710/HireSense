<%--
  Created by IntelliJ IDEA.
  User: neera
  Date: 9/12/2025
  Time: 12:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= application.getAttribute("appName")%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <script>
        function showSpinner(){
            document.querySelector(".btn-login").disabled=true;
            document.getElementById("LoadingSpinner").classList.remove("d-none");
        }
    </script>
</head>

<body>
<%@include file="includes/header.jsp"%>



<!-- registration form starts -->

<div class="login-container">
    <div class="login-card shadow">
        <h3 class="text-center">Create Your Account</h3>

<%--        Loding spinner code--%>
        <div id="LoadingSpinner" class="text-center d-none mt-3">
            <div class="spinner-border text-light"></div>
            <p class="mt-2">Sending OTP, please wait...</p>
        </div>
<%--        Loading spinner code ends here--%>

        <form action="OTPVerificationServlet" method="post" onsubmit="showSpinner()">
            <div class="mb-3">
                <input type="text" name="name" class="form-control" placeholder="Full Name" required>
            </div>
            <div class="mb-3">
                <input type="email" name="email" class="form-control" placeholder="email" required>
            </div>
            <div class="mb-3">
                <input type="password" name="password" class="form-control" placeholder="password" required>
            </div>
            <div class="mb-3">
                <select name="role" id="" class="form-select">
                    <option value="">Select your role</option>
                    <option value="user">Job Seeker</option>
                    <option value="employer">Employer</option>
                </select>
            </div>
            <button type="submit" class="btn btn-login mt-2">Register</button>
        </form>

        <div class="text-center mt-3">
            <small>Already have an account? <a href="#" class="text-warning">Login</a></small>
        </div>

    </div>
</div>

<!-- registration form ends -->

<%--Otp Modal code starts --%>
<%
    if("true".equals(request.getParameter("showOtp"))){
%>

<div class="modal fade show" id="otpModal" style="display: block;" aria-modal="true" role="dialog">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content bg-dark text-white">
            <div class="modal-header">
                <h5 class="modal-title">Enter OTP sent to your email</h5>
                <button type="button" class="btn-close btn-close-white" aria-label="Close" onclick="window.location.href='register.jsp'"></button>

            </div>
            <form action="RegistrationServlet" method="post">
                <div class="modal-body">
                    <%
                        if("invalid".equals(request.getParameter("error"))){
                    %>
                        <div class="alert alert-danger text-center py-1">❌ Invalid OTP</div>
                    <%
                        }
                    %>
                    <input type="text" name="otp" class="form-control mt-2" placeholder="Enter OTP" required/>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success w-100">Verify & Register</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.body.classList.add("modal-open");
</script>
<%
    }
%>
<%--Otp Modal code ends --%>

<%@include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
