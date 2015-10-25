<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="get" action="<%=application.getContextPath()%>/app/${requestScope.entity}">
	
	<div class="form-group">
		<label for="reserved">Ressource</label>
		<input type="text" class="form-control" id="reserved" name="reserved" placeholder="Ressource">
	</div>
	
	<div class="form-group">
		<label for="reserver">Utilisateur</label>
		<input type="text" class="form-control" id="reserver" name="reserver" placeholder="Utilisateur">
	</div>
	
	<div class="form-group">
		<label for="begin">Début</label>
		<input type="date" class="form-control" id="begin" name="begin">
	</div>
	
	<div class="form-group">
		<label for="end">Fin</label>
		<input type="date" class="form-control" id="end" name="end">
	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>