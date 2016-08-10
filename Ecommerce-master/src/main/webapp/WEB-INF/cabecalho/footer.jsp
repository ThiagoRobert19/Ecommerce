<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="sub-news">
	<div class="container">
		<form id="mail_form" method="post"
			action="<c:url value='/enviaEmail'/>">
			<h3><fmt:message key="noticia.noticia" /></h3>

			<input type="text" class="sub-email" value="Email"
				name="correio_eletronico" onfocus="this.value = '';"
				onblur="if (this.value == '') {
                        this.value = 'Email';
                    }">
			<a class="btn btn-default subs-btn" href="#"
				onClick="document.getElementById('mail_form').submit();"
				role="button"><fmt:message key="inscreva.inscreva" /></a>
		</form>
	</div>
</div>
</div>

<div class="footer-grid">
	<div class="container">
		<div class="col-md-2 re-ft-grd">
			<h3>
				<fmt:message key="caracteristica.caracteristica" />
			</h3>
			<ul class="categories">
				<li><a href="#"><fmt:message key="masculino.masculino" /></a></li>
				<li><a href="#"><fmt:message key="feminino.feminino" /></a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#"><fmt:message key="infantil.infantil" /></a></li>
				<li><a href="#"><fmt:message key="casual.casual" /></a></li>
				<li><a href="#"><fmt:message key="social.social" /></a></li>
				<li><a href="#"><fmt:message key="sandalia.sandalia" /></a></li>
				<li><a href="#"><fmt:message key="rasteirinha.rasteirinha" /></a></li>
				<li><a href="#"><fmt:message key="bota.bota" /></a></li>
				<li><a href="#"><fmt:message key="sapatenis.sapatenis" /></a></li>
			</ul>
		</div>

		<div class="col-md-4 re-ft-grd">
			<h3><fmt:message key="rede.social" /></h3>
			<ul class="social">
				<li><a href="https://www.facebook.com/ThiagoRobert1991"
					target="_blank" class="fb">facebook</a></li>
				<!--li><a href="https://www.facebook.com/lucas.jeronimo.52" target="_blank" class="gpls">g+ plus</a></li-->
				<div class="clearfix"></div>
			</ul>
		</div>
		<div class="col-md-4 re-ft-grd">
			<div class="bt-logo">
				<div>
					<a href="<c:url value='/'/>"> <img src="images/logo.png" alt="" /></a>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="copy-rt">
		<div class="container">
			<p>
				&copy; 2016 NEW SHOES All Rights Reserved. Design by <a
					href="https://www.facebook.com/ThiagoRobert1991">Thiago Robert</a>
			</p>
		</div>
	</div>


	</body>
	</html>