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

<!-- reg-form -->
<div class="reg-form">
	<div class="container">
		<div class="reg">
			<h3><fmt:message key="cadastre.se" /></h3>
			<p><fmt:message key="bem.vindo" /></p>
			<p>
				<fmt:message key="se.voce" /> <a href="#"><fmt:message key="clique.aqui" /></a>
			</p>
			<form action="<c:url value='/NewUser'/>" method="post">
				<div class="row">
					<div class="col-md-12">
						<ul>
							<li class="text-info"><fmt:message key="nome.nome" /></li>
							<li><input type="text"  name="cliente.nome" required></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<ul>
							<li class="text-info"><fmt:message key="usuario.usuario" /></li>
							<li><input type="text" name="cliente.usuario" required></li>
						</ul>
					</div>

					<div class="col-md-4">
						<ul>
							<li class="text-info"><fmt:message key="senha.senha" /></li>
							<li><input type="password" name="cliente.senha" required></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul>
							<li class="text-info"><fmt:message key="cpf.cpf" /></li>
							<li><input type="text" name="cliente.cpf" required></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul>
							<li class="text-info"><fmt:message key="email.email" /></li>
							<li><input type="text" name="cliente.email" required></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<ul>
							<li class="text-info"><fmt:message key="endereco.endereco" /></li>
							<li><input type="text" name="cliente.endereco" required></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">
						<ul>
							<li class="text-info"><fmt:message key="num.numero" /></li>
							<li><input type="text" name="cliente.numero" onkeypress='return SomenteNumero(event)' required></li>
							
						</ul>
					</div>
					<div class="col-md-4">
						<ul>
							<li class="text-info"><fmt:message key="cidade.cidade" /></li>
							<li><input type="text" name="cliente.cidade" required></li>

						</ul>
					</div>

					<ul>
						<li class="text-info"><fmt:message key="estado.estado" /></li>
						<li><select name="cliente.estado">
								<option>AC</option>
								<option>AL</option>
								<option>AP</option>
								<option>AM</option>
								<option>BA</option>
								<option>CE</option>
								<option>DF</option>
								<option>ES</option>
								<option>GO</option>
								<option>MA</option>
								<option>MT</option>
								<option>MS</option>
								<option>MG</option>
								<option>PA</option>
								<option>PB</option>
								<option>PR</option>
								<option>PE</option>
								<option>PI</option>
								<option>RJ</option>
								<option>RN</option>
								<option>RS</option>
								<option>RO</option>
								<option>RR</option>
								<option>SC</option>
								<option>SP</option>
								<option>SE</option>
								<option>TO</option>
						</select></li>
					</ul>

				</div>
				<div class="row">
					<div class="col-md-4">
						<ul>
							<li class="text-info"><fmt:message key="cep.cep" /></li>
							<li><input type="text" name="cliente.cep" required></li>
						</ul>
					</div>
					<div class="col-md-4">
						<ul>
							<li class="text-info"><fmt:message key="telefone.telefone" /></li>
							<li><input type="text" name="cliente.telefone" required></li>
						</ul>
					</div>
				</div>

				<input type="submit" value="Cadastre-se">
				
			</form>
			<c:if test="${not empty errors}">
					<div class="alert alert-danger">
						<c:forEach var="error" items="${errors}">
                	${error.message}
						</c:forEach>
					</div>
				</c:if>
			<!-- -------------------------------------------------------------- -->

		</div>
	</div>
</div>
<script language='JavaScript'>
	function SomenteNumero(e) {
		var tecla = (window.event) ? event.keyCode : e.which;
		if ((tecla > 47 && tecla < 58))
			return true;
		else {
			if (tecla == 8 || tecla == 0)
				return true;
			else
				return false;
		}
	}
</script>
<c:import url="/WEB-INF/cabecalho/footer.jsp" />