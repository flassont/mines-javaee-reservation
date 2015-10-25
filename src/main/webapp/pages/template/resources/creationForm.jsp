<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- If a resource entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty model}">
		<c:set var="parameter" value="/edit?id=${model.id}" />
		<c:set var="panelTitle" value="${model.name}" />
	</c:when>
	<c:otherwise>
		<c:set var="parameter" value="/new" />
		<c:set var="panelTitle" value="Nouvelle ressource" />
	</c:otherwise>
</c:choose>
<div class="panel panel-primary">
	<div class="panel-heading">${panelTitle}</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form method="POST"
				action="${param.contextPath}/app/${requestScope.entity}${parameter}">

				<div class="form-group">
					<label for="resourceType">Type de ressource</label> <select
						class="form-control" id="resourceType" name="resourceType" required>
						<c:forEach items="${requestScope.resourceTypes}" var="type">
							<option value="${type.id}" <c:if test="${type.id == model.type.id}">selected</c:if>>${type.name}</option>
						</c:forEach>
					</select>
				</div>
				

				<div class="form-group">
					<label for="responsible">Responsable</label> <select
						class="form-control" id="responsible" name="responsible" required>
						<c:forEach items="${requestScope.responsibles}" var="responsible">
							<option value="${responsible.id}" <c:if test="${responsible.id == model.responsible.id}">selected</c:if>>${responsible.lastName}
								${responsible.firstName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for=name>Libellé</label> <input type="text"
						class="form-control" value="${model.name}" id="name"
						name="name" placeholder="Libellé" required>
				</div>

				<div class="form-group">
					<label for=description>Description</label>
					<textarea rows="3" class="form-control" id="description"
						name="description" placeholder="Description" required>${model.description}</textarea>
				</div>

				<div class="form-group">
					<label for=location>Localisation</label> <input type="text"
						class="form-control" id="location" name="location"
						value="${model.location}" placeholder="Localisation" required>
				</div>

				<div class="text-center">
					<c:choose>
						<c:when test="${not empty model}">
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