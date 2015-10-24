<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">Liste des utilisateurs</div>

  <table class="table table-striped">

	<tr>
		<th>#</th>
		<th>Login</th>
		<th>Nom</th>
		<th>Pr�nom</th>
		<th class="text-center">Action</th>
	</tr>

	<c:forEach items="${requestScope.models}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.login}
			<td>${user.lastName}</td>
			<td>${user.firstName}</td>
			<td class="text-center">
				<div class="btn-group btn-group-sm" >
					<a href="<%= application.getContextPath()%>/test/${requestScope.entity}/edit?id=${user.id}" class="btn btn-default">
						<span class="glyphicon glyphicon glyphicon-edit"></span>
					</a>
					<a href="<%= application.getContextPath()%>/test/${requestScope.entity}/delete?id=${user.id}" class="btn btn-danger">
						<span class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
			</td>
		</tr>
	</c:forEach>

</table>
</div>