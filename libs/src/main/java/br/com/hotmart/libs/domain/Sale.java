package br.com.hotmart.libs.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.NotAudited;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(includeFieldNames = true)
@Entity
@Table(name = "sale")
@AuditTable(value = "sale_audit")
public class Sale extends AbstractEntityBase {

	private static final long serialVersionUID = 6816320349136158641L;

	@ManyToOne
	@JoinColumn(name = "id_salesman", referencedColumnName = "id")
	@NotAudited
	private Salesman salesman;

	@ManyToOne
	@JoinColumn(name = "id_product", referencedColumnName = "id")
	@NotAudited
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_buyer", referencedColumnName = "id")
	@NotAudited
	private Buyer buyer;
}
