<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped">

	<tr>
		<th class="col-md-1">#</th>
		<th>Libellé</th>
		<th>Propriétaire</th>
		<th>Localisation</th>
		<th class="col-md-1 text-center">Action</th>
	</tr>

	<c:forEach items="${requestScope.resources}" var="resource">
		<tr>
			<td>${resource.id}</td>
			<td>${resource.name}</td>
			<td>${resource.responsible}</td>
			<td>${resource.location}</td>
			<td class="text-center">
				<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</td>
		</tr>
	</c:forEach>
	
</table>