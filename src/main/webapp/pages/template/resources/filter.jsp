<form method="get" action="${param.contextPath}/app/${requestScope.entity}/search">
<%@ taglib prefix="in" uri="/../../tags/input.tld"%>

	<in:Input required="false" name="name" display="Libellé" placeholder="Libellé" value="${name}"/>	
	<in:Input required="false" name="responsible" display="Responsable" placeholder="Responsable" value="${responsible}"/>	
	<in:Input required="false" name="resourceType" display="Type de ressource" placeholder="Type de ressource" value="${resourceType}"/>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>