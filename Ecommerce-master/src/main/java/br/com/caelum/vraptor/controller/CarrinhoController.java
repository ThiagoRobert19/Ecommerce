/**
 * 
 */
package br.com.caelum.vraptor.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.CarrinhoDao;
import br.com.caelum.vraptor.dao.ClienteDao;
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.dao.PromocaoDao;
import br.com.caelum.vraptor.model.Carrinho;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.model.Numero;
import br.com.caelum.vraptor.model.Numero_Produto;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.model.Promocao;
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
public class CarrinhoController {
	
	private final Validator validator;
	
	private final ClienteLogado clienteLogado;
	
	private final Result result;

	@Inject
	public CarrinhoController(Validator validator, ClienteLogado clienteLogado, Result result) {
		super();
		this.validator = validator;
		this.clienteLogado = clienteLogado;
		this.result = result;
	}
	
	@Deprecated
    CarrinhoController() {
        this(null,null,null); 
    }
	
	@Path("/FecharCompra")
	public void close() {
		List<Cliente> list = new ArrayList<Cliente>();
		Cliente cliente= clienteLogado.getCliente();
		System.out.println(cliente.toString());
		ClienteDao dao = new ClienteDao();
		cliente= dao.pega(cliente);
		list.add(cliente);
		
		Promocao promocao = new Promocao();
		promocao.setCodigo(4011956);
		PromocaoDao promocaoDao = new PromocaoDao();
		
		promocao= promocaoDao.busca(promocao);
		BigDecimal total = Total.getTotal();
		BigDecimal porcento =promocao.getPromocao();
		BigDecimal desconto = total.divide(porcento);
		BigDecimal totalDesconto = total.subtract(desconto);
		
		List<Carrinho> lista = new ArrayList<Carrinho>();
		CarrinhoDao carrinhoDao = new CarrinhoDao();
		lista = carrinhoDao.listaCliente(cliente);
		
		double pega= totalDesconto.doubleValue();
		DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        String valor1 = df.format(pega/2);
        String valor2 = df.format(pega/3);
        String valor3 = df.format(pega/4);
        String valor4 = df.format(pega/5);
        String valor5 = df.format(pega/6);
        String valor6 = df.format(pega/7);
        String valor7 = df.format(pega/8);
        String valor8 = df.format(pega/9);
        String valor9 = df.format(pega/10);
        List<String> datas = new ArrayList<String>();
		String hoje;
        Date d = new Date();
        Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.YEAR, c.get(Calendar.YEAR));
		hoje=new SimpleDateFormat("yy").format(c.getTime());
		datas.add(hoje);
		for(int i=0;i<12;i++){
			c.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
			hoje=new SimpleDateFormat("yy").format(c.getTime());
			datas.add(hoje);
		}
		result.include("ano", datas);
        result.include("parcelado1", "2 x " + valor1);
        result.include("parcelado2", "3 x " + valor2);
        result.include("parcelado3", "4 x " + valor3);
        result.include("parcelado4", "5 x " + valor4);
        result.include("parcelado5", "6 x " + valor5);
        result.include("parcelado6", "7 x " + valor6);
        result.include("parcelado7", "8 x " + valor7);
        result.include("parcelado8", "9 x " + valor8);
        result.include("parcelado9", "10 x "+ valor9);
       
