<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2 class="mb-0">Topic Management</h2>
    <a class="btn btn-brand" href="${pageContext.request.contextPath}/topics/new">Add Topic</a>
</div>
<form class="row g-2 mb-3" method="get" action="${pageContext.request.contextPath}/topics">
    <div class="col-md-5">
        <input type="text" class="form-control" name="keyword" value="${keyword}" placeholder="Search by topic code or topic name">
    </div>
    <div class="col-auto">
        <button class="btn btn-soft" type="submit">Search</button>
        <a class="btn btn-soft" href="${pageContext.request.contextPath}/topics">Reset</a>
    </div>
</form>
<div class="page-card table-card">
    <div class="table-responsive">
        <table class="table table-hover align-middle mb-0">
            <thead class="table-primary">
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Research Field</th>
                <th>Years</th>
                <th>Students</th>
                <th>Status</th>
                <th>Description</th>
                <th style="width: 100px">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="t" items="${topics}">
                <tr>
                    <td>${t.topicCode}</td>
                    <td>${t.topicName}</td>
                    <td>${t.researchField}</td>
                    <td>${t.startYear} - ${t.endYear}</td>
                    <td>${t.registeredCount}/${t.maxStudents}</td>
                    <td><span class="badge ${t.status == 'Open' ? 'bg-success' : 'bg-secondary'}">${t.status}</span></td>
                    <td>${t.description}</td>
                    <td><a class="btn btn-sm btn-soft" href="${pageContext.request.contextPath}/topics/edit/${t.topicID}">Edit</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty topics}">
                <tr><td colspan="8" class="text-center text-muted py-4">No topics found.</td></tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
