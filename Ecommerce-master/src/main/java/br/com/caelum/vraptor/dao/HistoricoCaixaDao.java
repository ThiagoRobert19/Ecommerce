package br.com.caelum.vraptor.dao;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.controller.Conexao;
import br.com.caelum.vraptor.model.HistoricoCaixa;

public class HistoricoCaixaDao {
	EntityManager em;
	
	public boolean adiciona(HistoricoCaixa historico) {
		boolean resp = false;
		em = Conexao.Connection();
		try {
			em.persist(historico);
			resp = true;
		} catch (Exception e) {
			System.out.println("Erro");
		}

		em.getTransaction().commit();
		em.close();
		return resp;
	}
}
