<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Value will be filled if we past the user variable in case of editing -->
<div class="form-group">
	<label for="login">Nom d'utilisateur</label>
	<input type="text" class="form-control" value="${user.login}" id="login" name="login"
		placeholder="Login" <c:if test="${not empty parameter}">readonly</c:if> required>
</div>

<div class="form-group">
	<label for="password">Mot de passe</label> <input type="password"
		class="form-control" value="${user.password}" id="password"
		name="password" placeholder="Mot de passe" required>
</div>

<div class="form-group">
	<label for="lastName">Nom</label> <input type="text"
		class="form-control" value="${user.lastName}" id="lastName"
		name="lastName" placeHolder="Nom" required>
</div>

<div class="form-group">
	<label for="firstName">Prénom</label> <input type="text"
		class="form-control" value="${user.firstName}" id="firstName"
		name="firstName" placeHolder="Prénom" required>

</div>

<div class="form-group">
	<label for="mail">Email</label> <input type="email"
		class="form-control" value="${user.mail}" id="mail" name="mail"
		placeHolder="Email" required>
</div>

<div class="form-group">
	<label for="phone">Téléphone</label> <input type="text"
		class="form-control" value="${user.phone}" id="phone" name="phone"
		placeHolder="Téléphone" required>
</div>

<div class="text-center">
	<c:choose>
		<c:when test="${not empty user}">
			<button type="submit" class="btn btn-success inline-button">Modifier</button>
		</c:when>
		<c:otherwise>
			<button type="submit" class="btn btn-success inline-button">Valider</button>
		</c:otherwise>
	</c:choose>
</div>