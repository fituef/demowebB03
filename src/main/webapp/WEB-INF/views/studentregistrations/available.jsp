<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h2 class="mb-3">Available Topic Registration</h2>
<div class="row g-4">
    <div class="col-lg-4">
        <div class="page-card table-card">
            <div class="card-header border-0 pb-0">Register for one topic</div>
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/student-registrations/register">
                    <div class="mb-3">
                        <label class="form-label">Student</label>
                        <select class="form-select" name="studentID" required>
                            <option value="">-- Select student --</option>
                            <c:forEach var="s" items="${students}">
                                <option value="${s.studentID}" ${studentID == s.studentID ? 'selected' : ''}>${s.studentCode} - ${s.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Available Topic</label>
                        <select class="form-select" name="topicID" required>
                            <option value="">-- Select topic --</option>
                            <c:forEach var="t" items="${topics}">
                                <option value="${t.topicID}" ${topicID == t.topicID ? 'selected' : ''}>${t.topicCode} - ${t.topicName} (${t.registeredCount}/${t.maxStudents})</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Participation Role</label>
                        <select class="form-select" name="participationRole" required>
                            <option value="Team Leader" ${participationRole == 'Team Leader' ? 'selected' : ''}>Team Leader</option>
                            <option value="Team Member" ${participationRole == 'Team Member' ? 'selected' : ''}>Team Member</option>
                        </select>
                    </div>
                    <button class="btn btn-brand" type="submit">Register</button>
                </form>
            </div>
        </div>
    </div>
    <div class="col-lg-8">
        <div class="page-card table-card">
            <div class="card-header border-0 pb-0">Topics still available</div>
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-primary"><tr><th>Code</th><th>Name</th><th>Field</th><th>Capacity</th><th>Status</th></tr></thead>
                    <tbody>
                    <c:forEach var="t" items="${topics}">
                        <tr><td>${t.topicCode}</td><td>${t.topicName}</td><td>${t.researchField}</td><td>${t.registeredCount}/${t.maxStudents}</td><td>${t.status}</td></tr>
                    </c:forEach>
                    <c:if test="${empty topics}"><tr><td colspan="5" class="text-center text-muted py-4">No available topics.</td></tr></c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
