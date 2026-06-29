<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h2 class="mb-3">${isEdit ? 'Edit Assignment' : 'Assign Lecturer to Topic'}</h2>
<div class="page-card table-card"><div class="card-body">
    <form method="post" action="${pageContext.request.contextPath}${isEdit ? '/assignments/update' : '/assignments/save'}">
        <c:if test="${isEdit}"><input type="hidden" name="assignmentID" value="${not empty assignmentID ? assignmentID : assignment.assignmentID}"></c:if>
        <div class="row g-3">
            <div class="col-md-5">
                <label class="form-label">Topic</label>
                <select class="form-select" name="topicID" required>
                    <option value="">-- Select topic --</option>
                    <c:forEach var="t" items="${topics}">
                        <option value="${t.topicID}" ${topicID == t.topicID ? 'selected' : ''}>${t.topicCode} - ${t.topicName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-5">
                <label class="form-label">Lecturer</label>
                <select class="form-select" name="lecturerID" required>
                    <option value="">-- Select lecturer --</option>
                    <c:forEach var="l" items="${lecturers}">
                        <option value="${l.lecturerID}" ${lecturerID == l.lecturerID ? 'selected' : ''}>${l.lecturerCode} - ${l.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-2">
                <label class="form-label">Assignment Date</label>
                <input class="form-control" type="date" name="assignmentDate" value="${assignmentDate}" required>
            </div>
        </div>
        <div class="mt-3"><button class="btn btn-brand" type="submit">Save</button> <a class="btn btn-soft" href="${pageContext.request.contextPath}/assignments">Back</a></div>
    </form>
</div></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
