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
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="includes/header.jsp"%>

<!-- registration form starts -->

<div class="login-container">
    <div class="login-card shadow">
        <h3 class="text-center">Create Your Account</h3>

        <form action="#">
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
            <small>Alredy have an account? <a href="#" class="text-warning">Login</a></small>
        </div>

    </div>
</div>

<!-- registration form ends -->


<%@include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
