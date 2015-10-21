<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="POST" action="<%=application.getContextPath()%>/${requestScope.entity}">
	
	<div class="form-group">
		<label for="responsible">Responsable</label>
		<select class="form-control" id="responsible" name="responsible">
		<c:forEach items="${requestScope.responsibles}" var="responsible">
			<option value="${responsible.id}">${responsible.lastName} ${responsible.firstName}</option>
		</c:forEach>
		</select>
	</div>
	
	<div class="form-group">
		<label for="type">Type de ressource</label>
		<select class="form-control" id="type" name="type">
		<c:forEach items="${requestScope.types}" var="type">
			<option value="${type.id}">${type.name}</option>
		</c:forEach>
		</select>
	</div>
	
	<div class="form-group">
		<label for=name>Libellé</label>
		<input type="text" class="form-control" id="name" name="name" placeholder="Libellé">
	</div>
	
	<div class="form-group">
		<label for=description>Description</label>
		<textarea rows="3" class="form-control" id="description" name="description" placeholder="Description"></textarea>
	</div>
	
	<div class="form-group">
		<label for=location>Localisation</label>
		<input type="text" class="form-control" id="location" name="location" placeholder="Localisation">
	</div>
	
	<div class="text-center">
		<button type="submit" class="btn btn-success">Valider</button>
	</div>
	
</form>