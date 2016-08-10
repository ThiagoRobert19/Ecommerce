/**
 * 
 */
package br.com.caelum.vraptor.controller;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.util.JPAUtil;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 06/05/2016)
 * Responsabilidade da classe:
 *
 *
 */

/**
 * CIAware :: Center of Informatization and Analysis
 * -----------------------------------------------------
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 06/05/2016) 
 * Class responsibility: 
 *
 *
 */

public class Conexao {
	static EntityManager em= null;
    static Conexao connAccess = null;

    public static EntityManager Connection(){
        if ((em == null) || (!em.isOpen())) {
        	em = new JPAUtil().criaEntityManager();
    		em.getTransaction().begin();
            
        }

        return em;
    }
}
