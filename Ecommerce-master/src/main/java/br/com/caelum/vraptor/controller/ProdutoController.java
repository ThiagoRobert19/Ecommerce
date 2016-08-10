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
import br.com.caelum.vraptor.dao.ProdutoDao;
import br.com.caelum.vraptor.model.Numero;
import br.com.caelum.vraptor.model.Numero_Produto;
import br.com.caelum.vraptor.model.Produto;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016)
 * Responsabilidade da classe:
 *
 *
 */

@Controller
public class ProdutoController {
	 
	private final Result result;

	@Inject
	public ProdutoController(Result result) {
		super();
		this.result = result;
	}
	@Deprecated
    ProdutoController() {
        this(null); 
    }


	@Path("/CategoriaProduto")
	public void products(String categoria){
		if(categoria.equals("MASCULINO") || categoria.equals("FEMININO") || categoria.equals("INFANTIL")){
			List<Produto> produto = new ArrayList<Produto>();
	        ProdutoDao dao = new ProdutoDao();
	        produto = dao.listaCategoria(categoria);
	        result.include("total", Total.getTotal());
	        result.include("listaProdutos", produto);
		}else{
			List<Produto> produto = new ArrayList<Produto>();
	        ProdutoDao dao = new ProdutoDao();
	        produto = dao.listaSubCategoria(categoria);
	        result.include("total", Total.getTotal());
	        result.include("listaProdutos", produto);
		}
	}
	
	
	@Post("/Single")
	public void single(Produto produto){
		
		List<Numero> numero = new ArrayList<Numero>();
		List<Numero_Produto> numero_produto = new ArrayList<Numero_Produto>();
		List<Produto> list_produto = new ArrayList<Produto>();
        ProdutoDao dao = new ProdutoDao();
        produto=dao.busca(produto);
        list_produto.add(produto);
        numero_produto = dao.listaCodigo(produto);
        
        for(Numero_Produto number: numero_produto){
        	numero.add(dao.buscaNumero(number));
        }
        Numero num = new Numero();
	    
	    for (int linha = 0; linha < numero.size(); linha++) {
	    	num = numero.get(linha);
           System.out.println(num.toString()); 
        }
	    result.include("comentario",produto.getComentario());
	    result.include("total", Total.getTotal());
        result.include("listaProdutos", list_produto);
        result.include("listaNumeros", numero);
	    
	}
}
