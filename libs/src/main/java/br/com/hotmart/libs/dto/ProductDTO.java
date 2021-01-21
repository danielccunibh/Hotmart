package br.com.hotmart.libs.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true)
@Data
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 7969040427181177656L;

	private Long id;

	private String name;

	private String description;

	private Date creationDate;

	private String nameProductCategory;

	private Long idProductCategory;

}