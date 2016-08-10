<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/cabecalho/header.jsp" />
<!--menu-->
  <link rel="stylesheet" href="css/menu.css" type="text/css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="script/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="script/funcao.js"></script>
<!--menu-->

<!-- check-out -->
<div class="check">
	<div class="container">
		<!-- ################################################################################# -->
		<div class="col-md-4 cart-total">
			<a class="continue" href="<c:url value='/'/>"><fmt:message key="continue.comprando" /></a>
			<div class="price-details">
				<h3><fmt:message key="detalhes.detalhes" /></h3>
				<span><fmt:message key="total.total" /></span> <span class="total1">${total}</span> <span><fmt:message key="desconto.desconto" /></span>
				<span class="total1">${porcento}%(<fmt:message key="promocao.especial" />)</span> <span>
				<fmt:message key="desconto.total" />
				</span> <span class="total1">${desconto}</span>
				<div class="clearfix"></div>
			</div>
			<hr class="featurette-divider">
			<ul class="total_price">
				<li class="last_price">
					<h4><fmt:message key="total.maiusculo" /></h4>
				</li>
				<li class="last_price"><span>${totalDesconto}</span></li>
				<div class="clearfix"></div>
			</ul>
			<div class="clearfix"></div>
			<a class="order" href="<c:url value='/FecharCompra'/>"><fmt:message key="fechar.compra" /></a>
		</div>

		<!-- ################################################################################# -->
		<!-- ################################################################################# -->
		

		<div class="col-md-8 cart-items">
		</br>
		<h1><fmt:message key="meu.carrinho" /> (${tamanho})</h1>
			<c:forEach var="carrinho" items="${listaCarrinho}">
				<div class="cart-header">
					<div class="close1">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					</div>
					<div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							<img src="images/grid8.jpg" class="img-responsive" alt="" />
						</div>
						<div class="cart-item-info">
							<ul class="qty">
								<li><p><fmt:message key="descricao.descricao" /> ${carrinho.produto}</p></li>
								<li><p><fmt:message key="quantidade.quantidade" /> ${carrinho.quantidade}</p></li>
								<li><p><fmt:message key="cor.cor" /> ${carrinho.cor}</p></li>
								<li><p><fmt:message key="numero.numero" /> ${carrinho.numero}</p></li>
								<li><p><fmt:message key="valor.valor" /> ${carrinho.valor}</p></li>
							</ul>
							<div class="delivery">
								<p><fmt:message key="total.dinheiro" /> ${carrinho.total}</p>
								<!-- span>Prazo de entrega de 2-3 dias</span-->
								<div class="clearfix"></div>
							</div>
						</div>
						<div class="clearfix"></div>

					</div>
				</div>
			</c:forEach>
		</div>

		<!-- ################################################################################# -->
		<div class="clearfix"></div>
	</div>
</div>
<c:import url="/WEB-INF/cabecalho/footer.jsp" />
