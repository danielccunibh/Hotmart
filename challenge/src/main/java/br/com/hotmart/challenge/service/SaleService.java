package br.com.hotmart.challenge.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.hotmart.libs.dto.ProductValueDTO;
import br.com.hotmart.libs.repository.SaleRepository;
import br.com.hotmart.libs.util.DateUtil;

@Service
@CacheConfig(cacheNames = "hotmart")
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	@Cacheable
	public Map<Long, BigDecimal> findProductRatingMediaByIdProductAndLastTwelveMonth(List<Long> ids) {

		Date dateInitial = DateUtil.getDate000000(DateUtil.addDate(new Date(), Calendar.MONTH, -12));

		List<ProductValueDTO> result = saleRepository.findProductRatingMediaByIdProductAndDate(ids, dateInitial, new Date());

		return convertMapByIdProduto(result);
	}

	@Cacheable
	public Map<Long, BigDecimal> findSaleMediaProductByIdProduct(List<Long> ids) {
		List<ProductValueDTO> result = saleRepository.findSaleMediaProductByIdProduct(ids, new Date());

		return convertMapByIdProduto(result);
	}

	private Map<Long, BigDecimal> convertMapByIdProduto(List<ProductValueDTO> result) {
		return result.stream().collect(Collectors.toMap(ProductValueDTO::getIdProduto, ProductValueDTO::getValue));
	}

}
