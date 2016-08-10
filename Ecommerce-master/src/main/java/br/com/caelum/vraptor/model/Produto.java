/**
 * 
 */
package br.com.caelum.vraptor.model;

/**
 * CIAware :: Centro de Informatizações e Análises
 * ----------------------------------------------- 
 *
 * @author Thiago Robert Prado Souza (Thiago Robert 19, 04/05/2016)
 * Responsabilidade da classe:
 *
 *
 */


import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer codigo;
    private String descricao;
    private BigDecimal valor_compra;
    private BigDecimal valor_venda;
    private String cor;
    private byte[] foto;
    private String categoria;
    private String subcategoria;
    private String fornecedor;
    private String comentario;
    
    
    
    

    
    public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }
    
    
    
    
  
	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(BigDecimal valor_compra) {
        this.valor_compra = valor_compra;
    }

    public BigDecimal getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(BigDecimal valor_venda) {
        this.valor_venda = valor_venda;
    }

   

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", descricao=" + descricao + ", valor_compra=" + valor_compra + ", valor_venda=" + valor_venda + ", cor=" + cor + ", foto=" + foto + ", categoria=" + categoria + ", subcategoria=" + subcategoria + ", fornecedor=" + fornecedor +  '}';
    }


   
}


