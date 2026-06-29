<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>UEF Graduation Project Management</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/app.css" rel="stylesheet">
</head>
<body class="app-body">
<nav class="navbar navbar-expand-lg glass-nav sticky-top">
    <div class="container main-container">
        <a class="navbar-brand d-flex align-items-center text-white" href="${pageContext.request.contextPath}/">
            <span class="brand-mark">UEF</span>
            UEF Graduation Project
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto align-items-lg-center gap-lg-2">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/topics">Topics</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/lecturers">Lecturers</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/students">Students</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/assignments">Assignments</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student-registrations/available">Student Register</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/student-registrations/my">My Topics</a></li>
                <li class="nav-item"><a class="btn btn-sm btn-brand ms-lg-2 px-3" href="${pageContext.request.contextPath}/registrations">Admin Panel</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="page-shell">
    <div class="container main-container px-3 px-lg-4">
        <c:if test="${not empty message}">
            <div class="alert alert-success alert-dismissible fade show mt-4" role="alert">
                ${message}
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert"></button>
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show mt-4" role="alert">
                ${error}
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="alert"></button>
            </div>
        </c:if>
