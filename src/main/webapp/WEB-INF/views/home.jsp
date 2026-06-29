<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section class="page-hero">
    <div class="row g-4 align-items-center">
        <div class="col-lg-6">
            <div class="hero-card h-100">
                <span class="badge-soft mb-3">UEF graduation project platform</span>
                <h1 class="title">Manage <span class="gradient-text">graduation projects</span> with a modern dashboard experience.</h1>
                <p class="text-muted-app fs-5 mb-4">
                    A modern dark-mode interface for UEF students to manage topics, lecturers, supervising assignments,
                    and topic registrations in one place.
                </p>
                <div class="d-flex flex-wrap gap-3 mb-4">
                    <a class="btn btn-brand btn-lg px-4" href="${pageContext.request.contextPath}/topics">Explore Topics</a>
                    <a class="btn btn-soft btn-lg px-4" href="${pageContext.request.contextPath}/student-registrations/available">Register Topic</a>
                </div>
                <div class="row g-3">
                    <div class="col-sm-4"><div class="stat-card"><div class="metric-number">20+</div><div class="metric-label">Sample Topics</div></div></div>
                    <div class="col-sm-4"><div class="stat-card"><div class="metric-number">20+</div><div class="metric-label">Lecturers</div></div></div>
                    <div class="col-sm-4"><div class="stat-card"><div class="metric-number">20+</div><div class="metric-label">Students</div></div></div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="hero-card dashboard-preview">
                <div class="d-flex justify-content-between align-items-start mb-4">
                    <div>
                        <div class="text-muted-app small">Overview</div>
                        <h3 class="mb-0">System Dashboard</h3>
                    </div>
                    <span class="badge bg-success-subtle text-success border border-success-subtle">Live Modules</span>
                </div>
                <div class="row g-3 mb-3">
                    <div class="col-sm-6"><div class="mini-panel"><div class="text-muted-app small">Open Topics</div><div class="fs-3 fw-bold">12</div></div></div>
                    <div class="col-sm-6"><div class="mini-panel"><div class="text-muted-app small">Approved Registrations</div><div class="fs-3 fw-bold">8</div></div></div>
                    <div class="col-12"><div class="mini-panel"><div class="d-flex justify-content-between"><span>Registration Capacity</span><span>80%</span></div><div class="progress mt-3"><div class="progress-bar progress-bar-brand" style="width:80%"></div></div></div></div>
                </div>
                <div class="row g-3">
                    <div class="col-md-6"><a class="quick-link" href="${pageContext.request.contextPath}/lecturers"><span>Lecturer Management</span><span>→</span></a></div>
                    <div class="col-md-6"><a class="quick-link" href="${pageContext.request.contextPath}/students"><span>Student Management</span><span>→</span></a></div>
                    <div class="col-md-6"><a class="quick-link" href="${pageContext.request.contextPath}/assignments"><span>Supervising Assignment</span><span>→</span></a></div>
                    <div class="col-md-6"><a class="quick-link" href="${pageContext.request.contextPath}/registrations"><span>Registration Admin</span><span>→</span></a></div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="pb-4">
    <div class="section-header mb-3">
        <div>
            <h2 class="section-title">Core modules</h2>
            <p class="section-subtitle">All required exam features are organized into clean, easy-to-use screens.</p>
        </div>
    </div>
    <div class="row g-4">
        <div class="col-md-6 col-xl-3"><div class="feature-card"><h5>Topic Management</h5><p class="text-muted-app mb-0">Add, edit, search, and track topic capacity with clear status badges.</p></div></div>
        <div class="col-md-6 col-xl-3"><div class="feature-card"><h5>Lecturer & Student</h5><p class="text-muted-app mb-0">Maintain lecturer and student records with validated forms and quick edit flows.</p></div></div>
        <div class="col-md-6 col-xl-3"><div class="feature-card"><h5>Assignments</h5><p class="text-muted-app mb-0">Assign a supervising lecturer to each topic and update assignments when needed.</p></div></div>
        <div class="col-md-6 col-xl-3"><div class="feature-card"><h5>Registrations</h5><p class="text-muted-app mb-0">Students can register one topic, while admins review and update statuses.</p></div></div>
    </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
