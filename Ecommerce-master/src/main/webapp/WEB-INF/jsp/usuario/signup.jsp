<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/cabecalho/header.jsp" />
<!--menu-->
  <link rel="stylesheet" href="css/menu.css" type="text/css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="script/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="script/funcao.js"></script>
<!--menu-->
</br></br></br></br></br></br>

<!--signup-->
<!-- login-page -->
<div class="login">
	<div class="container">
		<div class="login-grids">
			<div class="col-md-6 log">
				<h3><fmt:message key="login.login" /></h3>
				<div class="strip"></div>
				<p><fmt:message key="por.favor" /></p>

				<form action="<c:url value='/Login'/>" method="post">
					<h5><fmt:message key="user.name" /></h5>
					<input type="text" name="cliente.usuario">
					<h5><fmt:message key="senha.senha" /></h5>
					<input type="password" name="cliente.senha"><br> <input
						type="submit" value="Login">

				</form>
				<c:if test="${not empty errors}">
					<div class="alert alert-danger">
						<c:forEach var="error" items="${errors}">
                	${error.message}
						</c:forEach>
					</div>
				</c:if>
				<!--a href="#">Esqueceu sua senha?</a-->
			</div>
			<div class="col-md-6 login-right">
				<h3><fmt:message key="novo.cadastro" /></h3>
				<div class="strip"></div>
				<p><fmt:message key="ao.criar" /></p>
				<p><fmt:message key="atraves.atraves" /></p>
				<p><fmt:message key="multiplo.multiplo" /></p>
				<p><fmt:message key="conta.muito" /></p>
					
				<a href="<c:url value='/Register'/>" class="button"><fmt:message key="cadastro.cadastro" /></a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!-- //login-page -->
<!--signup-->
<c:import url="/WEB-INF/cabecalho/footer.jsp" />