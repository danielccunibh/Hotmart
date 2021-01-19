package br.com.hotmart.libs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.libs.domain.Sale;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {

}
