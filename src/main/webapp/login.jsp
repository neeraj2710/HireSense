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
    <title>Login | <%=application.getAttribute("appName")%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="includes/header.jsp"%>

<div class="login-container">
<div class="login-card shadow">
    <h3 class="text-center mb-4">Welcome Back</h3>
    <%
        String error = (String)request.getAttribute("error");
        if(error != null){
    %>
    <div class="alert alert-danger text-center py-1"><%=error%></div>
    <%
        }
        String reg = request.getParameter("registered");
        if("true".equals(reg)){
    %>
    <div class="alert alert-success text-center py-1">✔️ Registration Successful</div>
    <%
        }
    %>

    <form action="LoginServlet" method="post">
        <div class="mb-3">
            <input type="email" name="email" class="form-control" placeholder="email" required>
        </div>
        <div class="mb-3">
            <input type="password" name="password" class="form-control" placeholder="password" required>
        </div>
        <button type="submit" class="btn btn-login mt-2">Login</button>
        <div class="text-center mt-3">
            <small>Don't have an account? <a href="" class="text-warning">Register</a></small>
        </div>
    </form>
</div>
</div>

<%@include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
