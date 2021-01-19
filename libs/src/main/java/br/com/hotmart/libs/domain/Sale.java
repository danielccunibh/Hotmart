package br.com.hotmart.libs.domain;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true)
@Entity
@Table(name = "sale")
@AuditTable(value = "sale_audit")
@NoArgsConstructor
@AllArgsConstructor
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

}
