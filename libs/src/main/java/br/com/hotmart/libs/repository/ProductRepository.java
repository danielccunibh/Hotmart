package br.com.hotmart.libs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.libs.domain.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