		result.include("listaCarrinho", lista);
		result.include("listaCliente", list);
		result.include("total", totalDesconto);
	}
	@Path("/ZerarCarrinho")
	public void zerar() {
		Cliente cliente= clienteLogado.getCliente();
		System.out.println(cliente.toString());
		ClienteDao dao = new ClienteDao();
		cliente= dao.pega(cliente);
		Carrinho carrinho = new Carrinho();
		CarrinhoDao carrinhoDao = new CarrinhoDao();
		List<Carrinho> list = new ArrayList<Carrinho>();
		list = carrinhoDao.listaCliente(cliente);
		for (Carrinho car : list) {
			carrinhoDao.remove(car);
		}
		Total.setTotal(new BigDecimal(0));
		result.include("total", Total.getTotal());
		result.redirectTo(IndexController.class).index();
	}
	
	

	@Path("/Checkout")
	public void checkout(String cliente) {
		if (cliente.equals("")) {
			cliente = "";
		}
		ClienteDao clienteDao = new ClienteDao();
		CarrinhoDao carrinhoDao = new CarrinhoDao();
		Cliente cli = new Cliente();
		cli.setUsuario(cliente);
		cli = clienteDao.pega(cli);
		System.out.println(cli.toString());

		List<Carrinho> lista = new ArrayList<Carrinho>();
		lista = carrinhoDao.listaCliente(cli);
		int tamanho = lista.size();
		BigDecimal total = new BigDecimal(0);
		for (Carrinho car : lista) {
			total = total.add(car.getTotal());
			System.out.println(total + "Este é o total#######################################");
		}
		Promocao promocao = new Promocao();
		promocao.setCodigo(4011956);
		PromocaoDao promocaoDao = new PromocaoDao();
		
		promocao= promocaoDao.busca(promocao);
		BigDecimal porcento = new BigDecimal(10);
		BigDecimal desconto = total.divide(porcento);
		BigDecimal totalDesconto = total.subtract(desconto);
		System.out.println(promocao.getPromocao());
		result.include("total", Total.getTotal());
		result.include("porcento", promocao.getPromocao());
		result.include("desconto", desconto);
		result.include("totalDesconto", totalDesconto);
		result.include("total", total);
		result.include("listaCarrinho", lista);
		result.include("tamanho", tamanho);
	}

	@Post("/AdicionarCarrinho")
	public void adicionar(Produto produto, Numero numero, Numero_Produto numero_produto, Cliente cliente) {
		BigDecimal total = new BigDecimal(0);

		if (!cliente.getUsuario().equals("")) {
			Carrinho carrinho = new Carrinho();
			Carrinho carrinho2 = new Carrinho();
			CarrinhoDao carrinhoDao = new CarrinhoDao();
			ClienteDao clienteDao = new ClienteDao();
			ProdutoDao produtoDao = new ProdutoDao();
			produto = produtoDao.busca(produto);

			cliente = clienteDao.pega(cliente);
			carrinho.setCor(produto.getCor());
			carrinho.setIdcliente(cliente.getId());
			carrinho.setNumero(numero.getNumero());
			carrinho.setIdproduto(produto.getId());
			carrinho.setProduto(produto.getDescricao());
			carrinho.setValor(produto.getValor_venda());
			carrinho.setQuantidade(numero_produto.getQuantidade());
			carrinho.setData(new Date());
			BigDecimal temp = new BigDecimal(numero_produto.getQuantidade());
			BigDecimal resultado = produto.getValor_venda().multiply(temp);
			carrinho.setTotal(resultado);

			ProdutoDao prodDao = new ProdutoDao();
			produto = prodDao.busca(produto);
			if (carrinhoDao.existe(carrinho)) {
				carrinho2 = carrinhoDao.dados(carrinho);
				carrinho.setQuantidade(carrinho2.getQuantidade() + carrinho.getQuantidade());
				carrinho.setTotal(carrinho.getTotal().add(carrinho2.getTotal()));
				carrinho.setId(carrinho2.getId());
				carrinhoDao.atualizar(carrinho);
				List<Carrinho> lista = new ArrayList<Carrinho>();
				lista = carrinhoDao.listaCliente(cliente);
				int tamanho = lista.size();

				for (Carrinho car : lista) {
					total = total.add(car.getTotal());
					System.out.println(total + "Este é o total#######################################");
				}
				Total.setTotal(total);
				result.include("total", Total.getTotal());
				
				result.include("alteracao", "carrinho.alterado");
				result.redirectTo(IndexController.class).index();

			} else {
				if (carrinhoDao.adiciona(carrinho)) {
					List<Carrinho> lista = new ArrayList<Carrinho>();
					lista = carrinhoDao.listaCliente(cliente);
					int tamanho = lista.size();

					for (Carrinho car : lista) {
						total = total.add(car.getTotal());
						System.out.println(total + "Este é o total#######################################");
					}
					Total.setTotal(total);
					result.include("total", Total.getTotal());
					
					result.include("adicao", "carrinho.adicionado");
					result.redirectTo(IndexController.class).index();

				} else {
					List<Carrinho> lista = new ArrayList<Carrinho>();
					lista = carrinhoDao.listaCliente(cliente);
					int tamanho = lista.size();

					for (Carrinho car : lista) {
						total = total.add(car.getTotal());
						System.out.println(total + "Este é o total#######################################");
					}
					Total.setTotal(total);
					result.include("total", Total.getTotal());
					
					result.include("excecao", "carrinho.excecao");
					result.redirectTo(IndexController.class).index();
				}

			}
			
		} else {
			validator.add(new I18nMessage("fazer", "fazer.login"));
			validator.onErrorUsePageOf(UsuarioController.class).signup();
		}

	}

}
