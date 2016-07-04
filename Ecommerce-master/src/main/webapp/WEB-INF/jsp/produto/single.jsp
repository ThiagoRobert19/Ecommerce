<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/cabecalho/header.jsp" />
<!--menu-->
  <link rel="stylesheet" href="css/menu.css" type="text/css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="script/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="script/funcao.js"></script>
<!--menu-->

<div class="showcase-grid">
	<div class="container">
		<!-- ------------------------------------------------------------------ -->
		<div class="col-md-8 showcase">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
					<li data-target="#myCarousel" data-slide-to="3"></li>
				</ol>

				
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="images/show.jpg" alt="...">
					</div>
					<div class="item">
						<img src="images/show1.jpg" alt="...">

					</div>
					<div class="item">
						<img src="images/show2.jpg" alt="...">

					</div>
					<div class="item">
						<img src="images/show3.jpg" alt="...">

					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left car-icn" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right car-icn"
					aria-hidden="true"></span> <span class="sr-only">Next</span>
				</a>
			</div>
		</div>

		<!-- --------------------------------------------------------------- -->
		<form action="<c:url value='/AdicionarCarrinho'/>" method="post">
			<div class="col-md-4 showcase">
				<div class="showcase-rt-top">

					<c:forEach var="produto" items="${listaProdutos}">
						<div class="pull-left shoe-name">
							<input type="hidden" name="cliente.usuario"
								value="${clienteLogado.cliente.usuario}" /> <input
								type="hidden" name="produto.id" value="${produto.id}" /> <input
								type="hidden" name="produto.descricao"
								value="${produto.descricao}" /> <input type="hidden"
								name="produto.valor_venda" value="${produto.valor_venda}" />
							<h3>${produto.descricao}</h3>
							<p>${produto.categoria}-${produto.subcategoria}</p>
							<h4>&#36;${produto.valor_venda}</h4>
						</div>
					</c:forEach>

					<div class="pull-left rating-stars">
						<ul>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
				<hr class="featurette-divider">
				<div class="shocase-rt-bot">
					<div class="float-qty-chart">
						<ul>
							<li class="qty">
								<h3><fmt:message key="numero.numero" /></h3> <select class="form-control siz-chrt"
								name="numero.numero">
									<c:forEach var="numero" items="${listaNumeros}">
										<option>${numero.numero}</option>
									</c:forEach>
							</select>

							</li>
							<li class="qty">
								<h4><fmt:message key="quantidade.quantidade" /></h4> <select class="form-control qnty-chrt"
								name="numero_produto.quantidade">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
							</select>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<c:if test="${not empty clienteLogado.cliente.usuario}">
						<ul>
							<li class="ad-2-crt simpleCart_shelfItem">
								<button type="submit">
									<a class="btn item_add" role="button" style="width: 150px;">
									<fmt:message key="add.carrinho" /></a>
								</button>
							</li>
						</ul>
					</c:if>

				</div>
				<div class="showcase-last">
					<h3><fmt:message key="detalhes.produto" /></h3>
					<ul>
						<li>${comentario}</li>

					</ul>
				</div>
			</div>
		</form>

		<div class="clearfix"></div>
	</div>
</div>

<div class="specifications">
	<div class="container">
		<h3>Item Details</h3>
		<div class="detai-tabs">
			<!-- Nav tabs -->
			<ul class="nav nav-pills tab-nike" role="tablist">
				<li role="presentation" class="active"><a href="#home"
					aria-controls="home" role="tab" data-toggle="tab"><fmt:message key="avaliacao.avaliacao" /></a></li>

				<li role="presentation"><a href="#messages"
					aria-controls="messages" role="tab" data-toggle="tab"><fmt:message key="caracteristica.caracteristica" /></a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content col-md-8 showcase">

				<div role="tabpanel" class="tab-pane active" id="home">
					<div class="rating-stars">
						<ul>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#" class="active"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span></a></li>
						</ul>
					</div>
					<p>O tênis possui um design muito bonito, com traços diferentes
						de qualquer outro Tênis que já tinha visto. Gosto muito das linhas
						nike e essa, com certeza, não podia ser diferente a questão
						beleza, conforto e durabilidade!</p>


				</div>


				<div role="tabpanel" class="tab-pane" id="messages">
					<p>
						<b>Tecnologia:</b> Phylon, Air Max
					</p>
					<p>
						<b>Definição da Tecnologia:</b> Sistema de amortecimento leve e
						durável.
					</p>
					<p>
						<b>Composição:</b> Cabedal: em material sintético e mesh
						respirável para maior ventilação e suporte. Entressola: com espuma
						Phylon com amortecimento Air Max em todo o comprimento para
						amortecimento. Solado: em borracha com padrão multidirecional para
						excelente tração.
					</p>
					<p>
						<b>Peso do Produto:</b> 324g. (o peso do calçado varia de acordo
						com a numeração.)
					</p>
					<p>
						<b>Garantia do Fabricante:</b> Contra Defeito de Fabricação
					</p>
					<p>Origem: Importado</p>
				</div>
			</div>
		</div>

	</div>
</div>
<c:import url="/WEB-INF/cabecalho/footer.jsp" />

