<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${not empty model}">
		<c:set var="parameter" value="/edit?id=${model.id}" />
		<c:set var="panelTitle" value="Reservation n° ${model.id}" />
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
				<c:when test="${not empty model}">
					<%@include file="forms/edit.jsp" %>
				</c:when>
				<c:when test="${empty resourceType}">
					<%@include file="forms/step1.jsp" %>
				</c:when>
				<c:when test="${not empty resourceType && not empty authenticatedUser}">
					<%@include file="forms/step2.jsp" %>
				</c:when>
			</c:choose>
			
		</div>
	</div>
</div>