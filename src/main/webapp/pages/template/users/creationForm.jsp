<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- If a user entity has been past, it means we're editing it. To update it, we need to pass its id as parameter -->
<c:choose>
	<c:when test="${not empty user}">
       <c:set var="parameter" value="?id=${user.id}" />
    </c:when>
	<c:otherwise>
        <c:set var="parameter" value="" />
    </c:otherwise>
</c:choose>
<form method="POST"
	action="<%=application.getContextPath()%>/${requestScope.entity}${parameter}"
	class="form-horizontal">
	<%@ include file="userForm.jsp"%>
</form>