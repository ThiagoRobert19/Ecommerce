/**
 * 
 */
package br.com.caelum.vraptor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.CarrinhoDao;
import br.com.caelum.vraptor.dao.ClienteDao;
import br.com.caelum.vraptor.model.Carrinho;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

/**
 * CIAware :: Centro de Informatizações e Análises
 * -----------------------------------------------
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016)
 *         Responsabilidade da classe:
 *
 *
 */
@Controller
public class UsuarioController {
	private final ClienteLogado clienteLogado;
	private final Validator validator;
	private final ClienteDao dao;
	private final Cliente cliente;
	
	@Inject
	public UsuarioController(ClienteLogado clienteLogado, Validator validator, ClienteDao dao, Cliente cliente,
			Result result) {
		super();
		this.clienteLogado = clienteLogado;
		this.validator = validator;
		this.dao = dao;
		this.cliente = cliente;
		this.result = result;
	}
	@Deprecated
    UsuarioController() {
        this(null,null,null,null,null); 
    }

	@Inject
	private Result result;

	@Path("/LoginPage")
	public void signup() {

	}

	@Post("/Login")
	public void simples(Cliente cliente) {

		if (dao.existe(cliente)) {
			cliente = dao.dados(cliente);
			Carrinho carrinho = new Carrinho();
			CarrinhoDao carrinhoDao = new CarrinhoDao();
			List<Carrinho> list = new ArrayList<Carrinho>();
			list = carrinhoDao.listaCliente(cliente);
			for (Carrinho car : list) {
				carrinhoDao.remove(car);
			}

			clienteLogado.setCliente(cliente);
			clienteLogado.setCliente(cliente);
			//result.redirectTo(this).logar(cliente);
			result.redirectTo(IndexController.class).index();
		} else {
			validator.add(new I18nMessage("login", "login.invalido"));
			validator.onErrorUsePageOf(this).signup();

		}
	}

	

	@Path("/Logout")
	public void logout() {

		clienteLogado.setCliente(cliente);
		//result.redirectTo(this).logar(cliente);
		result.redirectTo(IndexController.class).index();

	}

	@Path("/Register")
	public void register() {

	}

	@Post("/NewUser")
	public void create(Cliente cliente) {

		ClienteDao dao = new ClienteDao();
		if (!dao.verificaUsuario(cliente)) {

			if (dao.adiciona(cliente)) {
				result.redirectTo(this).signup();
			} else {
				validator.add(new I18nMessage("cadastro", "cadastro.invalido"));
				validator.onErrorUsePageOf(this).register();
			}
		} else {
			validator.add(new I18nMessage("cadastro", "usuario.existe"));
			validator.onErrorUsePageOf(this).register();
		}

	}
}
