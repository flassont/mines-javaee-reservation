<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-primary">
	<!-- Default panel contents -->
	<div class="panel-heading">Liste des types de ressource</div>

	<table class="table table-striped">

		<tr>
			<th>#</th>
			<th>Nom</th>
			<th class="text-center">Action</th>
		</tr>

		<c:forEach items="${requestScope.resourceTypes}" var="type">
			<tr>
				<td>${type.id}</td>
				<td>${type.name}</td>
				<td class="text-center">
					<div class="btn-group btn-group-sm">
						<a
							href="<%= application.getContextPath()%>/${requestScope.entity}/edit?id=${type.id}"
							class="btn btn-default"> <span
							class="glyphicon glyphicon glyphicon-edit"></span>
						</a> <a
							href="<%= application.getContextPath()%>/${requestScope.entity}/delete?id=${type.id}"
							class="btn btn-danger"> <span
							class="glyphicon glyphicon-remove"></span>
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>

	</table>
</div>