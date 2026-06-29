<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h2 class="mb-3">Registration Management</h2>
<form class="row g-2 mb-3" method="get" action="${pageContext.request.contextPath}/registrations">
    <div class="col-md-5"><input class="form-control" name="keyword" value="${keyword}" placeholder="Search by student or topic"></div>
    <div class="col-auto"><button class="btn btn-soft" type="submit">Search</button> <a class="btn btn-soft" href="${pageContext.request.contextPath}/registrations">Reset</a></div>
</form>
<div class="page-card table-card">
    <div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary"><tr><th>Student</th><th>Topic</th><th>Role</th><th>Date</th><th>Status</th><th style="width: 260px">Update Status</th></tr></thead>
            <tbody>
            <c:forEach var="r" items="${registrations}">
                <tr>
                    <td>${r.studentCode} - ${r.studentName}</td>
                    <td>${r.topicCode} - ${r.topicName}</td>
                    <td>${r.participationRole}</td>
                    <td>${r.registrationDate}</td>
                    <td><span class="badge ${r.registrationStatus == 'Approved' ? 'bg-success' : (r.registrationStatus == 'Rejected' ? 'bg-danger' : 'bg-warning text-dark')}">${r.registrationStatus}</span></td>
                    <td>
                        <form class="d-flex gap-2" method="post" action="${pageContext.request.contextPath}/registrations/update-status">
                            <input type="hidden" name="registrationID" value="${r.registrationID}">
                            <select class="form-select form-select-sm" name="registrationStatus">
                                <option value="Pending" ${r.registrationStatus == 'Pending' ? 'selected' : ''}>Pending</option>
                                <option value="Approved" ${r.registrationStatus == 'Approved' ? 'selected' : ''}>Approved</option>
                                <option value="Rejected" ${r.registrationStatus == 'Rejected' ? 'selected' : ''}>Rejected</option>
                            </select>
                            <button class="btn btn-sm btn-primary" type="submit">Save</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty registrations}"><tr><td colspan="6" class="text-center text-muted py-4">No registration records found.</td></tr></c:if>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
