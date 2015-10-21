<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="get" action="reservation">
	
	<div class="form-group">
		<label for="reserver">Utilisateur</label>
		<select class="form-control" id="reserver">
		<c:forEach items="${requestScope.reservers}" var="reserver">
			<option>${reserver.lastName} ${reserver.firstName}</option>
		</c:forEach>
		</select>
	</div>
	
	<div class="form-group">
		<label for="begin">Début</label>
		<input type="date" id="begin" name="begin">
	</div>
	
	<div class="form-group">
		<label for="end">Fin</label>
		<input type="date" id="end" name="end">
	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>