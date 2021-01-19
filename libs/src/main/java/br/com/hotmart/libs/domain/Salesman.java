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
@Table(name = "salesman")
public class Salesman extends AbstractCrudEntity {

	private static final long serialVersionUID = 8631439815775215931L;

	@Id
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

}
