package br.com.caelum.vraptor.controller;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.util.JPAUtil;

public class Total {
	static BigDecimal total = new BigDecimal(0);

	public static BigDecimal getTotal() {
		return total;
	}

	public static void setTotal(BigDecimal total) {
		Total.total = total;
	}
	
	
    
}
