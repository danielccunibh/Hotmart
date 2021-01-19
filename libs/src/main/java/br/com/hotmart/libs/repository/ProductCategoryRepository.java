package br.com.hotmart.libs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.libs.domain.ProductCategory;

public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {

}
