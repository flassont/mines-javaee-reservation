<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="post" action="<%=application.getContextPath()%>/app/${requestScope.entity}/${parameter}">	
	
	<div class="form-group">
		<label for="resourceType">Type de la ressource</label>
		<input type="text" class="form-control" id="resourceType" name="resourceType" value="${model.reserved.type.name}" readonly>
	</div>
	
	<div class="form-group">
		<label for="resource">Ressource</label>
		<select class="form-control" id="resource" name="reserved" required>
			<option value="${requestScope.reservation.reserved.id}" selected>${model.reserved.name}</option>
			<c:forEach items="${resources}" var="resource">
				<option value="${resource.id}">${resource.name}</option>
			</c:forEach>
		</select>
	</div>
			
	<div class="form-group">
		<label for="reserver">Utilisateur</label>
		<select class="form-control" id="reserver" name="reserver" readonly>
			<option value="${requestScope.reservation.reserver.id}" selected>${model.reserver.firstName} ${requestScope.reservation.reserver.lastName}</option>
		</select>
	</div>
			
	<div class="form-group">
		<label for="begin">Début</label>
		<input type="date" class="form-control" id="begin" name="begin" value="${model.begin}" required>
	</div>
			
	<div class="form-group">
		<label for="end">Fin</label>
		<input type="date" class="form-control" id="end" name="end" value="${model.end}" required>
	</div>

	<div class="text-center">
		<c:choose>
			<c:when test="${not empty model}">
				<button type="submit" class="btn btn-success inline-button">Modifier</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-success">Valider</button>
			</c:otherwise>
		</c:choose>
	</div>
</form>