/**
 * 
 */
package br.com.caelum.vraptor.dao;

import java.sql.Date;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016)
 * Responsabilidade da classe:
 *
 *
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.controller.Conexao;
import br.com.caelum.vraptor.model.Carrinho;
import br.com.caelum.vraptor.model.Numero;
import br.com.caelum.vraptor.model.Numero_Produto;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.util.JPAUtil;

public class ProdutoDao {

    EntityManager em;
    
    

@SuppressWarnings("unchecked")
    public List<Produto> lista() {
		em=Conexao.Connection();
        List<Produto> produto = new ArrayList<Produto>();
        produto = em.createQuery("select p from Produto p").getResultList();
        em.getTransaction().commit();
        em.close();
        return produto;
    }
public List<Produto> listaCategoria(String categoria) {
	em=Conexao.Connection();
    List<Produto> produto = new ArrayList<Produto>();
    produto = em.createQuery("select p from Produto p where p.categoria='"+categoria+"'").getResultList();
    em.getTransaction().commit();
    em.close();
    return produto;
}
public List<Produto> listaSubCategoria(String subcategoria) {
	em=Conexao.Connection();
    List<Produto> produto = new ArrayList<Produto>();
    produto = em.createQuery("select p from Produto p where p.subcategoria='"+subcategoria+"'").getResultList();
    em.getTransaction().commit();
    em.close();
    return produto;
}
public List<Numero_Produto> listaCodigo(Produto produto) {
	em=Conexao.Connection();
    List<Numero_Produto> numero_produto = new ArrayList<Numero_Produto>();
    numero_produto = em.createQuery("select n from Numero_Produto n where n.codigoProduto='"+produto.getCodigo()+"'").getResultList();
    em.getTransaction().commit();
    em.close();
    for(Numero_Produto num : numero_produto){
    	if(num.getQuantidade()==0){
    		numero_produto.remove(num);
    	}
    		
    }
    return numero_produto;
}
public Numero_Produto NumeroProduto(int idNumero,int codigoProduto) {
	em=Conexao.Connection();
    List<Numero_Produto> numero_produto = new ArrayList<Numero_Produto>();
    numero_produto = em.createQuery("select n from Numero_Produto n where n.idNumero='"+idNumero+"' and "
    		+ "n.codigoProduto='"+codigoProduto+"'").getResultList();
    em.getTransaction().commit();
    em.close();
    Numero_Produto  np = new Numero_Produto();
    np = numero_produto.get(0);
    return np;
}
public Numero buscaNumeroNumero(Numero numero) {
	em=Conexao.Connection();
    List<Numero> numero_produto = new ArrayList<Numero>();
    numero_produto = em.createQuery("select n from Numero n where n.numero='"+numero.getNumero()+"'").getResultList();
    em.getTransaction().commit();
    em.close();
    numero= numero_produto.get(0);
    return numero;
}
    public Produto busca(Produto produto) {
    	em=Conexao.Connection();
        Produto prod = new Produto();
        prod = em.find(Produto.class, produto.getId());
        em.getTransaction().commit();
        em.close();
        return prod;
    }
	
	public Numero buscaNumero(Numero_Produto number) {
		em=Conexao.Connection();
		List<Numero> numero = new ArrayList<Numero>();
	    numero = em.createQuery("select n from Numero n where n.id='"+number.getIdNumero()+"' order by numero").getResultList();
	    em.getTransaction().commit(); 
	    em.close();
	    Numero num = new Numero();
	    
	    for (int linha = 0; linha < numero.size(); linha++) {
	    	num = numero.get(linha);
            
        }
	    
	    return num;
	}
	
	public boolean atualizarQuantidade(Numero_Produto numProd) {
		boolean resp = false;
		em = Conexao.Connection();
		
		try {
			em.merge(numProd);
			resp = true;
		} catch (Exception e) {
			System.out.println("Erro \n" + e);
		}
		em.getTransaction().commit();
		em.close();
		return resp;
	}
    
    
}

