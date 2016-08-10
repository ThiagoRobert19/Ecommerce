/**
 * 
 */
package br.com.caelum.vraptor.dao;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016)
 * Responsabilidade da classe:
 *
 *
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import br.com.caelum.vraptor.controller.Conexao;
import br.com.caelum.vraptor.model.Carrinho;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.util.JPAUtil;

public class ClienteDao {
	EntityManager em;
	

	public boolean adiciona(Cliente cliente) {
		em=Conexao.Connection();
		boolean resp = false;
		try {
			em.persist(cliente);
			resp = true;
		} catch (Exception e) {
			System.out.println("Erro");
		}
		em.getTransaction().commit();
		em.close();
		return resp;
	}
	public Cliente busca(Cliente cliente){
		em=Conexao.Connection();
		Cliente cli = new Cliente();
        cli = em.find(Cliente.class, cliente.getId());
        em.getTransaction().commit();
        em.close();
        return cli;
	}

	public boolean verificaUsuario(Cliente cliente) {
		em=Conexao.Connection();
		return !em.createQuery("select c from Cliente c where c.usuario = "
			+ ":usuario or c.email = :email or c.cpf = :cpf", Cliente.class)
			.setParameter("usuario", cliente.getUsuario())
			.setParameter("email", cliente.getEmail())
			.setParameter("cpf", cliente.getCpf())
			.getResultList().isEmpty();
		
		
	}
	public Cliente pega(Cliente cliente) {
		em=Conexao.Connection();
	    List<Cliente> cliente_list = new ArrayList<Cliente>();
	    cliente_list = em.createQuery("select c from Cliente c where c.usuario='"+cliente.getUsuario()+"'").getResultList();
	    em.getTransaction().commit();
	    em.close();
	    cliente =(Cliente) cliente_list.get(0);
	    return cliente;
	}
	
	
	
	public boolean existe(Cliente cliente) {
		em=Conexao.Connection();
		return !em.createQuery("select c from Cliente c where c.usuario = "
			+ ":usuario and c.senha = :senha", Cliente.class)
			.setParameter("usuario", cliente.getUsuario())
			.setParameter("senha", cliente.getSenha())
			.getResultList().isEmpty();
	}
	
	public Cliente dados (Cliente cliente){
		List<Cliente> lista = new ArrayList<Cliente>();
		lista = em.createQuery(
				"select c from Cliente c where c.usuario='"
						+ cliente.getUsuario() + "'").getResultList();
		em.getTransaction().commit();
		em.close();

		for (int linha = 0; linha < lista.size(); linha++) {
			cliente = lista.get(linha);

		}
		return cliente;
	}

	public boolean Atualizar(Cliente cliente){
		em=Conexao.Connection();
		boolean resp = false;
		try {
			em.merge(cliente);
			resp = true;
		} catch (Exception e) {
			System.out.println("Erro");
		}
		em.getTransaction().commit();
		em.close();
		return resp;
	}
	
	
}
