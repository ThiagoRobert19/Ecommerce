/**
 * 
 */
package br.com.caelum.vraptor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


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
public class Numero_Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer codigoProduto;
	
	
	private Integer idNumero;

	private Integer quantidade;
	
	
	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCodigoProduto() {
		return codigoProduto;
	}


	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}


	public Integer getIdNumero() {
		return idNumero;
	}


	public void setIdNumero(Integer idNumero) {
		this.idNumero = idNumero;
	}
	
	
}
