<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- If a user entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty model}">
		<c:set var="parameter" value="/edit?id=${model.id}" />
		<c:set var="panelTitle" value="${model.firstName} ${model.lastName}" />
	</c:when>
	<c:otherwise>
		<c:set var="parameter" value="/new" />
		<c:set var="panelTitle" value="Nouvel utilisateur" />
	</c:otherwise>
</c:choose>

<div class="panel panel-primary">
	<div class="panel-heading">${panelTitle}</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form method="POST"
				action="${param.contextPath}/app/${requestScope.entity}${parameter}"
				class="form-horizontal">
				<%@ include file="userForm.jsp"%>
			</form>
		</div>
	</div>
</div>