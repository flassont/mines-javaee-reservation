<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form method="POST" action="<%=application.getContextPath()%>/${requestScope.entity}">
	
	Formulaire de cr�ation
	
	<button type="submit" class="btn btn-success">Valider</button>
	
</form>