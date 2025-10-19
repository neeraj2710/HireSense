<%@ page import="in.hiresense.pojo.JobPojo" %>
<%@ page import="java.util.List" %><%--
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<%@include file="includes/header.jsp"%>
<%
    if(session == null || session.getAttribute("userId") == null || !session.getAttribute("userRole").equals("employer")){
        response.sendRedirect("login.jsp");
        return;
    }
%>
<main class="container py-5">
    <h2 class="mb-4">Welcome, <%= session.getAttribute("userName")%> 🧑‍💼</h2>

        <!-- Post-job form starts -->

        <div class="p-4 mb-4 job-form-card">
            <h5>📢 Post a new job</h5>

                <form action="PostJobServlet" method="post">
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

        <!-- Post-job form ends -->

        <!--Search and filter starts  -->

        <form action="EmployerDashboardServlet" method="post">
            <div class="row g-2">
                <div class="col-md-4">
                    <input type="text" name="search" class="form-control" placeholder="Search by title" value="${param.search}">
                </div>
                <div class="col-md-3">
                    <select name="status" class="form-select" id="" >
                        <option value="" disabled selected>Select Status</option>
                        <option value="active" ${param.status == 'active' ? 'selected' : ''}>Active</option>
                        <option value="inactive" ${param.status == 'inactive' ? 'selected' : ''}>Inactive</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select name="sort" class="form-select" id="">
                        <option value="" disabled selected>Sort by applicants</option>
                        <option value="asc" ${param.sort == 'asc' ? 'selected' : ''}>Least to most</option>
                        <option value="desc" ${param.sort == 'desc' ? 'selected' : ''}>Most to least</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-login">Search</button>
                </div>
            </div>
        </form>

        <!-- Search and filter ends -->
<%
    List<JobPojo> jobList =  (List<JobPojo>) request.getAttribute("jobList");
    if(jobList != null && !jobList.isEmpty()){
%>
        <!-- manage job listing table -->
        <div class="card bg-glass p-5 mb-4 mt-4">
            <h5>📃 <%=jobList.get(0).getCompany()%>'s Posted Jobs</h5>
            <table class="table text-white mt-3">
                <thead>
                <tr>
                    <th>Job title</th>
                    <th>Applicants</th>
                    <th>status</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for(JobPojo job : jobList){
                %>
                    <tr>
                        <td><%=job.getTitle()%></td>
                        <td><%=job.getApplicantCount()%></td>
                        <td><%=job.getStatus().toUpperCase()%></td>
                        <td>
                            <a href="ViewApplicantsServlet?jobId=<%=job.getId()%>" class="btn btn-sm btn-primary">View</a>
                            <a href="ToggleJobStatusServlet?jobId=<%=job.getId()%>" class="btn btn-sm <%=job.getStatus().equals("active")?"btn-warning":"btn-success"%>"><%=job.getStatus().equals("active")?"Deactivate":"Activate"%></a>
                        </td>
                    </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <%
            }else {
        %>
            <p class="text-center">No Jobs Posted</p>
        <%
            }
        %>
        <!-- manage job listing table ends -->
</main>
<%
    String success = request.getParameter("success");
    if("1".equals(success)){
%>
<script>
    Swal.fire({
        title: "Job Posted!",
        text: "Your job has been successfully posted!",
        timer:2000,
        icon: "success",
        showConfirmButton: false
    });
</script>
<%
    }
    String error = request.getParameter("error");
    if("1".equals(error)){
%>
<script>
    Swal.fire({
        title: "Failed!",
        text: "Something went wrong! Please try again",
        timer:2000,
        icon: "error",
        confirmButtonText: "Okay"
    });
</script>
<%
    }
%>
<%@include file="includes/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
