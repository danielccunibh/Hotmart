package br.com.hotmart.challenge.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.hotmart.libs.NewsApiStatusEnum;
import br.com.hotmart.libs.domain.ProductCategory;
import br.com.hotmart.libs.exception.HotmartException;
import br.com.hotmart.libs.response.NewsApiResponse;

@Service
public class NewsApiService {

	private static final Logger log = LoggerFactory.getLogger(NewsApiService.class);

	@Value("${hotmart.challenge.news-api.key}")
	private String key;

	@Value("${hotmart.challenge.news-api.url}")
	private String url;

	@Value("${hotmart.challenge.news-api.param}")
	private String param;

	@Autowired
	private ProductCategoryService productCategoryService;

	@Autowired
	private ProductCategoryNewsService productCategoryNewsService;

	public void updateNewsApi() {

		Map<Long, Integer> map = new HashMap<Long, Integer>();

		List<ProductCategory> productCategories = productCategoryService.findAll();

		log.debug("Quantidade de categorias: {}", productCategories.size());

		fillAmountByProductCategoryMap(map, productCategories);

		productCategoryNewsService.updateProductCategoryNews(map);

	}

	private void fillAmountByProductCategoryMap(Map<Long, Integer> amountByProductCategoryMap,
			List<ProductCategory> productCategories) {
		try {
			for (ProductCategory pc : productCategories) {

				RestTemplate restTemplate = new RestTemplate();
				NewsApiResponse resp = restTemplate.getForObject(url + key + param + pc.getName(),
						NewsApiResponse.class);

				validateNewsApiStatus(resp);

				log.debug("Quantidade de noticias encontradas para categoria {}: {}", pc.getName(),
						resp.getTotalResults());

				amountByProductCategoryMap.put(pc.getId(), resp.getTotalResults());
			}
		} catch (HttpClientErrorException e) {
			log.error("Quantidade de maxima de requisições diárias ({}/dia) realizadas.", 100);
			throw new HotmartException(e);
		}
	}

	private void validateNewsApiStatus(NewsApiResponse response) {
		if (NewsApiStatusEnum.ERROR.getStatus().equalsIgnoreCase(response.getStatus())) {
			HotmartException e = new HotmartException("Erro ao conectar no serviço News API: " + response.toString());
			log.error(e.getMessage());
			throw e;
		}
	}

}
