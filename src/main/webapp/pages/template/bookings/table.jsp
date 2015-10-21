<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped">
	<tr>
		<th class="col-md-1">#</th>
		<th>Utilisateur</th>
		<th>Ressource</th>
		<th>Début</th>
		<th>Fin</th>
		<th class="col-md-1 text-center">Action</th>
	</tr>

	<c:forEach items="${requestScope.reservations}" var="reservation">
		<tr>
			<td>${reservation.id}</td>
			<td>${reservation.reserver.login}</td>
			<td>${reservation.reserved.name}</td>
			<td>${reservation.end}</td>
			<td class="text-center">
				<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</td>
		</tr>
	</c:forEach>
</table>