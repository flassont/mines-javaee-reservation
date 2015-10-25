<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="in" uri="/../../tags/input.tld"%>
<!-- dynamiccaly check the checkbox if needed -->
<c:choose>
	<c:when test="${isAdmin}">
       <c:set var="check" value="checked" />
    </c:when>
	<c:otherwise>
        <c:set var="check" value="" />
    </c:otherwise>
</c:choose>

<form method="get" action="${param.contextPath}/app/${requestScope.entity}/search">
	
	<in:Input required="false" name="lastName" display="Nom" placeholder="Nom" value="${lastName}"/>
	<in:Input required="false" name="firstName" display="Prénom" placeholder="Prénom" value="${firstName}"/>
	<in:Input required="false" name="isAdmin" display="Administrateur" placeholder="" value="" type="checkbox" additionalHtml="${check}"/>

	
<!-- 	 <div class="checkbox"> -->
<!--     	<label for="administrator"> -->
<%--       		<input type="checkbox" ${check} id="administrator" name="administrator">Administrateur --%>
<!--       	</label> -->
<!--   	</div> -->
	
	<button type="submit" class="btn btn-default">Rechercher</button>
	
</form>