package br.com.hotmart.challenge.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.hotmart.libs.domain.ProductCategory;
import br.com.hotmart.libs.domain.ProductCategoryNews;
import br.com.hotmart.libs.dto.ProductValueDTO;
import br.com.hotmart.libs.repository.ProductCategoryNewsRepository;
import br.com.hotmart.libs.util.DateUtil;

@Service
public class ProductCategoryNewsService {

	private static final Logger log = LoggerFactory.getLogger(ProductCategoryNewsService.class);

	@Autowired
	private ProductCategoryNewsRepository productCategoryNewsRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateProductCategoryNews(Map<Long, Integer> map) {
		log.debug("Iniciando atualização de ProductCategoryNews");

		Date dateInitial = DateUtil.getDate000000(new Date());
		Date dateEnd = DateUtil.getDate235959(new Date());

		List<ProductCategoryNews> result = productCategoryNewsRepository.findByDateAndProductCategory(dateInitial,
				dateEnd, map.keySet());

		if (result.isEmpty()) {
			saveAll(map);
		} else {
			updateAllProductCategoryNews(result, map);
		}

		log.debug("Finalizando atualização de ProductCategoryNews");
	}

	private void updateAllProductCategoryNews(List<ProductCategoryNews> result, Map<Long, Integer> map) {

		List<ProductCategoryNews> entitiesForUpdate = new ArrayList<ProductCategoryNews>();

		result.forEach(r -> {
			if (map.containsKey(r.getId()) && map.get(r.getId()) > r.getAmount()) {
				Integer amount = r.getAmount() + (map.get(r.getId()) - r.getAmount());
				log.debug("Atualizando quantidade de notícias da categoria de id {} de {} para {}",
						r.getProductCategory().getId(), r.getAmount(), amount);
				r.setAmount(amount);
				entitiesForUpdate.add(r);
			}
			if (!map.containsKey(r.getId())) {
				entitiesForUpdate.add(createProductCategoryNews(map, r.getId()));
			}
		});

		log.debug("Quantidade total de ProductCategoryNews atualizadas: {}", entitiesForUpdate.size());

		productCategoryNewsRepository.saveAll(entitiesForUpdate);
	}

	private void saveAll(Map<Long, Integer> map) {
		List<ProductCategoryNews> entitiesForSave = new ArrayList<ProductCategoryNews>();

		createProductCategoryNews(map, entitiesForSave);

		productCategoryNewsRepository.saveAll(entitiesForSave);

		log.debug("Quantidade total de ProductCategoryNews criadas: {}", entitiesForSave.size());
	}

	private void createProductCategoryNews(Map<Long, Integer> map, List<ProductCategoryNews> entities) {
		map.keySet().forEach(k -> {
			ProductCategoryNews pcn = createProductCategoryNews(map, k);
			entities.add(pcn);
		});
	}

	private ProductCategoryNews createProductCategoryNews(Map<Long, Integer> map, Long k) {
		ProductCategory pc = new ProductCategory();
		pc.setId(k);
		ProductCategoryNews pcn = new ProductCategoryNews();
		pcn.setAmount(map.get(k));
		pcn.setDateNews(new Date());
		pcn.setProductCategory(pc);

		log.debug("Criando ProductCategoryNews para categoria id: {} com quantidade de {} noticias.", k, map.get(k));

		return pcn;
	}

	@Cacheable
	public Map<Long, BigDecimal> findAmountNewsByIdProduct(List<Long> ids) {
		Date date = DateUtil.addDate(new Date(), Calendar.DAY_OF_MONTH, 1);

		List<ProductValueDTO> result = productCategoryNewsRepository
				.findAmountNewsByIdProduct(DateUtil.getDate000000(date), DateUtil.getDate235959(date), ids);

		return result.stream().collect(Collectors.toMap(ProductValueDTO::getIdProduto, ProductValueDTO::getValue));
	}

}
