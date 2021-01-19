package br.com.hotmart.libs.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Audited
public abstract class AbstractEntity extends AbstractEntityBase {

	private static final long serialVersionUID = -8136560437524043493L;

	@Column(name = "name", nullable = false)
	protected String name;

}
