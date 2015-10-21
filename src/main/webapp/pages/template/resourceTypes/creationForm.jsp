<form method="POST" action="<%=application.getContextPath()%>/${requestScope.entity}">

	<div class="form-group">
		<label for="name">Libellé</label>
		<input type="text" class="form-control" id="name" name="name" placeholder="Libellé">
	</div>
	
	<div class="text-center">
		<button type="submit" class="btn btn-success">Valider</button>
	</div>
	
</form>