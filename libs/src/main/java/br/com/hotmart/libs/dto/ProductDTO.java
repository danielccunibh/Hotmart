package br.com.hotmart.libs.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false, of = { "id" })
@ToString(includeFieldNames = true, exclude = { "nameProductCategory", "idProductCategory" })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 7969040427181177656L;

	private Long id;

	private String name;

	private String description;

	private Date creationDate;

	private int score;

	private String nameProductCategory;

	private Long idProductCategory;

}