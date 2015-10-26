<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="in" uri="/../../tags/input.tld"%>
<%@ taglib prefix="mod" uri="/../../tags/modelSubmit.tld"%>

<form method="post" action="${param.contextPath}/app/${requestScope.entity}/${parameter}">	
	
	<in:Input name="resourceType" display="Type de la ressource" placeholder="Type de la ressource" value="${resourceType.name}" additionalHtml="disabled"/>
	
	<div class="form-group">
		<label for="reserved">Ressource</label>
		<select class="form-control" id="reserved" name="reserved" required>
			<c:forEach items="${resources}" var="resource">
				<option value="${resource.id}">${resource.name}</option>
			</c:forEach>
		</select>
	</div>
			
	<div class="form-group">
		<label for="reserver">Utilisateur</label>
		<select class="form-control" id="reserver" name="reserver" readonly>
			<option value="${authenticatedUser.id}">${authenticatedUser.firstName} ${authenticatedUser.lastName}</option>
		</select>
	</div>
	
	<in:Input name="begin" display="Début" placeholder="Début" value="" additionalHtml="data-provide=\"datepicker\" data-date-format=\"dd/mm/yyyy\""/>
	<in:Input name="end" display="Fin" placeholder="Fin" value="" additionalHtml="data-provide=\"datepicker\" data-date-format=\"dd/mm/yyyy\""/>

	<mod:ModelSubmit model="${model}"/>	

</form>