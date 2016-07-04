<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="/WEB-INF/cabecalho/header.jsp" />
<!--menu-->
  <link rel="stylesheet" href="css/menu.css" type="text/css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="script/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="script/funcao.js"></script>
<!--menu-->
<c:if test="${not empty errors}">
	<div class="alert alert-danger">
		<c:forEach var="error" items="${errors}">
                	${error.message}
		</c:forEach>
	</div>
</c:if>
<div class="feel-fall"></div>

<div class="shop-grid">
	<div class="container">
		<c:forEach var="produto" items="${listaProdutos}">
			<form action="<c:url value='/Single'/>" method="post">
				<input type="hidden" name="produto.codigo" value="${produto.codigo}">
				<input type="hidden" name="produto.id" value="${produto.id}">
				<div class="col-md-4 grid-stn simpleCart_shelfItem">
					<!-- normal -->
					<div class="ih-item square effect3 bottom_to_top">
						<div class="bottom-2-top">
							<div class="img">
								<img src="images/grid4.jpg" alt="/"
									class="img-responsive gri-wid">
							</div>
							<div class="info">
								<div class="pull-left styl-hdn">

									<h3>${produto.id}-${produto.descricao}</h3>


								</div>
								<div class="pull-right styl-price">
									<p>
										<a href="#"><span
											class="glyphicon glyphicon-shopping-cart grid-cart"
											aria-hidden="true"></span> <span class=" item_price">${produto.valor_venda}</span></a>
									</p>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<!-- end normal -->
					<div class="quick-view">

						<button type="submit">
							<a><fmt:message key="visualizar.visualizar" /></a>
						</button>
					</div>
				</div>
			</form>
		</c:forEach>
		<div class="clearfix"></div>
	</div>
</div>
<c:import url="/WEB-INF/cabecalho/footer.jsp" />