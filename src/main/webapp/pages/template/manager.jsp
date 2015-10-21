<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<div class="row">
		<%@include file="navbar.jsp"%>
	</div>

	<div class="row">
	
		<c:if test="${not requestScope.creationMode}">
		<div class="col-md-3">
			<div class="search-form">
				<jsp:include page="${requestScope.entity}/filter.jsp" />
			</div>
			<a href="<%=application.getContextPath()%>/${requestScope.entity}/new" role="button" class="btn btn-primary btn-block">Créer</a>
		</div>
		</c:if>
		
		<div class="col-md-9">
			<c:choose>
				<c:when test="${requestScope.creationMode}">
						<jsp:include page="${requestScope.entity}/creationForm.jsp" />
				</c:when>
				<c:otherwise>
					<jsp:include page="${requestScope.entity}/table.jsp" />
				</c:otherwise>
			</c:choose>
		</div>
		
	</div>
	
</div>