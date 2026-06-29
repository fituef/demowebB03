<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<h2 class="mb-3">${isEdit ? 'Edit Student' : 'Add Student'}</h2>
<div class="page-card table-card"><div class="card-body">
    <form method="post" action="${pageContext.request.contextPath}${isEdit ? '/students/update' : '/students/save'}">
        <c:if test="${isEdit}"><input type="hidden" name="studentID" value="${student.studentID}"></c:if>
        <div class="row g-3">
            <div class="col-md-3"><label class="form-label">Student ID</label><input class="form-control" name="studentCode" value="${student.studentCode}" required></div>
            <div class="col-md-5"><label class="form-label">Full Name</label><input class="form-control" name="fullName" value="${student.fullName}" required></div>
            <div class="col-md-4"><label class="form-label">Major</label><input class="form-control" name="major" value="${student.major}" required></div>
            <div class="col-md-6"><label class="form-label">Email</label><input class="form-control" type="email" name="email" value="${student.email}" required></div>
            <div class="col-md-6"><label class="form-label">Phone Number</label><input class="form-control" name="phoneNumber" value="${student.phoneNumber}"></div>
        </div>
        <div class="mt-3"><button class="btn btn-brand" type="submit">Save</button> <a class="btn btn-soft" href="${pageContext.request.contextPath}/students">Back</a></div>
    </form>
</div></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
