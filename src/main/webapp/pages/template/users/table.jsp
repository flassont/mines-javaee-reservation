<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped">

	<tr>
		<th class="col-md-1">#</th>
		<th>Login</th>
		<th>Nom</th>
		<th>Prénom</th>
		<th class="col-md-1 text-center">Action</th>
	</tr>

	<c:forEach items="${requestScope.users}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.login}
			<td>${user.lastName}</td>
			<td>${user.firstName}</td>
			<td class="text-center">
				<a href="<%= application.getContextPath()%>/${requestScope.entity}/edit?id=${user.id}">
					<span class="glyphicon glyphicon glyphicon-edit"></span>
				</a>
				<a href="<%= application.getContextPath()%>/${requestScope.entity}/delete?id=${user.id}">
					<span class="glyphicon glyphicon-remove"></span>
				</a>
			</td>
		</tr>
	</c:forEach>

</table>