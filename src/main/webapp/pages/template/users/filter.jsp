<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- dynamiccaly check the checkbox if needed -->
<c:choose>
	<c:when test="${administrator}">
       <c:set var="check" value="checked" />
    </c:when>
	<c:otherwise>
        <c:set var="check" value="" />
    </c:otherwise>
</c:choose>

<form method="get" action="<%=application.getContextPath()%>/test/${requestScope.entity}/search">

	<div class="form-group">
		<label for="lastName">Nom</label>
		<input type="text" class="form-control" value="${lastName}" id="lastName" name="lastName" placeholder="Nom">
	</div>
	
	<div class="form-group">
		<label for="firstName">Pr�nom</label>
		<input type="text" class="form-control" value="${firstName}" id="firstName" name="firstName" placeholder="Nom">
	</div>
	
	 <div class="checkbox">
    	<label for="administrator">
      		<input type="checkbox" ${check} id="administrator" name="administrator">Administrateur
      	</label>
  	</div>
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>