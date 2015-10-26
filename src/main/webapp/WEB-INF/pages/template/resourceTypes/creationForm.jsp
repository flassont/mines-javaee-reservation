<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="in" uri="/WEB-INF/tags/input.tld"%>
<%@ taglib prefix="mod" uri="/WEB-INF/tags/modelSubmit.tld"%>
<!-- If a resource type entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty model}">
		<c:set var="parameter" value="/edit?id=${model.id}" />
		<c:set var="panelTitle" value="${model.name}" />
	</c:when>
	<c:otherwise>
		<c:set var="parameter" value="/new" />
		<c:set var="panelTitle" value="Nouveau type de ressource" />
	</c:otherwise>
</c:choose>

<div class="panel panel-primary">
	<div class="panel-heading">${panelTitle}</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form method="POST"
				action="${param.contextPath}/app/${requestScope.entity}${parameter}">
				<in:Input name="name" display="Libellé" placeholder="Libellé" value="${model.name}"/>
				<mod:ModelSubmit model="${model}"/>
			</form>
		</div>
	</div>
</div>
