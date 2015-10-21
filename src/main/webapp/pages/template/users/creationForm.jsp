<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- If a user entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty user}">
		<c:set var="parameter" value="?id=${user.id}" />
		<c:set var="panelTitle" value="${user.firstName} ${user.lastName}" />
	</c:when>
	<c:otherwise>
		<c:set var="parameter" value="" />
		<c:set var="panelTitle" value="Nouvel utilisateur" />
	</c:otherwise>
</c:choose>

<div class="panel panel-primary">
	<div class="panel-heading">${panelTitle}</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form method="POST"
				action="<%=application.getContextPath()%>/${requestScope.entity}${parameter}"
				class="form-horizontal">
				<%@ include file="userForm.jsp"%>
			</form>
		</div>
	</div>
</div>