<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h2 class="mb-3">${isEdit ? 'Edit Topic' : 'Add Topic'}</h2>
<div class="page-card table-card">
    <div class="card-body">
        <form method="post" action="${pageContext.request.contextPath}${isEdit ? '/topics/update' : '/topics/save'}">
            <c:if test="${isEdit}"><input type="hidden" name="topicID" value="${topic.topicID}"></c:if>
            <div class="row g-3">
                <div class="col-md-4">
                    <label class="form-label">Topic Code</label>
                    <input class="form-control" name="topicCode" value="${topic.topicCode}" required>
                </div>
                <div class="col-md-8">
                    <label class="form-label">Topic Name</label>
                    <input class="form-control" name="topicName" value="${topic.topicName}" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Research Field</label>
                    <input class="form-control" name="researchField" value="${topic.researchField}" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Start Year</label>
                    <input class="form-control" type="number" name="startYear" value="${topic.startYear}" min="1900" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">End Year</label>
                    <input class="form-control" type="number" name="endYear" value="${topic.endYear}" min="1900" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Max Students</label>
                    <input class="form-control" type="number" name="maxStudents" value="${topic.maxStudents}" min="1" required>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Status</label>
                    <select class="form-select" name="status">
                        <option value="Open" ${topic.status == 'Open' ? 'selected' : ''}>Open</option>
                        <option value="Closed" ${topic.status == 'Closed' ? 'selected' : ''}>Closed</option>
                    </select>
                </div>
                <div class="col-12">
                    <label class="form-label">Description</label>
                    <textarea class="form-control" name="description" rows="4">${topic.description}</textarea>
                </div>
            </div>
            <div class="mt-3">
                <button class="btn btn-brand" type="submit">Save</button>
                <a class="btn btn-soft" href="${pageContext.request.contextPath}/topics">Back</a>
            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
