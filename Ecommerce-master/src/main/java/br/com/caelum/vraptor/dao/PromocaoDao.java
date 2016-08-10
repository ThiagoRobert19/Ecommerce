package br.com.caelum.vraptor.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.controller.Conexao;
import br.com.caelum.vraptor.model.Cliente;
import br.com.caelum.vraptor.model.Produto;
import br.com.caelum.vraptor.model.Promocao;

public class PromocaoDao {
    EntityManager em;
    
     public Promocao busca(Promocao promocao){
    	 em=Conexao.Connection();
    	 promocao.setPromocao(new BigDecimal(0));
    	    List<Promocao> promo = new ArrayList<Promocao>();
    	    promo = em.createQuery("select p from Promocao p where p.codigo='"+promocao.getCodigo()+"'").getResultList();
    	    em.getTransaction().commit();
    	    em.close();
    	    if(promo.size()>0){
    	    	promocao=promo.get(0);
    	    }
    	    
    	    return promocao;
    
	}

}
