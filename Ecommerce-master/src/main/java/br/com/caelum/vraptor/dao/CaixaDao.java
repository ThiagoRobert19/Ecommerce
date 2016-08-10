package br.com.caelum.vraptor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.controller.Conexao;
import br.com.caelum.vraptor.model.Caixa;
import br.com.caelum.vraptor.model.Numero;
import br.com.caelum.vraptor.model.Numero_Produto;

public class CaixaDao {
	 EntityManager em;
	 public Caixa buscaCaixa(Caixa caixa) {
			em=Conexao.Connection();
			List<Caixa> list = new ArrayList<Caixa>();
		    list= em.createQuery("select c from Caixa c where c.codigo='"+caixa.getCodigo()+"'").getResultList();
		    em.getTransaction().commit(); 
		    em.close();
		    caixa= list.get(0);
		    return caixa;
		}
		
		public boolean atualizar(Caixa caixa) {
			boolean resp = false;
			em = Conexao.Connection();
			
			try {
				em.merge(caixa);
				resp = true;
			} catch (Exception e) {
				System.out.println("Erro \n" + e);
			}
			em.getTransaction().commit();
			em.close();
			return resp;
		}
}
