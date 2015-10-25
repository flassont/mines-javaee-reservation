<nav class="navbar navbar-default">
 	<div class="container-fluid">
	  
	    <div class="navbar-header">
	     	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-nav" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
	     	<a class="navbar-brand" href="${param.contextPath}/app">Ressource Manager</a>
	    </div>
	
	    <div class="collapse navbar-collapse" id="main-nav">
	      	<ul class="nav navbar-nav">
	        	<li <c:if test="${requestScope.entity == 'reservations'}"> class="active"</c:if>><a href="${param.contextPath}/app/reservations">Réservations</a></li>
		        	<c:if test="${authenticatedUser.isAdmin}">
		        		<li <c:if test="${requestScope.entity == 'resources'}">class="active"</c:if>><a href="${param.contextPath}/app/resources">Ressources</a></li>
		        		<li <c:if test="${requestScope.entity == 'resourceTypes'}">    class="active"</c:if>><a href="${param.contextPath}/app/resourceTypes">Types de ressources</a></li>
		        		<li <c:if test="${requestScope.entity == 'users'}">    class="active"</c:if>><a href="${param.contextPath}/app/users">Utilisateurs</a></li>
		        	</c:if>	
	        	</ul>
	      
	      	<ul class="nav navbar-nav navbar-right">
<!-- 	        	  <li><a href="#">Profil</a></li> -->
	          	<li><a href="<%=application.getContextPath()%>/app/logout">Deconnexion</a></li>
	      	</ul>
	    </div>
 	</div>
</nav>