<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="in" uri="/WEB-INF/tags/input.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:formatDate value="${begin}" pattern="dd/MM/yyyy" var="formattedBegin"/>
<fmt:formatDate value="${end}" pattern="dd/MM/yyyy" var="formattedEnd"/>

<form method="get" action="${param.contextPath}/app/${requestScope.entity}/search">
	
	<in:Input required="false" name="reserved" display="Ressource" placeholder="Ressource" value="${reserved}"/>
	<c:if test="${authenticatedUser.isAdmin}">
		<in:Input required="false" name="reserver" display="Utilisateur" placeholder="Utilisateur" value="${reserver}"/>
	</c:if>
	<in:Input required="false" name="begin" display="Du" placeholder="Du" value="${formattedBegin}" additionalHtml="data-provide=\"datepicker\" data-date-format=\"dd/mm/yyyy\"" />
	<in:Input required="false" name="end" display="Au" placeholder="Au" value="${formattedEnd}" additionalHtml="data-provide=\"datepicker\" data-date-format=\"dd/mm/yyyy\"" />
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>