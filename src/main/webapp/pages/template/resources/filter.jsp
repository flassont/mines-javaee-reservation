<form method="get" action="${param.contextPath}/app/${requestScope.entity}">

	<div class="form-group">
		<label for="name">Libellé</label>
		<input type="text" class="form-control" value="${name}" id="name" name="name" placeholder="Libellé">
	</div>
	
	<div class="form-group">
		<label for="responsible">Responsable</label>
		<input type="text" class="form-control" value="${responsible}" id="responsible" name="responsible" placeholder="Responsable">
	</div>
	
	<div class="form-group">
		<label for="resourceType">Type</label>
		<input type="text" class="form-control" value="${location}" id="resourceType" name="resourceType" placeholder="Type">
	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>