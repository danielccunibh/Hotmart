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
@Table(name = "product_category")
@AuditTable(value = "product_category_audit")
public class ProductCategory extends AbstractEntity {

	private static final long serialVersionUID = 2261391281046111059L;

}
