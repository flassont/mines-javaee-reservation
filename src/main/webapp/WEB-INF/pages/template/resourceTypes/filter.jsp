<form method="get" action="${param.contextPath}/app/${requestScope.entity}/search">
<%@ taglib prefix="in" uri="/WEB-INF/tags/input.tld"%>

	<in:Input required="false" name="name" display="Libell�" placeholder="Libell�" value="${name}"/>	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>