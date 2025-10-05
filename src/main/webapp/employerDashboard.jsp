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
    <title>Employer Dashboard | <%=application.getAttribute("appName")%></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="includes/header.jsp"%>
<%
    Integer userId = (Integer) session.getAttribute("userId");
    if(userId == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<main class="container py-5">
    <h2 class="mb-4">Welcome, <%= session.getAttribute("userName")%> 🧑‍💼</h2>

        <!-- Post-job form starts -->

        <div class="p-4 mb-4 job-form-card">
            <h5>📢 Post a new job</h5>

                <form action="#" method="post">
                    <div class="mb-3">
                        <input type="text" class="form-control" placeholder="Job title" name="title" required>
                    </div>
                    <div class="mb-3">
                            <textarea name="description" class="form-control" placeholder="Job Description" required
                                      id=""></textarea>
                    </div>
                    <div class="mb-3">
                        <input type="text" name="skills" class="form-control"
                               placeholder="required Skills(comma separated)" required>
                    </div>
                    <div class="mb-3">
                        <input type="text" name="company" class="form-control" placeholder="Company Name" required>
                    </div>
                    <div class="mb-3">
                        <select name="location" class="form-select" id="">
                            <option value="" disabled selected>Select Location</option>
                            <option value="Bangalore">Bangalore</option>
                            <option value="mumbai">Mumbai</option>
                            <option value="pune">Pune</option>
                            <option value="delhi">Delhi</option>
                            <option value="chennai">Chennai</option>
                            <option value="hyderabad">Hyderabad</option>
                            <option value="kolkata">Kolkata</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <select name="experience" class="form-select" id="">
                            <option value="" disabled selected>Select Experience</option>
                            <option value="fresher">Fresher</option>
                            <option value="1-3 years">1-3 Years </option>
                            <option value="3-5 years">3-5 Years</option>
                            <option value="5+ years">5+ Years</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <select name="packageLpa" class="form-select" id="">
                            <option value="" disabled selected>Package (LPA)</option>
                            <option value="1-3 lpa">1-3 LPA</option>
                            <option value="3-5 lpa">3-5 LPA</option>
                            <option value="5-7 lpa">5-7 LPA</option>
                            <option value="7-10 lpa">7-10 LPA</option>
                            <option value="10+ lpa">10+ LPA</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <input type="number" name="vacancies" class="form-control" placeholder="Number of vacancies" required>
                    </div>
                    <button type="submit" class="btn btn-login">Post Job</button>
                </form>
        </div>

        <!-- Post job form ends -->

        <!--Search and filter starts  -->

        <form action="#" method="post">
            <div class="row g-2">
                <div class="col-md-4">
                    <input type="text" name="search" class="form-control" placeholder="Search by title" required>
                </div>
                <div class="col-md-3">
                    <select name="status" class="form-select" id="">
                        <option value="" disabled selected>Select Status</option>
                        <option value="active">Active</option>
                        <option value="inactive">Inactive</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="sort" class="form-select" id="">
                        <option value="" disabled selected>Sort by applicants</option>
                        <option value="asc">Least to most</option>
                        <option value="desc">Most to least</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-login">Search</button>
                </div>
            </div>
        </form>

        <!-- Search and filter ends -->

        <!-- manage job listing table -->
        <div class="card p-5 mb-4 mt-4">
            <h5>📃 Manage Job Listing</h5>
            <table class="table">
                <thead>
                <tr>
                    <th>Job title</th>
                    <th>Company</th>
                    <th>Applicants</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>SDE</td>
                    <td>Google</td>
                    <td>1098</td>
                    <td>
                        <a href="#" class="btn btn-danger">Remove</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" class="text-center text-warning">
                        No Job Listings found
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <!-- manage job listing table ends -->
</main>

<%@include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
