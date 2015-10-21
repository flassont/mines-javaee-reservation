<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default">
 	<div class="container-fluid">
	  
	    <div class="navbar-header">
	     	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"></button>
	     	<a class="navbar-brand" href="<%=application.getContextPath()%>">Ressource Manager</a>
	    </div>
	
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      	<ul class="nav navbar-nav">
	        	<li <c:if test="${requestScope.entity == 'bookings'}"> class="active"</c:if>><a href="<%= application.getContextPath()%>/bookings">Réservations</a></li>
	        	<li <c:if test="${requestScope.entity == 'resources'}">class="active"</c:if>><a href="<%= application.getContextPath()%>/resources">Ressources</a></li>
	        	<li <c:if test="${requestScope.entity == 'types'}">    class="active"</c:if>><a href="<%= application.getContextPath()%>/types">Types de ressources</a></li>
	        	<li <c:if test="${requestScope.entity == 'users'}">    class="active"</c:if>><a href="<%= application.getContextPath()%>/users">Utilisateurs</a></li>
	      	</ul>
	      
	      	<ul class="nav navbar-nav navbar-right">
	        	  <li><a href="#">Profil</a></li>
	          	<li><a href="logout">Deconnexion</a></li>
	      	</ul>
	    </div>
 	</div>
</nav>