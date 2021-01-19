package br.com.hotmart.libs.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.AuditTable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(includeFieldNames = true)
@Entity
@Table(name = "salesman")
@AuditTable(value = "salesman_audit")
public class Salesman extends AbstractEntity {

	private static final long serialVersionUID = 8631439815775215931L;

}
