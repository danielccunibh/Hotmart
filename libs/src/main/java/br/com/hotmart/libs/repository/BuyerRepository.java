package br.com.hotmart.libs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.libs.domain.Buyer;

public interface BuyerRepository extends PagingAndSortingRepository<Buyer, Long> {

}
