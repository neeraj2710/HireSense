<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<%@include file="includes/header.jsp"%>

<main class="container py-5 flex-grow-1" >
    <h2 class="mb-4">Welcome Neeraj</h2>

    <!-- filter code starts -->
    <form action="#">
        <div class="row g-3 mb-4">
            <div class="col-md-3">
                <input
                        type="text"
                        name="search"
                        class="form-control"
                        placeholder="Search by title or company"
                />
            </div>

            <div class="col-md-2">
                <input
                        type="text"
                        name="location"
                        class="form-control"
                        placeholder="Location"
                />
            </div>

            <div class="col-md-2">
                <select name="experience" class="form-select">
                    <option value="" selected disabled>Experience</option>
                    <option value="fresher">Fresher</option>
                    <option value="0-1">0-1 years</option>
                    <option value="1-3">1-3 years</option>
                    <option value="3-5">3-5 years</option>
                    <option value="5+">5+ years</option>
                </select>
            </div>

            <div class="col-md-2">
                <select name="packageLpa" class="form-select">
                    <option value="" selected disabled>Package (LPA)</option>
                    <option value="1-3">1-3 LPA</option>
                    <option value="3-6">3-6 LPA</option>
                    <option value="6-10">6-10 LPA</option>
                    <option value="10+">10+ LPA</option>
                    <option value="not disclosed">Not disclosed</option>
                </select>
            </div>

            <div class="col-md-2">
                <select name="sort" class="form-select">
                    <option value="" selected disabled>Sort</option>
                    <option value="fewest">Fewest</option>
                    <option value="most">Most</option>
                </select>
            </div>

            <div class="col-md-1">
                <button type="submit" class="btn btn-primary">Go</button>
            </div>
        </div>
    </form>
    <!-- filter code ends -->

    <!-- job card starts -->
    <div class="col-md-4">
        <div class="card p-3 position-relative">
          <span class="position-absolute top-0 end-0 px-2 py-1 small"
          >6 Sep</span
          >

            <div class="card-body">
                <h5 class="mb-1">Software Developer</h5>
                <p class="text-muted">A2Infotech Pvt Ltd</p>

                <div class="d-flex flex-wrap text-muted small mb-2 gap-3">
                    <div><i class="bi bi-briefcase-fill me-1"></i>3-5 years</div>
                    <div><i class="bi bi-currency-rupee me-1"></i>6-10 LPA</div>
                    <div><i class="bi bi-geo-alt me-1"></i>Noida, India</div>
                    <div><i class="bi bi-people-fill me-1"></i>5 Openings</div>
                </div>

                <span class="badge bg-success col-md-2 p-2 mt-2">Applied</span>
                <br />
                <button
                        type="button"
                        class="btn btn-outline-primary btn-sm mt-2 small"
                        data-bs-toggle="modal"
                        data-bs-target="#uploadresume"
                >
                    Apply Now
                </button>
                <button
                        type="button"
                        class="btn btn-outline-secondary btn-sm mt-2 small"
                        data-bs-toggle="modal"
                        data-bs-target="#jobDetails"
                >
                    View Details
                </button>
            </div>

            <div
                    class="position-absolute badge bg-primary bottom-0 end-0 p-2 small m-2"
            >
                30% match
            </div>
        </div>
    </div>
    <!-- job card ends -->

    <!-- resume upload popup starts -->
    <div class="modal fade" id="uploadresume">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <form
                    action="#"
                    method="post"
                    class="modal-content bg-dark text-white"
            >
                <div class="modal-header">
                    <h5>Upload Resume</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    <input type="hidden" name="jobId" id="modalJobId" />
                    <input type="hidden" name="skills" id="modalSkills" />
                    <label for="resumeFile" class="form-label">Upload Resume</label>
                    <input
                            type="file"
                            name="resume"
                            id="resumeFile"
                            class="form-control"
                            accept=".pdf"
                            required
                    />
                </div>

                <div class="modal-footer justify-content-between">
                    <button type="submit" class="btn btn-success">
                        Continue
                    </button>
                    <button
                            type="submit"
                            class="btn btn-secondary"
                            data-bs-dismiss="modal"
                    >
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    </div>
    <!-- resume upload popup ends -->

    <!-- view job details popup starts -->
    <div class="modal fade" id="jobDetails">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <form
                    action="#"
                    method="post"
                    class="modal-content bg-white"
            >
                <div class="modal-header">
                    <h5 class="fw-bold" id="modalJobTitle">Software Developer</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    <p><strong>Company: </strong><span id="modalCompany">A2Infotech Pvt Ltd</span></p>
                    <p><strong>Location: </strong><span id="modalLocation">Delhi</span></p>
                    <p><strong>Experience: </strong><span id="modalExperience">2-3 Years</span></p>
                    <p><strong>Package: </strong><span id="modalPackage">4 LPA</span></p>
                    <p><strong>Vacancies: </strong><span id="modalVacancies">10</span></p>
                    <p><strong>Skills: </strong><span id="modalSkills">Java, Python, Go, SQL, Docker, K8s</span></p>
                    <p><strong>Job Description: </strong><span id="modalDescription">Lorem ipsum dolor sit amet consectetur adipisicing elit. Officiis, mollitia?</span></p>
                    <p><strong>Date posted: </strong><span id="modalDatePosted">6 Sept 2025</span></p>
                </div>
            </form>
        </div>
    </div>
    <!-- view job details popup ends -->
</main>

<%@include file="includes/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>
