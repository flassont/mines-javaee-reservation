<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">

	<div class="row">
		<%@include file="navbar.jsp"%>
	</div>

	<div class="row">

		<c:choose>
			<c:when test="${not requestScope.creationMode}">
				<c:set var="colDef" value="col-md-9" />
				<div class="col-md-3">
					<div class="search-form">
						<jsp:include page="${requestScope.entity}/filter.jsp" />
					</div>
					<a
						href="<%=application.getContextPath()%>/app/${requestScope.entity}/new"
						role="button" class="btn btn-primary btn-block">Créer</a>
				</div>
			</c:when>
			<c:otherwise>
				<c:set var="colDef" value="col-md-6 col-md-offset-3" />
			</c:otherwise>
		</c:choose>

		<div class="${colDef}">
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