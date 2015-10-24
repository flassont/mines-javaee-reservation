<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty requestScope.reservation}">
		<c:set var="parameter" value="/edit?id=${requestScope.reservation.id}" />
		<c:set var="panelTitle" value="Reservation n° ${requestScope.reservation.id}" />
	</c:when>
	<c:otherwise>
		<c:set var="parameter" value="/new" />
		<c:set var="panelTitle" value="Nouvelle réservation" />
	</c:otherwise>
</c:choose>
	
<div class="panel panel-primary">
	<div class="panel-heading">${panelTitle}</div>
	
	<div class="panel-body">
		<div class="container-fluid">
			
			<c:choose>
				<c:when test="${not empty requestScope.reservation}">
					<%@include file="forms/edit.jsp" %>
				</c:when>
				<c:when test="${empty requestScope.resourceType}">
					<%@include file="forms/step1.jsp" %>
				</c:when>
				
				<c:when test="${not empty requestScope.resourceType and not empty requestScope.authenticatedUser}">
					<%@include file="forms/step2.jsp" %>
				</c:when>
			</c:choose>
		</div>
	</div>
</div>