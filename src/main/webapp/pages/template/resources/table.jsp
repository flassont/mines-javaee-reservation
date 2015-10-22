<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped">

	<tr>
		<th>#</th>
		<th>Libellé</th>
		<th>Propriétaire</th>
		<th>Localisation</th>
		<th class="text-center">Action</th>
	</tr>

	<c:forEach items="${requestScope.resources}" var="resource">
		<tr>
			<td>${resource.id}</td>
			<td>${resource.name}</td>
			<td>${resource.responsible.firstName}
				${resource.responsible.lastName}</td>
			<td>${resource.location}</td>
			<td class="text-center">
				<div class="btn-group btn-group-sm">
					<a
						href="<%= application.getContextPath()%>/${requestScope.entity}/edit?id=${resource.id}"
						class="btn btn-default"> <span
						class="glyphicon glyphicon glyphicon-edit"></span>
					</a> <a
						href="<%= application.getContextPath()%>/${requestScope.entity}/delete?id=${resource.id}"
						class="btn btn-danger"> <span
						class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
			</td>
		</tr>
	</c:forEach>

</table>