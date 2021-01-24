package br.com.hotmart.libs.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.com.hotmart.libs.domain.ProductCategoryNews;

public interface ProductCategoryNewsRepository extends PagingAndSortingRepository<ProductCategoryNews, Long> {

	@Query("SELECT pcn FROM ProductCategoryNews pcn INNER JOIN FETCH pcn.productCategory pc WHERE pcn.dateNews BETWEEN :dateInitial AND :dateEnd AND pc.id in (:keys)")
	@Cacheable
	public List<ProductCategoryNews> findByDateAndProductCategory(@Param(value = "dateInitial") Date dateInitial,
			@Param(value = "dateEnd") Date dateEnd, @Param(value = "keys") Set<Long> keys);

}
