<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page
	import="in.hiresense.pojo.ApplicationPojo, in.hiresense.pojo.JobPojo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Applicants | <%=application.getAttribute("appName")%></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">
<link href="/css/style.css"
	rel="stylesheet">
<style>
body {
	background-color: #f2f4ff;
	color: #212529;
}

.applicant-card {
	background-color: white;
	border-radius: 1rem;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
	padding: 1.5rem;
	height: 100%;
	position: relative;
	transition: all 0.3s ease;
}

.applicant-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
}

.match-badge {
	position: absolute;
	bottom: 15px;
	right: 15px;
	background-color: #007bff;
	color: white;
	font-size: 12px;
	padding: 4px 10px;
	border-radius: 12px;
}

.small-btn {
	font-size: 0.8rem;
	padding: 3px 8px;
}

.text-dark p, .text-dark span, .text-dark strong {
	color: #000 !important;
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>

	<main class="container py-5">
		<div class="card bg-white p-4 mb-4 border-0 shadow-sm rounded-4">
            <h4>Company Name</h4>>
            <p class="text-muted">
				<i class="bi bi-geo-alt"></i>
				Location
				&nbsp; <i class="bi bi-briefcase"></i>
				Experience
				&nbsp; <i class="bi bi-currency-rupee"></i>
				Package
				&nbsp; <i class="bi bi-people"></i>
				Vacancies
			</p>
		</div>

		<div class="card bg-white p-4 mb-4 border-0 shadow-sm rounded-4">
			<form method="get" action="#">
				<input type="hidden" name="jobId" />
				<div class="row align-items-center">
					<div class="col-md-4">
						<label for="status" class="form-label">Filter by Status</label> 
                        <select
							name="status" class="form-select">
							<option value="applied">
								Applied
							<option value="shortlisted">
								Shortlisted </option>
							<option value="rejected">
								Rejected</option>
						</select>
					</div>
				</div>
			</form>
		</div>

		<h5 class="mb-3">👤 Applicants List</h5>
		<div class="row g-4">

			<div class="col-md-6 col-lg-4">
				<div class="applicant-card">
					<h6 class="fw-bold">
						👤 User ID: 101</h6>
					<p class="mb-1">
						<i class="bi bi-bar-chart"></i> <strong>Status:</strong> <span
							class="text-capitalize">Applied</span>
					</p>
					<p class="mb-1">
						<i class="bi bi-calendar-check"></i> <strong>Applied: 2025-10-30 19:36:26</strong>
						</p>
					<p class="mb-1">
						<i class="bi bi-file-earmark-arrow-down"></i> <strong>Resume:</strong>
						<a href="#"
							target="_blank" class="btn btn-outline-primary btn-sm small-btn">Download</a>
	
						<span class="text-danger">No Resume</span>

					</p>
					<form method="post" action="#"
						class="d-flex gap-2 mt-3">
						<input type="hidden" name="appId" value="" /> <input
							type="hidden" name="jobId" value="" />
						<button type="submit" name="status" value="shortlisted"
							class="btn btn-success btn-sm small-btn">Shortlist</button>
						<button type="submit" name="status" value="rejected"
							class="btn btn-danger btn-sm small-btn">Reject</button>
					</form>
					<form action="" method="get">
						<input type="hidden" name="userId" value="">
						<button type="submit"
							class="btn btn-secondary btn-sm small-btn mt-2">
							<i class="bi bi-eye"></i> View Full Details
						</button>
					</form>
					<div class="match-badge">
						Match:
                        <strong>85%</strong>
					</div>
				</div>
			</div>
			<div class="col-12">
				<div class="alert alert-warning text-center">No applicants
					found for this status.</div>
			</div>
		</div>
	</main>

	<%@ include file="includes/footer.jsp"%>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
