package br.com.hotmart.libs.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntityBase implements Serializable {

	private static final long serialVersionUID = 509248274115759968L;

	public abstract Long getId();

	public abstract void setId(Long id);

}
