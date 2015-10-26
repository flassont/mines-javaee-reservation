<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="in" uri="/WEB-INF/tags/input.tld"%>
<%@ taglib prefix="mod" uri="/WEB-INF/tags/modelSubmit.tld"%>

<!-- If a user entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty model}">
		<c:set var="parameter" value="/edit?id=${model.id}" />
		<c:set var="panelTitle" value="${model.firstName} ${model.lastName}" />
	</c:when>
	<c:otherwise>
		<c:set var="parameter" value="/new" />
		<c:set var="panelTitle" value="Nouvel utilisateur" />
	</c:otherwise>
</c:choose>

<div class="panel panel-primary">
	<div class="panel-heading">${panelTitle}</div>
	<div class="panel-body">
		<div class="container-fluid">
			<form method="POST"
				action="${param.contextPath}/app/${requestScope.entity}${parameter}"
				class="form-horizontal">
				
				<!-- Value will be filled if we past the user variable in case of editing -->
				<!-- <div class="form-group"> -->
				<!-- 	<label for="login">Nom d'utilisateur</label> <input type="text" -->
				<%-- 		class="form-control" value="${model.login}" id="login" name="login" --%>
				<!-- 		placeholder="Login" required> -->
				<!-- </div> -->
				<in:Input name="login" display="Nom d'utilisateur" placeholder="Login" value="${model.login}"/>
				<in:Input name="password" display="Mot de passe" placeholder="Mot de passe" value="${model.password}" type="password"/>
				<in:Input name="lastName" display="Nom" placeholder="Nom" value="${model.lastName}"/>
				<in:Input name="firstName" display="Prénom" placeholder="Prénom" value="${model.firstName}"/>
				<in:Input name="mail" display="Email" placeholder="Email" value="${model.mail}" type="email"/>
				<in:Input name="phone" display="Téléphone" placeholder="Téléphone" value="${model.phone}"/>
				
				<mod:ModelSubmit model="${model}"/>
				
			</form>
		</div>
	</div>
</div>