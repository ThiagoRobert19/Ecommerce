package br.com.caelum.vraptor.model;

public class Cartao {
	private Integer id;
	private String numeroCartao;
	private String nomeCartao;
	private String mesCartao;
	private String anoCartao;
	private String codigoCartao;
	private String opcaoCartao;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	public String getMesCartao() {
		return mesCartao;
	}
	public void setMesCartao(String mesCartao) {
		this.mesCartao = mesCartao;
	}
	public String getAnoCartao() {
		return anoCartao;
	}
	public void setAnoCartao(String anoCartao) {
		this.anoCartao = anoCartao;
	}
	public String getCodigoCartao() {
		return codigoCartao;
	}
	public void setCodigoCartao(String codigoCartao) {
		this.codigoCartao = codigoCartao;
	}
	public String getOpcaoCartao() {
		return opcaoCartao;
	}
	public void setOpcaoCartao(String opcaoCartao) {
		this.opcaoCartao = opcaoCartao;
	}
	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", nomeCartao=" + nomeCartao + ", mesCartao="
				+ mesCartao + ", anoCartao=" + anoCartao + ", codigoCartao=" + codigoCartao + ", opcaoCartao="
				+ opcaoCartao + "]";
	}
	
}
