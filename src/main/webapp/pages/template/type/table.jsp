<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped">

	<tr>
		<th class="col-md-1">#</th>
		<th>Libellé</th>
		<th class="col-md-1 text-center">Action</th>
	</tr>

	<c:forEach items="${requestScope.types}" var="type">
		<tr>
			<td>${type.id}</td>
			<td>${type.name}</td>
			<td class="text-center">
				<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</td>
		</tr>
	</c:forEach>
	
</table>