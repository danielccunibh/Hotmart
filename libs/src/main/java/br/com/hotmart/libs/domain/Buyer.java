package br.com.hotmart.libs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.envers.AuditTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true)
@Entity
@Table(name = "buyer")
@AuditTable(value = "buyer_audit")
@Data
public class Buyer extends AbstractEntity {

	private static final long serialVersionUID = 3342223448955513258L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;
}
