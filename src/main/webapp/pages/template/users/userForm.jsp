<div class="form-group">
	<label for="login">Nom d'utilisateur</label>
	<input type="text" class="form-control" value="${user.login}" id="login" name="login" placeholder="Login">
</div>
	
<div class="form-group">
	<label for="password">Mot de passe</label>
	<input type="password" class="form-control" value="${user.password}" id="password" name="password" placeholder="Mot de passe">
</div>
	
<div class="form-group">
	<label for="lastName">Nom</label>
	<input type="text" class="form-control" value="${user.lastName}" id="lastName" name="lastName" placeHolder="Nom">
</div>
	
<div class="form-group">
	<label for="firstName">Prénom</label>
	<input type="text" class="form-control" value="${user.firstName}" id="firstName" name="firstName" placeHolder="Prénom">

</div>
	
<div class="form-group">
	<label for="mail">Email</label>
	<input type="email" class="form-control"  value="${user.mail}" id="mail" name="mail" placeHolder="Email">
</div>
	
<div class="form-group">
	<label for="phone">Téléphone</label>
	<input type="text" class="form-control" value="${user.phone}" id="phone" name="phone" placeHolder="Téléphone">
</div>
	
<div class="text-center">
	<button type="submit" class="btn btn-success inline-button">Valider</button>
</div>