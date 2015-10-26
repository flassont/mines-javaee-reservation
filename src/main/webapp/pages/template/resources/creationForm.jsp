<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mod" uri="/../../tags/modelSubmit.tld"%>
<%@ taglib prefix="in" uri="/../../tags/input.tld"%>

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
						class="form-control" id="type" name="type" required>
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
				<in:Input name="name" display="Libellé" placeholder="Libellé" value="${model.name}"/>
				<in:Input name="description" display="Description" placeholder="Description" value="${model.description}"/>
				<in:Input name="location" display="Localisation" placeholder="Localisation" value="${model.location}"/>

				<mod:ModelSubmit model="${model}"/>
			</form>
		</div>
	</div>
</div>