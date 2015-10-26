<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="vertical-center">
	<div class="login-form">

		<span class="form-header">
			<h2>Se connecter</h2>
		</span>
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<form method="POST"
			action="${javax.servlet.forward.request_uri}">
			<div class="form-group">
				<label for="loginAuth">Nom d'utilisateur</label> <input type="text"
					class="form-control" id="loginAuth" name="loginAuth" placeholder="Login">
			</div>
			<div class="form-group">
				<label for="passwordAuth">Mot de passe</label> <input type="password"
					class="form-control" id="passwordAuth" name="passwordAuth"
					placeholder="Mot de passe">
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-success inline-button">Connexion</button>
			</div>
		</form>
	</div>
</div>