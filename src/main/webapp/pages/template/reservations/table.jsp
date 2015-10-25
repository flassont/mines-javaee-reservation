<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="panel panel-primary">
	<div class="panel-heading">Liste des réservations</div>
	
	<table class="table table-striped">
		<tr>
			<th class="col-md-1">#</th>
			<th>Utilisateur</th>
			<th>Ressource</th>
			<th>Début</th>
			<th>Fin</th>
			<th class="col-md-1 text-center">Action</th>
		</tr>
	
		<c:forEach items="${requestScope.models}" var="reservation">
			<tr>
				<td>${reservation.id}</td>
				<td>${reservation.reserver.firstName} ${reservation.reserver.lastName}</td>
				<td>${reservation.reserved.name}</td>
				<td class="col-md-2"><fmt:formatDate value="${reservation.begin}" pattern="dd/MM/yyyy" /></td>
				<td class="col-md-2"><fmt:formatDate value="${reservation.end}" pattern="dd/MM/yyyy"/></td>
				<td class="text-center col-md-2">
					<div class="btn-group btn-group-sm">
						<a
							href="<%= application.getContextPath()%>/app/${requestScope.entity}/edit?id=${reservation.id}"
							class="btn btn-default"> <span
							class="glyphicon glyphicon glyphicon-edit"></span>
						</a> <a
							href="<%= application.getContextPath()%>/app/${requestScope.entity}/delete?id=${reservation.id}"
							class="btn btn-danger"> <span
							class="glyphicon glyphicon-remove"></span>
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>