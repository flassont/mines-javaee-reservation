<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="<%= application.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/datepicker.css"/>
    <link type="text/css" rel="stylesheet" href="${contextPath}/css/style.css"/>
<title>${requestScope.title}</title>
</head>
<body>
	<jsp:include page="${requestScope.page}">
		<jsp:param name="contextPath" value="${contextPath}"/>
	</jsp:include>

    <script type="text/javascript" src="${contextPath}/js/jquery-1.11.13.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${contextPath}/js/bootstrap-datepicker.js"></script>
</body>
</html>