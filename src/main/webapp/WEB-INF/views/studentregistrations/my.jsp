<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h2 class="mb-3">My Registered Topics</h2>
<form class="row g-2 mb-3" method="get" action="${pageContext.request.contextPath}/student-registrations/my">
    <div class="col-md-5">
        <select class="form-select" name="studentID" required>
            <option value="0">-- Select student --</option>
            <c:forEach var="s" items="${students}">
                <option value="${s.studentID}" ${studentID == s.studentID ? 'selected' : ''}>${s.studentCode} - ${s.fullName}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-auto"><button class="btn btn-brand" type="submit">View</button></div>
</form>
<div class="page-card table-card">
    <div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary"><tr><th>Topic Code</th><th>Topic Name</th><th>Role</th><th>Status</th><th>Registration Date</th></tr></thead>
            <tbody>
            <c:forEach var="r" items="${registrations}">
                <tr>
                    <td>${r.topicCode}</td>
                    <td>${r.topicName}</td>
                    <td>${r.participationRole}</td>
                    <td><span class="badge ${r.registrationStatus == 'Approved' ? 'bg-success' : (r.registrationStatus == 'Rejected' ? 'bg-danger' : 'bg-warning text-dark')}">${r.registrationStatus}</span></td>
                    <td>${r.registrationDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty registrations}"><tr><td colspan="5" class="text-center text-white py-4">No registrations found.</td></tr></c:if>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
