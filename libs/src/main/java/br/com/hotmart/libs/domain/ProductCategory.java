package br.com.hotmart.libs.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.envers.AuditTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true, exclude = { "products" })
@Entity
@Table(name = "product_category")
@AuditTable(value = "product_category_audit")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory extends AbstractEntity {

	private static final long serialVersionUID = 2261391281046111059L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	private List<Product> products = new ArrayList<>();

}
