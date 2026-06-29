<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2 class="mb-0">Lecturer Management</h2>
    <a class="btn btn-brand" href="${pageContext.request.contextPath}/lecturers/new">Add Lecturer</a>
</div>
<div class="page-card table-card">
    <div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary"><tr><th>ID</th><th>Full Name</th><th>Faculty</th><th>Email</th><th>Phone</th><th>Action</th></tr></thead>
            <tbody>
            <c:forEach var="l" items="${lecturers}">
                <tr>
                    <td>${l.lecturerCode}</td>
                    <td>${l.fullName}</td>
                    <td>${l.faculty}</td>
                    <td>${l.email}</td>
                    <td>${l.phoneNumber}</td>
                    <td><a class="btn btn-sm btn-soft" href="${pageContext.request.contextPath}/lecturers/edit/${l.lecturerID}">Edit</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty lecturers}"><tr><td colspan="6" class="text-center text-muted py-4">No lecturers found.</td></tr></c:if>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
