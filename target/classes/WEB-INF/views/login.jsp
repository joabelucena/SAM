<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="includes/header.jsp"%>

<style>

	body {
		background-image: url('resources/img/login-bg.jpg');
		background-color: #FFF;
		background-position: center;
		background-repeat: no-repeat;
		vertical-align: middle;
	}
	
	.error {
		padding: 15px;
		margin-bottom: 20px;
		border: 1px solid transparent;
		border-radius: 4px;
		color: #a94442;
		background-color: #f2dede;
		border-color: #ebccd1;
	}

</style>



<form class="form-signin" action="j_spring_security_check" method="POST">
	<h2 class="form-signin-heading">
		<strong>SAM<strong><p>Sistema de Apoio a
					Manutenção</p>
	</h2>

	<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<div class="login-wrap">

		<input type="text" name="username" class="form-control"
			placeholder="<spring:message code="login.user" />" autofocus>
		<input type="password" name="password" class="form-control"
			placeholder="<spring:message code="login.password" />">

		<button class="btn btn-lg btn-login btn-block" type="submit">
			<spring:message code="login.signin" />
		</button>

	</div>

	<img class="form-signin-image" src="resources/img/logo-vlt-emtu.png">

</form>

</div>

<!-- js placed at the end of the document so the pages load faster -->
<script src="../resources/js/jquery.js"></script>
<script src="../resources/js/bootstrap.min.js"></script>

</body>
</html>
