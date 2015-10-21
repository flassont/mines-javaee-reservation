<form method="get" action="<%=application.getContextPath()%>/${requestScope.entity}">

	<div class="form-group">
		<label for="name">Libellé</label>
		<input type="text" class="form-control" value="${name}" id="name" name="name" placeholder="Libellé">
	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>