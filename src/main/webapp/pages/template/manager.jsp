<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<%@include file="navbar.jsp"%>
	</div>

	<div class="row">
		<div class="col-md-3">
			<div class="search-form">
				<jsp:include page="${requestScope.entity}/filter.jsp" />
			</div>
			<button type="button" class="btn btn-default btn-block">Créer</button>
		</div>
		<div class="col-md-9">
			<c:choose>
				<c:when test="${requestScope.creationMode}"><jsp:include
						page="${requestScope.entity}/creationForm.jsp" /></c:when>
				<c:otherwise><jsp:include
						page="${requestScope.entity}/table.jsp" /></c:otherwise>
			</c:choose>
		</div>
	</div>
</div>