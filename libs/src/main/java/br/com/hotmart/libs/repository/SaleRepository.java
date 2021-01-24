package br.com.hotmart.libs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.libs.domain.Sale;
import br.com.hotmart.libs.dto.ProductValueDTO;

public interface SaleRepository extends PagingAndSortingRepository<Sale, Long> {

	@Query("SELECT new br.com.hotmart.libs.dto.ProductValueDTO(p.id, (SUM(s.evaluation) / COUNT(id))) FROM Sale s INNER JOIN s.product p WHERE s.dateSale BETWEEN :dataInitial AND :dataFinal AND p.id IN (:ids) GROUP BY p.id ")
	List<ProductValueDTO> findProductRatingMediaByIdProductAndDate(@Param("ids") List<Long> ids,
			@Param("dateInitial") Date dateInitial, @Param("dateFinal") Date dateFinal);

	@Query("SELECT new br.com.hotmart.libs.dto.ProductValueDTO(p.id, (SUM(s.amount), p.creationDate)) FROM Sale s INNER JOIN s.product p WHERE p.id IN (:ids) GROUP BY p.id, p.creationDate")
	List<ProductValueDTO> findSaleMediaProductByIdProduct(@Param("ids") List<Long> ids,
			@Param("currentDate") Date currentDate);

}
