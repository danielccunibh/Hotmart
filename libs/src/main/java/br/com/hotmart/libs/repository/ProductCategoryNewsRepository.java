package br.com.hotmart.libs.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.libs.domain.ProductCategoryNews;
import br.com.hotmart.libs.dto.ProductValueDTO;

public interface ProductCategoryNewsRepository extends PagingAndSortingRepository<ProductCategoryNews, Long> {

	@Query("SELECT pcn FROM ProductCategoryNews pcn INNER JOIN FETCH pcn.productCategory pc WHERE pcn.dateNews BETWEEN :dateInitial AND :dateEnd AND pc.id in (:keys)")
	@Cacheable
	public List<ProductCategoryNews> findByDateAndProductCategory(@Param(value = "dateInitial") Date dateInitial,
			@Param(value = "dateEnd") Date dateEnd, @Param(value = "keys") Set<Long> keys);

	@Query("SELECT new br.com.hotmart.libs.dto.ProductValueDTO(p.id, pcn.amount)) FROM ProductCategoryNews pcn INNER JOIN pcn.productCategory pc INNER JOIN  pc.producty p WHERE pcn.dateNews BETWEEN :dateInitial AND :dateEnd AND pc.id in (:ids)")
	@Cacheable
	public List<ProductValueDTO> findAmountNewsByIdProduct(@Param(value = "dateInitial") Date dateInitial,
			@Param(value = "dateEnd") Date dateEnd, @Param(value = "ids") List<Long> ids);

}
