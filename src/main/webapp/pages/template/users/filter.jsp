<form method="get" action="<%=application.getContextPath()%>/${requestScope.entity}">

	<div class="form-group">
		<label for="lastName">Nom</label>
		<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Nom">
	</div>
	
	<div class="form-group">
		<label for="firstName">Prénom</label>
		<input type="text" class="form-control" id="firstName" name="firstName" placeholder="Nom">
	</div>
	
	 <div class="checkbox">
    	<label for="administrator">
      		<input type="checkbox" id="administrator" name="administrator">Administrateur
    	</label>
  	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>