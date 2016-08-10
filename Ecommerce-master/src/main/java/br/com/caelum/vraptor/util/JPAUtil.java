/**
 * 
 */
package br.com.caelum.vraptor.util;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016)
 * Responsabilidade da classe:
 *
 *
 */

/**
 * CIAware :: Center of Informatization and Analysis
 * -----------------------------------------------------
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016) 
 * Class responsibility: 
 *
 *
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	public static EntityManager criaEntityManager()
        {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("default");
		return factory.createEntityManager();
	}
}

