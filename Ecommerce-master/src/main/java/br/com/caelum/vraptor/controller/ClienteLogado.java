/**
 * 
 */
package br.com.caelum.vraptor.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.vraptor.model.Cliente;

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
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016) Class
 *         responsibility:
 *
 *
 */
@SessionScoped
@Named
public class ClienteLogado implements Serializable {

	private Cliente usuario;

	public Cliente getCliente() {
		return usuario;
	}

	public void setCliente(Cliente usuario) {
		this.usuario = usuario;
	}

}
