<div class="vertical-center">
	<div class="login-form">
		<span class="form-header">
			<h2>Ouvrir un compte</h2>
			<p>
				Vous avez d�j� un compte ? <a href="login">Connectez-vous</a>
			</p>
		</span>
		
		<form id="loginForm" method="POST" action="/reservation/newUser">
			<%@include file="../user/userForm.jsp" %>
		</form>
	</div>
</div>