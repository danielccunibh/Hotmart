package br.com.hotmart.libs.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString(includeFieldNames = true)
public class NewsApiResponse {

	private Integer totalResults;

	private String status;

	private String code;

	private String message;

}