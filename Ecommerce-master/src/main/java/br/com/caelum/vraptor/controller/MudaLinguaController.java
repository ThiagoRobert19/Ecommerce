package br.com.caelum.vraptor.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class MudaLinguaController {
	private final HttpServletRequest request;

	private final HttpServletResponse response;
	private final Validator validator;

	private final ClienteLogado clienteLogado;
	private final CulturaScope culturaScope;

	private final Result result;
	@Inject
	public MudaLinguaController(Validator validator, ClienteLogado clienteLogado, Result result,
			HttpServletRequest request, HttpServletResponse response,CulturaScope culturaScope) {
		super();
		this.validator = validator;
		this.clienteLogado = clienteLogado;
		this.result = result;
		this.request = request;
		this.response = response;
		this.culturaScope=culturaScope;
	}

	@Deprecated
	MudaLinguaController() {
		this(null, null, null, null, null,null);
	}
	@Path("/MudaLingua")
	public void mudaLingua(String lingua1,String lingua2){

		Locale locale = new Locale(lingua1, lingua2);
		culturaScope.setLocale(locale);

	    result.redirectTo(IndexController.class).index();
	    
	}
	
	
}
