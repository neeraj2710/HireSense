<!-- navbar code starts here -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid p-2">
        <a href="./index.jsp" class="navbar-brand fw-bold"><%=application.getAttribute("appName")%></a>

        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#mynav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="mynav">
            <ul class="navbar-nav ms-auto">
            <%
                String role = (String) session.getAttribute("userRole");
                if(role == null){ // common navbar starts
            %>
                <!-- guest links -->
                <li class="nav-item">
                    <a href="./login.jsp" class="nav-link">Login</a>
                </li>
                <li class="nav-item">
                    <a href="./register.jsp" class="nav-link">Register</a>
                </li>
            <%
                } // common navbar ends
                else if (role.equals("user")) { // user navbar starts
            %>
                <!-- employer links -->
                <li class="nav-item">
                    <a href="./userDashboard.jsp" class="nav-link">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a href="" class="nav-link">Logout</a>
                </li>
            <%
                } //user navbar ends
                else if (role.equals("employer")) { // employer navbar starts
            %>
                <!-- employee links -->
                <li class="nav-item">
                    <a href="../employerDashboard.jsp" class="nav-link">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Logout</a>
                </li>
            <%
                } // employer navbar ends
                else if (role.equals("admin")) { // admin navbar starts
            %>
                <!-- admin links -->
                <li class="nav-item">
                    <a href="./adminPanel.jsp" class="nav-link">Admin Panel</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Logout</a>
                </li>
            <%
                } // admin navbar ends
            %>
            </ul>
        </div>
    </div>
</nav>
<!-- the navbar code ends here -->