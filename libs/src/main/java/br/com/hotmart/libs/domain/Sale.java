package br.com.hotmart.libs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.NotAudited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true)
@Entity
@Table(name = "sale")
@AuditTable(value = "sale_audit")
@Data
public class Sale extends AbstractEntityBase {

	private static final long serialVersionUID = 6816320349136158641L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

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

	@Column(name = "amount", nullable = false)
	private Integer amount;

	@Column(name = "evaluation")
	private Integer evaluation;

	@Column(name = "date_sale", nullable = false)
	private Date dateSale;
}
