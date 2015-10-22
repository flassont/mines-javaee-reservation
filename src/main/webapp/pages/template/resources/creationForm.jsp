<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- If a resource entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty resource}">
		<c:set var="parameter" value="/edit?id=${resource.id}" />
		<c:set var="panelTitle" value="${resource.name}" />
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
				action="<%=application.getContextPath()%>/${requestScope.entity}${parameter}">

				<div class="form-group">
					<label for="responsible">Responsable</label> <select
						class="form-control" id="responsible" name="responsible">
						<c:forEach items="${requestScope.responsibles}" var="responsible">
							<option value="${responsible.id}">${responsible.lastName}
								${responsible.firstName}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label for="type">Type de ressource</label> <select
						class="form-control" id="type" name="type">
						<c:forEach items="${requestScope.types}" var="type">
							<option value="${type.id}">${type.name}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label for=name>Libellé</label> <input type="text"
						class="form-control" value="${resource.name}" id="name"
						name="name" placeholder="Libellé">
				</div>

				<div class="form-group">
					<label for=description>Description</label>
					<textarea rows="3" class="form-control" id="description"
						name="description" placeholder="Description">${resource.description}</textarea>
				</div>

				<div class="form-group">
					<label for=location>Localisation</label> <input type="text"
						class="form-control" id="location" name="location"
						value="${resource.location}" placeholder="Localisation">
				</div>

				<div class="text-center">
					<c:choose>
						<c:when test="${not empty resource}">
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