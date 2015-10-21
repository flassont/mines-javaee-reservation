<div class="vertical-center">
	<div class="login-form">
		
		<span class="form-header">
			<h2>Se connecter</h2>
			<p>
				<a href="signup">Ou créez un compte</a>
			</p>
		</span>
		
		<form method="POST" action="/login">
			<div class="form-group">
				<label for="login">Nom d'utilisateur</label>
				<input type="text" class="form-control" id="login" name="login" placeholder="Login">
			</div>
			<div class="form-group">
				<label for="password">Mot de passe</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-success inline-button">Connexion</button>
				<button type="button" class="btn btn-default inline-button">Mot de passe oublié</button>
			</div>
		</form>
	</div>
</div>