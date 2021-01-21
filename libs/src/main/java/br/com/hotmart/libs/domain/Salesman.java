package br.com.hotmart.libs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true)
@Entity
@Table(name = "salesman")
@AuditTable(value = "salesman_audit")
@Data
public class Salesman extends AbstractEntityBase {

	private static final long serialVersionUID = 8631439815775215931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

}
