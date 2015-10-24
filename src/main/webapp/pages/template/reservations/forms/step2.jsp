<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="post" action="<%=application.getContextPath()%>/${requestScope.entity}/${parameter}">	
	
	<div class="form-group">
		<label for="resourceType">Type de la ressource</label>
		<input type="text" class="form-control" id="resourceType"
		placeholder="${requestScope.resourceType.name}" 
		disabled">
	</div>
	
	<div class="form-group">
		<label for="reserved">Ressource</label>
		<select class="form-control" id="reserved" name="reserved">
			<c:forEach items="${resources}" var="resource">
				<option value="${resource.id}">${resource.name}</option>
			</c:forEach>
		</select>
	</div>
			
	<div class="form-group">
		<label for="reserver">Utilisateur</label>
		<select class="form-control" id="reserver" name="reserver" readonly>
			<option value="${requestScope.authenticatedUser.id}">${requestScope.authenticatedUser.firstName} ${requestScope.authenticatedUser.lastName}</option>
		</select>
	</div>
			
	<div class="form-group">
		<label for="begin">Début</label>
		<input type="date" class="form-control" id="begin" name="begin">
	</div>
			
	<div class="form-group">
		<label for="end">Fin</label>
		<input type="date" class="form-control" id="end" name="end">
	</div>

	<div class="text-center">
		<c:choose>
			<c:when test="${not empty reservation}">
				<button type="submit" class="btn btn-success inline-button">Modifier</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-success">Valider</button>
			</c:otherwise>
		</c:choose>
	</div>
</form>