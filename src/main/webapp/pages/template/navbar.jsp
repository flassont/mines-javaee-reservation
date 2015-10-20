<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-default">
 	<div class="container-fluid">
	  
	    <div class="navbar-header">
	     	<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false"></button>
	     	<a class="navbar-brand" href="#">Ressource Manager</a>
	    </div>
	
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      	<ul class="nav navbar-nav">
	        	<li <c:if test="${requestScope.entity == 'booking'}"> class="active"</c:if>><a href="bookings">Réservations</a></li>
	        	<li <c:if test="${requestScope.entity == 'resource'}">class="active"</c:if>><a href="resources">Ressources</a></li>
	        	<li <c:if test="${requestScope.entity == 'type'}">    class="active"</c:if>><a href="types">Types de ressources</a></li>
	        	<li <c:if test="${requestScope.entity == 'user'}">    class="active"</c:if>><a href="users">Utilisateurs</a></li>
	      	</ul>
	      
	      	<ul class="nav navbar-nav navbar-right">
	        	  <li><a href="#">Profil</a></li>
	          	<li><a href="logout">Deconnexion</a></li>
	      	</ul>
	    </div>
 	</div>
</nav>