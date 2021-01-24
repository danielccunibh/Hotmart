package br.com.hotmart.libs.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductValueDTO implements Serializable {

	private static final long serialVersionUID = 2105188883158601462L;

	private Long idProduto;

	private BigDecimal value;

	public ProductValueDTO(Long idProduto, BigDecimal value) {
		this.idProduto = idProduto;
		this.value = value;
	}

	/**
	 * Construtor utilizado para auxiliar no calculo de media de vendas por dia.
	 * 
	 * @param idProduto
	 * @param value
	 * @param createDate
	 */
	public ProductValueDTO(Long idProduto, BigDecimal value, Date createDate) {
		this.idProduto = idProduto;
		this.value = value.divide(
				BigDecimal.valueOf(Days.daysBetween(new DateTime(createDate), new DateTime(new Date())).getDays()));
	}

}