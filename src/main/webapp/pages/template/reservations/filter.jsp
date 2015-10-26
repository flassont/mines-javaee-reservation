<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="in" uri="/../../tags/input.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:formatDate value="${begin}" pattern="dd/MM/yyyy" var="formattedBegin"/>
<fmt:formatDate value="${end}" pattern="dd/MM/yyyy" var="formattedEnd"/>

<form method="get" action="${param.contextPath}/app/${requestScope.entity}/search">
	
	<in:Input required="false" name="reserved" display="Ressource" placeholder="Ressource" value="${reserved}"/>
	<in:Input required="false" name="reserver" display="Utilisateur" placeholder="Utilisateur" value="${reserver}"/>
	<in:Input required="false" name="begin" display="Début" placeholder="Début" value="${formattedBegin}" additionalHtml="data-provide=\"datepicker\" data-date-format=\"dd/mm/yyyy\""/>
	<in:Input required="false" name="end" display="Fin" placeholder="Fin" value="${formattedEnd}" additionalHtml="data-provide=\"datepicker\" data-date-format=\"dd/mm/yyyy\""/>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>