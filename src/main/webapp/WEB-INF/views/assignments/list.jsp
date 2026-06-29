<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2 class="mb-0">Supervising Lecturer Assignment</h2>
    <a class="btn btn-brand" href="${pageContext.request.contextPath}/assignments/new">Assign Lecturer</a>
</div>
<div class="alert alert-info">Each topic has one supervising lecturer. If you assign a lecturer to a topic that already has an assignment, the system updates the existing assignment.</div>
<div class="page-card table-card">
    <div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary"><tr><th>Topic Code</th><th>Topic Name</th><th>Lecturer ID</th><th>Lecturer Name</th><th>Assignment Date</th><th>Action</th></tr></thead>
            <tbody>
            <c:forEach var="a" items="${assignments}">
                <tr>
                    <td>${a.topicCode}</td>
                    <td>${a.topicName}</td>
                    <td>${a.lecturerCode}</td>
                    <td>${a.lecturerName}</td>
                    <td>${a.assignmentDate}</td>
                    <td><a class="btn btn-sm btn-soft" href="${pageContext.request.contextPath}/assignments/edit/${a.assignmentID}">Edit</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty assignments}"><tr><td colspan="6" class="text-center text-muted py-4">No assignments found.</td></tr></c:if>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
