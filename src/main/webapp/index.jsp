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

<main>
    <div class="hero">
        <h1>Get Hired Smarter With AI</h1>
        <p>AI-powered resume analysis and smart job matching in one platform</p>
        <a href="#" class="btn btn-cta mt-4">Get Started</a>
    </div>

    <!-- Feature content starts-->
    <div class="container py-5">
        <div class="row text-center">
            <div class="col-md-4 mb-4">
                <div class="feature-card">
                    <h4>🧠 AI Resume Insights</h4>
                    <p>
                        Let our AI analyze your resume and extract deep insights like
                        skills, experience, and summary.
                    </p>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="feature-card">
                    <h4>🛠️ Skill Gap Analyzer</h4>
                    <p>
                        Identify missing skills by comparing your resume with job
                        requirements - instantly.
                    </p>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="feature-card">
                    <h4>🎯 Smart Job Matching</h4>
                    <p>
                        Get job suggestions that best match your resume, skills, and
                        goals - powered by intelligent Al.
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- Feature content ends -->
</main>

<%@include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
