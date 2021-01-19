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
@Table(name = "buyer")
@AuditTable(value = "buyer_audit")
public class Buyer extends AbstractEntity {

	private static final long serialVersionUID = 3342223448955513258L;

}
