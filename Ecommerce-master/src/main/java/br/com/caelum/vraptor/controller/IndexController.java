package br.com.caelum.vraptor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;







import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ClienteDao;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class IndexController {

	private final ClienteLogado clienteLogado;
	
	private final Validator validator;

	
	private final Result result;
	
	
	@Inject
	public IndexController(ClienteLogado clienteLogado, Validator validator, Result result) {
		super();
		this.clienteLogado = clienteLogado;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
    IndexController() {
        this(null,null,null); 
    }



	@Path("/")
	public void index() {
		List<Produto> produto = new ArrayList<Produto>();
        ProdutoDao dao = new ProdutoDao();
        produto = dao.lista();
        result.include("total", Total.getTotal());
        result.include("listaProdutos", produto);
     }
}