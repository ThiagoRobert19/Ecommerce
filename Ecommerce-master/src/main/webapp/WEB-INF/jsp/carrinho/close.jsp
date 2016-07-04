<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/cabecalho/header.jsp" />
<!--menu-->
  <link rel="stylesheet" href="css/menu.css" type="text/css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="script/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="script/funcao.js"></script>
<!--menu-->

<div class="check">
	<div class="container">
		<!-- ################################################################################# -->
		<div class="col-md-8 cart-total">
			<a class="continue" href="<c:url value='/'/>"><fmt:message key="pagamento.pagamento" /></a>
			<h3>
				<b><fmt:message key="tipo.entrega" /></b>
			</h3>
			<hr class="featurette-divider">
			<form action="<c:url value='/CartaoCredito'/>" method="Post">
				<div class="price-details">
					<h3><fmt:message key="escolha.frete" /></h3>
					<input TYPE="radio" NAME="radios" VALUE="radio2" checked>
						<fmt:message key="normal.gratis" /> <BR>
					<input TYPE="radio" NAME="radios" VALUE="radio3">
						<fmt:message key="expressa.expressa" /><BR /> <BR /> 
					<a class="continue" href="<c:url value='/'/>">
						<fmt:message key="pague.credito" />
					</a>
					<div class="reg-form col-md-12">
						<div class="reg">
							<big><label class="text-info"><fmt:message key="numero.cartao" /></label></big><br>
							<input type="text" name="cartao.numeroCartao" size="75" required><br> <br>
							<big><label class="text-info"><fmt:message key="nome.cartao" /></label></big><br>
							 <input type="text" name="cartao.nomeCartao" size="75" required><br> <br> 
							 <big><label class="text-info"><fmt:message key="data.validade" /></label></big><br>
							 <select type="text" name="cartao.mesCartao">
							 	<!--option>Mês</option-->
								<option>01</option>
								<option>02</option>
								<option>03</option>
								<option>04</option>
								<option>05</option>
								<option>06</option>
								<option>07</option>
								<option>08</option>
								<option>09</option>
								<option>10</option>
								<option>11</option>
								<option>12</option>
							</select>
							<select type="text" name="cartao.anoCartao">
								<!--option>Ano</option-->
							<c:forEach var="datas" items="${ano}">
								
								<option>${datas}</option>
							</c:forEach>
							</select><br> <br>
							
							<big><label class="text-info"><fmt:message key="codigo.seguranca" /></label></big><br>
							<input type="text" name="cartao.codigoCartao"  maxlength="3" onkeypress='return SomenteNumero(event)' required><br> <br> 
							<big><label class="text-info"><fmt:message key="opcao.pagamento" /></label></big><br>
							<select type="text" name="cartao.opcaoCartao">
								<option>${total}</option>
								<option>${parcelado1}</option>
								<option>${parcelado2}</option>
								<option>${parcelado3}</option>
								<option>${parcelado4}</option>
								<option>${parcelado5}</option>
								<option>${parcelado6}</option>
								<option>${parcelado7}</option>
								<option>${parcelado8}</option>
								<option>${parcelado9}</option>
							</select>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="shocase-rt-bot">
					<div class="float-qty-chart">
						<ul>
							<li class="ad-2-crt simpleCart_shelfItem">
								<div class="ad-2-crt simpleCart_shelfItem">
									<button type="submit">
										<a class="btn item_add" role="button" style="width: 150px;"><fmt:message key="cartao.credito" /></a>
									</button>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</form>
			<hr class="featurette-divider">

			<div class="clearfix"></div>
			<a class="order" href="<c:url value='/FecharCompra'/>"><fmt:message key="pagar.boleto" /></a><br /> 
				<div class="shocase-rt-bot">
					<div class="float-qty-chart">
						<ul>
							<li class="ad-2-crt simpleCart_shelfItem">
								<div class="ad-2-crt simpleCart_shelfItem">
									<button>
										<a href="<c:url value='/GeraBoleto'/>" class="btn item_add" role="button" style="width: 150px;"><fmt:message key="boleto.boleto" /></a>
									</button>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<c:if test="${not empty errors}">
					<div class="alert alert-danger">
						<c:forEach var="error" items="${errors}">
                	${error.message}
						</c:forEach>
					</div>
				</c:if>
			
		</div>
		<!-- ################################################################################# -->
		<div class="col-md-4 cart-total">
			<a class="continue" href="<c:url value='/'/>"><fmt:message key="resumo.pedido" /></a>
			<div class="price-details">
				<h3>
					<b><fmt:message key="entrega.entrega" /></b>
				</h3>
				<hr class="featurette-divider">
				<h4>
					<b><i><fmt:message key="endereco.principal" /></i></b>
				</h4>
				<c:forEach var="cliente" items="${listaCliente}">
					</br> <fmt:message key="rua.rua" /> ${cliente.endereco} , ${cliente.numero}<br />
					 <fmt:message key="cidade.cidade" />${cliente.cidade}<br />
					 <fmt:message key="cep.cep" /> ${cliente.cep}<br />
					 <fmt:message key="estado.estado" />${cliente.estado}<br />
				</c:forEach>
				<div class="clearfix"></div>
			</div>

			<a class="continue" href="<c:url value='/'/>"><fmt:message key="item.pedido" /></a>
			<table class="table table-striped" cellspacing="0" cellpadding="0">
				<thead>
						<tr>
							<th><fmt:message key="descricao.descricao" /></th>
							<th><fmt:message key="quantidade.quantidade" /></th>
							<th><fmt:message key="numero.numero" /></th>
						</tr>
				</thead>
				<tbody>
					<c:forEach var="carrinho" items="${listaCarrinho}">
						<tr>
							<td>${carrinho.produto}</td>
							<td>${carrinho.quantidade}</td>
							<td>${carrinho.numero}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			 <a class="continue" href="<c:url value='/'/>"><fmt:message key="total.desconto" /> ${total}</a>


			
			<div class="clearfix"></div>
			
		</div>


		<!-- ################################################################################# -->
		<div class="clearfix"></div>
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