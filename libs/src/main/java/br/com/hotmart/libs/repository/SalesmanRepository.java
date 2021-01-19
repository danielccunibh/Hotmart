package br.com.hotmart.libs.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.hotmart.libs.domain.Salesman;

public interface SalesmanRepository extends PagingAndSortingRepository<Salesman, Long> {

}
