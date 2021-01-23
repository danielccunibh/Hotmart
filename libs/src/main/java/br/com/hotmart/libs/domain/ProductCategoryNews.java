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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true)
@Entity
@Table(name = "product_category_news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryNews extends AbstractEntityBase {

	private static final long serialVersionUID = 4353481086450410825L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_news", nullable = false)
	private Date dateNews;

	@ManyToOne
	@JoinColumn(name = "id_product_category", referencedColumnName = "id")
	private ProductCategory productCategory;

	@Column(name = "amount", nullable = false)
	private Integer amount;
}
