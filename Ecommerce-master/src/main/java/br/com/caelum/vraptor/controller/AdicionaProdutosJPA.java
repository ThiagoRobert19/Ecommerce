/**
 * 
 */
package br.com.caelum.vraptor.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.model.Caixa;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.model.Promocao;

public class AdicionaProdutosJPA {
	public static void main(String[] args) {
	
		 Produto produto = new Produto(); 
		 Promocao promocao = new Promocao();
		 
		 Caixa caixa= new Caixa();
		 caixa.setCodigo(1);
		 caixa.setCredito(new BigDecimal(0));
		 caixa.setDinheiro(new BigDecimal(0));
		 
		 EntityManager em = Conexao.Connection(); 
		 em.persist(caixa);
		 em.getTransaction().commit();
		 em.close();
		 
		
		
	}
}
