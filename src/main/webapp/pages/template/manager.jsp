<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="navbar.jsp"%>

<div class="container">

	<div class="row">

		<c:choose>
			<c:when test="${not requestScope.creationMode}">
				<c:set var="colDef" value="col-md-9" />
				<div class="col-md-3">
					<div class="search-form">
						<jsp:include page="${requestScope.entity}/filter.jsp">
							<jsp:param name="contextPath" value="${param.contextPath}"/>
						</jsp:include>
					</div>
					<a
						href="${param.contextPath}/app/${requestScope.entity}/new"
						role="button" class="btn btn-primary btn-block">Créer</a>
				</div>
			</c:when>
			<c:otherwise>
				<c:set var="colDef" value="col-md-6 col-md-offset-3" />
			</c:otherwise>
		</c:choose>

		<div class="${colDef}">
		
			<c:if test="${not empty transactionError}">
				<div class="panel panel-danger">
					<div class="panel-heading">Erreur</div>
					<div class="panel-body">${transactionError}</div>
				</div>
			</c:if>
			
			<c:choose>
				<c:when test="${requestScope.creationMode}">
					<jsp:include page="${requestScope.entity}/creationForm.jsp">
						<jsp:param name="contextPath" value="${param.contextPath}"/>
					</jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="${requestScope.entity}/table.jsp">
						<jsp:param name="contextPath" value="${param.contextPath}"/>
					</jsp:include>
				</c:otherwise>
			</c:choose>
			
		</div>

	</div>

</div>