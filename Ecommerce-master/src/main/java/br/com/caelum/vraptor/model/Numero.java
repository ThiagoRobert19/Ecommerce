/**
 * 
 */
package br.com.caelum.vraptor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 05/05/2016)
 * Responsabilidade da classe:
 *
 *
 */

@Entity
public class Numero {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer numero;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Numero [id=" + id + ", numero=" + numero + "]";
	}
	
	
}
