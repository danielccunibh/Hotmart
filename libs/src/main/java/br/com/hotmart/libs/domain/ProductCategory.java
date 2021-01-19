package br.com.hotmart.libs.domain;

import javax.persistence.Column;
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
@Table(name = "product_category")
public class ProductCategory extends AbstractCrudEntity {

	private static final long serialVersionUID = 2261391281046111059L;

	@Id
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

}
