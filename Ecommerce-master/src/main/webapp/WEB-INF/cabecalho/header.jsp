<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>New-Shoes E-commerce - Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	  
<script type="application/x-javascript">	
addEventListener("load", function() {setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>



<!--menu-->
  <link rel="stylesheet" href="css/menu.css" type="text/css"/>
  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
  <script type="text/javascript" src="script/jquery.easing.1.3.js"></script>
  <script type="text/javascript" src="script/funcao.js"></script>
<!--menu-->




<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

<script src="js/simpleCart.min.js"></script>
<script src="js/jquery-2.1.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>



<style>
	.bnd{margin-top:-236px;}
	#not{list-style:none;}
	#option{margin-top:-110px;}
	
	.login-out img {width:30px; height:30px;}
	.ch img {width:50px; height:50px;}



/*.login-bars{border: solid red 10px;}*/
/*.pull-left, .bnd{border: solid blue 10px;}*/
/*.logo{border: solid green 10px;}*/


#bs-example-navbar-collapse-1{border: solid yellow 10px;margin-left:350px;}


</style>

</head>
<body>
	<c:if test="${not empty cultura.locale}">
		<fmt:setLocale value="${cultura.locale}" scope="session" />

	</c:if>
<div class="row form-group">
		<div class="navbar navbar-default navbar-top">
			<div class="container">
				<div class="header">
					<div class="container">
					
						<div class="header-top">
						<div class="row">
							<div>
								<a href="<c:url value='/'/>">
								<img src="images/logo.png" alt=""/></a>
							</div>
							
							<div class="pull-left">
							
								<li id="not" class="dropdown dropdown-language">
								<a href="# class=" dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
									data-close-others="true"> <img class="bnd" width="32px" alt=""
										src="<c:url value='/bandeiras/${cultura.locale }.png'/>">
								</a>
									<ul id="option" class="dropdown-menu dropdown-menu-default">
										<c:if test="${cultura.locale.country!='BR' }">
											<li><a
												href="<c:url value='/MudaLingua?lingua1=pt&lingua2=BR'/>"
												onclick="Javascript:setCultura('pt','BR','${pageContext.request.contextPath}' );">
													<img width="32px" alt=""
													src="<c:url value='/bandeiras/pt_BR.png'/>"> <fmt:message
														key="menu.br" />
											</a></li>
										</c:if>
										<c:if test="${cultura.locale.country!='ES' }">
											<li><a
												href="<c:url value='/MudaLingua?lingua1=es&lingua2=ES'/>"
												onclick="Javascript:setCultura('es','ES','${pageContext.request.contextPath}' );">
													<img width="32px" alt=""
													src="<c:url value='/bandeiras/es_ES.png'/>"> <fmt:message
														key="menu.es" />
											</a></li>
										</c:if>
										<c:if test="${cultura.locale.country!='US' }">
											<li><a
												href="<c:url value='/MudaLingua?lingua1=en&lingua2=US'/>"
												onclick="Javascript:setCultura('en','US','${pageContext.request.contextPath}' );">
													<img width="32px" alt=""
													src="<c:url value='/bandeiras/en_US.png'/>"> <fmt:message
														key="menu.en" />
											</a></li>
										</c:if>



									</ul></li> <!--AZUL--></div></div>
							<div class="login-bars">
								

								<c:if test="${not empty clienteLogado.cliente.usuario}">
								<span style="color:red;">Bem Vindo :</span>
									<b>  ${clienteLogado.cliente.usuario}       </b>
									<span style="padding-right:90px;"></span>
									
									<a class="login-out"
										href="<c:url value='/Logout'/>" role="button">        <fmt:message
											key="logout.logout" />           <img src="images/logout.png" alt=""/></a>
											<span style="padding-right:20px;"></span>
									<a class="ch"
										href="<c:url value='/Checkout?cliente=${clienteLogado.cliente.usuario}'/>"
										role="button"><fmt:message key="checkout.checkout" /><img src="images/checkout.png" alt=""/></a>

									<div class="cart box_1" style="margin-right:-400px;">
										<h3>
											<a href="<c:url value='/ZerarCarrinho'/>"
												class="simpleCart_empty" role="button"> <fmt:message
													key="zerar.zerar" />
											</a> R$ ${total}

											<div>

												<!--span class="simpleCart_total"></span>asd(<span
													id="simpleCart_quantity" class="simpleCart_quantity"></span>)-->
											</div>
										</h3>
										<div class="clearfix"></div>
									</div>

								</c:if>
								<c:if test="${empty clienteLogado.cliente.usuario}">
									<a class="login-out"
										href="<c:url value='/Register'/>" role="button"> <fmt:message
											key="cadastro.cadastro" />   <img src="images/novo.png" alt=""/></a>
											<span style="padding-right:20px;"></span>
									<a class="login-out"
										href="<c:url value='/LoginPage'/>" role="button"> <fmt:message
											key="login.login" />            <img src="images/login.png" alt=""/></a>
								</c:if><!--VERMELHO--></div>
							<div class="clearfix"></div>
						</div>
							

							<!--AMARELO *MENU*-->

							
											
											<div class="clearfix"></div>
										</ul>
										<div class="clearfix"></div>
									</div>



									<div class="clearfix"></div>
								</nav>
								<!--/.navbar-->
								<div class="clearfix"></div>
							</div>
							<!--/.content--->
						</div>
						<!--header-bottom-->
					</div>
				</div>
			</div>
		</div>
	</div>
	</br>
	</br>



<ul id="menu" class="menu">
					<li>
						<a  href="<c:url value='/Register'/>" role="button">
							<img src="images/2.jpg" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link">New Shoes</span>
								<span class="sdt_descr">Seja um de nossos clientes</span>
							</span>
						</a>
					</li>
					<li>
						<a href="#">
							<img src="images/1.jpg" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link"><fmt:message key="categoria.categoria" /></span>
								<span class="sdt_descr">Conheça nossos produtos</span>
							</span>
						</a>
						<div class="sdt_box">
								<a href="<c:url value='/CategoriaProduto?categoria=ESPORTE'/>"><fmt:message
																			key="esporte.esporte" /></a>
								<a href="<c:url value='/CategoriaProduto?categoria=CASUAL'/>"><fmt:message
																			key="casual.casual" /></a>
								<a href="<c:url value='/CategoriaProduto?categoria=SOCIAL'/>"><fmt:message
																			key="social.social" /></a>
								<a href="<c:url value='/CategoriaProduto?categoria=SANDALIA'/>"><fmt:message
																			key="sandalia.sandalia" /></a>
								<a href="#<c:url value='/CategoriaProduto?categoria=RASTEIRINHA'/>"><fmt:message
																			key="rasteirinha.rasteirinha" /></a>
								<a href="<c:url value='/CategoriaProduto?categoria=BOTA'/>"><fmt:message
																			key="bota.bota" /></a>
								<a href="<c:url value='/CategoriaProduto?categoria=SAPATENIS'/>"><fmt:message
					
																			key="sapatenis.sapatenis" /></a>
						</div>
					</li>
					<li>
						<a href="<c:url value='/CategoriaProduto?categoria=MASCULINO'/>">
							<img src="images/3.jpg" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link"><fmt:message key="masculino.masculino" /></span>
								<span class="sdt_descr">Calçado para os homens</span>
							</span>
						</a>
					</li>
					<li>
						<a href="<c:url value='/CategoriaProduto?categoria=FEMININO'/>">
							<img src="images/4.jpg" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link"><fmt:message key="feminino.feminino" /></span>
								<span class="sdt_descr">Calçado para as mulheres</span>
							</span>
						</a>
					</li>
					<li>
						<a href="<c:url value='/CategoriaProduto?categoria=INFANTIL'/>">
							<img src="images/5.jpg" alt=""/>
							<span class="sdt_active"></span>
							<span class="sdt_wrap">
								<span class="sdt_link"><fmt:message key="infantil.infantil" /></span>
								<span class="sdt_descr">Calçado para as crianças</span>
							</span>
						</a>
					</li>
					
				</ul>




        


			












