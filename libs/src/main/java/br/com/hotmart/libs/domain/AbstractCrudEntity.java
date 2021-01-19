package br.com.hotmart.libs.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractCrudEntity extends AbstractEntity {
	
	private static final long serialVersionUID = -8136560437524043493L;

	abstract String getName();
    
    abstract void setName(String name);
}
