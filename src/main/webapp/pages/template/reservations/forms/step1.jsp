<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="get" action="${param.contextPath}/app/${requestScope.entity}/new">
	
	<div class="form-group">
		<label for="resourceType">Type de la ressource</label>
		<select class="form-control" id="resourceType" name="resourceType" required>
			<c:forEach items="${requestScope.resourceTypes}" var="type">
				<option value="${type.id}">${type.name}</option>
			</c:forEach>
		</select>
	</div>
			
	<div class="text-center">
		<button type="submit" class="btn btn-success inline-button">Suivant</button>
	</div>

</form>