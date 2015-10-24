<form method="get" action="resources">

	<div class="form-group">
		<label for="name">Libellé</label>
		<input type="text" class="form-control" value="${name}" id="name" name="name" placeholder="Libellé">
	</div>
	
	<div class="form-group">
		<label for="responsible">Responsable</label>
		<input type="text" class="form-control" value="${responsible}" id="responsible" name="responsible" placeholder="Responsable">
	</div>
	
	<div class="form-group">
		<label for="location">Localisation</label>
		<input type="text" class="form-control" value="${location}" id="location" name="location" placeholder="Localisation">
	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>