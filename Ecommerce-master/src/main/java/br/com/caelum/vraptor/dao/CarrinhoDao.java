/**
 * 
 */
package br.com.caelum.vraptor.dao;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 09/05/2016)
 * Responsabilidade da classe:
 *
 *
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.*;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.controller.Conexao;
import br.com.caelum.vraptor.model.Carrinho;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.model.Numero;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.util.JPAUtil;

public class CarrinhoDao {
	EntityManager em;
	private ResultSet rs;

	public boolean adiciona(Carrinho carrinho) {
		boolean resp = false;
		em = Conexao.Connection();
		try {
			em.persist(carrinho);
			resp = true;
		} catch (Exception e) {
			System.out.println("Erro");
		}

		em.getTransaction().commit();
		em.close();
		return resp;
	}

	public boolean remove(Carrinho carrinho) {
		boolean resp = false;
		em = Conexao.Connection();
		try {
			em.remove(em.find(Carrinho.class, carrinho.getId()));
			resp = true;
		} catch (Exception e) {
			System.out.println("Erro");
		}
		em.getTransaction().commit();
		em.close();
		return resp;
	}

	public boolean existe(Carrinho carrinho) {
		boolean resp = false;
		em = Conexao.Connection();
		List<Carrinho> lista = new ArrayList<Carrinho>();
		lista = em.createQuery(
				"select c from Carrinho c where c.idproduto='"
						+ carrinho.getIdproduto() + "' and c.numero='"
						+ carrinho.getNumero()+ "'").getResultList();
		em.getTransaction().commit();
		em.close();
		if (lista.size() > 0) {
			resp = true;
			System.out.println("Produto ja existe no carrinho");
		}
		return resp;
	}
	
	public Carrinho dados(Carrinho carrinho) {

		em = Conexao.Connection();
		List<Carrinho> lista = new ArrayList<Carrinho>();
		lista = em.createQuery(
				"select c from Carrinho c where c.idproduto='"
						+ carrinho.getIdproduto() + "' and c.numero='"
						+ carrinho.getNumero()+ "'").getResultList();
		em.getTransaction().commit();
		em.close();

		for (int linha = 0; linha < lista.size(); linha++) {
			carrinho = lista.get(linha);

		}
		return carrinho;
	}
	
	public List<Carrinho> listaCliente(Cliente cliente) {
		em=Conexao.Connection();
	    List<Carrinho> lista = new ArrayList<Carrinho>();
	    lista = em.createQuery("select c from Carrinho c where c.idcliente='"+cliente.getId()+"'").getResultList();
	    em.getTransaction().commit();
	    em.close();
	    return lista;
	}

	public boolean atualizar(Carrinho carrinho) {
		Date data = new java.sql.Date(new java.util.Date().getTime());
		carrinho.setData(data);
		boolean resp = false;
		em = Conexao.Connection();
		
		try {
			em.merge(carrinho);

		} catch (Exception e) {
			System.out.println("Erro \n" + e);
		}
		em.getTransaction().commit();
		em.close();
		return resp;
	}
}
