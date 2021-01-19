package br.com.hotmart.libs.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true, of = { "id" })
@ToString(includeFieldNames = true)
@Entity
@Table(name = "sale")
public class Sale extends AbstractEntity {

	private static final long serialVersionUID = 6816320349136158641L;

	@Id
	private Long id;

	private Salesman salesman;

	private Product product;

	private Buyer buyer;
}
