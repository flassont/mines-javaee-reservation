<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-primary">
	<!-- Default panel contents -->
	<div class="panel-heading">Liste des ressources</div>
	<table class="table table-striped">

		<tr>
			<th>#</th>
			<th>Libell�</th>
			<th>Responsable</th>
			<th>Type</th>
			<th class="text-center">Action</th>
		</tr>

		<c:forEach items="${requestScope.models}" var="resource">
			<tr>
				<td>${resource.id}</td>
				<td>${resource.name}</td>
				<td>${resource.responsible.firstName}
					${resource.responsible.lastName}</td>
				<td>${resource.type.name}</td>
				<td class="text-center">
					<div class="btn-group btn-group-sm">
						<a
							href="<%= application.getContextPath()%>/app/${requestScope.entity}/edit?id=${resource.id}"
							class="btn btn-default"> <span
							class="glyphicon glyphicon glyphicon-edit"></span>
						</a> <a
							href="<%= application.getContextPath()%>/app/${requestScope.entity}/delete?id=${resource.id}"
							class="btn btn-danger"> <span
							class="glyphicon glyphicon-remove"></span>
						</a>
					</div>
				</td>
			</tr>
		</c:forEach>

	</table>
</div>