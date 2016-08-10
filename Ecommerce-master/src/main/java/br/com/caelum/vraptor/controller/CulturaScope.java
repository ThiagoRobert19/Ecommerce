/**
 * 
 */
package br.com.caelum.vraptor.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.model.ECultura;



/**
 * CIAware :: Center of Informatization and Analysis
 * -----------------------------------------------------
 *
 * @author Rebeka Cunha (cw005, 19/10/2015) 
 * Class responsibility: Class JAVABEAN of Culture settings.
 *
 *
 */
@SessionScoped
@Named("cultura")
public class CulturaScope implements Serializable {

	private Locale locale;
	private String nome;

	@Inject
	private HttpServletRequest request;

	public CulturaScope() {
		nome = "cultura";
		
		if (request == null) {
			locale = new Locale("pt", "BR");
//			locale = new Locale("en", "US");
		} else {
			locale = request.getLocale();
		}
		

	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale) {

		this.locale = locale;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	public ECultura getECultura() {

		String idCultura = this.getLocale().getLanguage().toLowerCase() + "_"
				+ this.getLocale().getCountry().toUpperCase();
		ECultura retorno = ECultura.valueOf(idCultura);

		if (retorno == null) {

			retorno = ECultura.pt_BR;
//			retorno = ECultura.en_US;
		}

		
		return retorno;
	
	}
}