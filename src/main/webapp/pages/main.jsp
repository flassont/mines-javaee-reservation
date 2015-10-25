<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="<%= application.getContextPath()%>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%= application.getContextPath()%>/css/datepicker.css"/>
    <link rel="stylesheet" href="<%= application.getContextPath()%>/css/style.css"/>
<title>${requestScope.title}</title>
</head>
<body>
	<jsp:include page="${requestScope.page}" />

    <script type="text/javascript" src="<%= application.getContextPath()%>/js/jquery-1.11.13.min.js"></script>
    <script type="text/javascript" src="<%= application.getContextPath()%>/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%= application.getContextPath()%>/js/bootstrap-datepicker.js"></script>
</body>
</html>