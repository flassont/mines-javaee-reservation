<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- If a resource type entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty resourceType}">
		<c:set var="parameter" value="/edit?id=${resourceType.id}" />
		<c:set var="panelTitle" value="${resourceType.name}" />
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
			<c:if test="${not empty error}">
				<div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					${error}
				</div>
			</c:if>
			<form method="POST"
				action="<%=application.getContextPath()%>/${requestScope.entity}${parameter}">

				<div class="form-group">
					<label for="name">Libellé</label> <input type="text"
						class="form-control" id="name" value="${resourceType.name}"
						name="name" placeholder="Libellé">
				</div>

				<div class="text-center">
					<c:choose>
						<c:when test="${not empty resourceType}">
							<button type="submit" class="btn btn-success inline-button">Modifier</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-success inline-button">Valider</button>
						</c:otherwise>
					</c:choose>
				</div>
			</form>
		</div>
	</div>
</div>
