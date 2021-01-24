package br.com.hotmart.challenge.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.hotmart.libs.domain.Product;
import br.com.hotmart.libs.domain.ProductCategory;
import br.com.hotmart.libs.dto.ProductDTO;
import br.com.hotmart.libs.exception.HotmartException;
import br.com.hotmart.libs.repository.ProductCategoryRepository;
import br.com.hotmart.libs.repository.ProductRepository;

@Service
@CacheConfig(cacheNames = "hotmart")
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private SaleService saleService;

	@Autowired
	private ProductCategoryNewsService productCategoryNewsService;

	@Cacheable
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Product save(Product p) {
		validateProduct(p);
		return productRepository.save(p);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public ResponseEntity<Product> update(long id, ProductDTO dto) {
		return productRepository.findById(id).map(p -> {

			p.setName(dto.getName());
			p.setDescription(dto.getDescription());

			fillProductCategory(dto, p);

			return ResponseEntity.ok().body(productRepository.save(p));

		}).orElse(ResponseEntity.notFound().build());
	}

	private void fillProductCategory(ProductDTO dto, Product p) {
		Optional<ProductCategory> productCategory = productCategoryRepository.findById(dto.getIdProductCategory());
		validateProductCategory(productCategory);
		p.setProductCategory(productCategory.get());
	}

	private void validateProductCategory(Optional<ProductCategory> productCategory) {
		if (!productCategory.isPresent()) {
			throw new HotmartException("Erro ao atualizar Produto. Categoria do produto é obrigatório.");
		}
	}

	private void validateProductCategory(ProductCategory productCategory) {
		validateProductCategory(Optional.of(productCategory));
	}

	private void validateProduct(Product p) {
		validateName(p);
		validateDescription(p);
		validateProductCategory(p.getProductCategory());
	}

	private void validateName(Product p) {
		if (StringUtils.isEmpty(p.getName())) {
			throw new HotmartException("Erro ao atualizar Produto. Nome é obrigatório.");
		}
	}

	private void validateDescription(Product p) {
		if (StringUtils.isEmpty(p.getDescription())) {
			throw new HotmartException("Erro ao atualizar Produto. Descrição é obrigatório.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		productRepository.deleteById(id);
	}

	@Cacheable
	public List<ProductDTO> findByCurrentDateAndSearchTerm(Date currentDate, String searchTerm, int page, int size) {
		Pageable pageable = getPageable(page, size);

		Page<Product> products = productRepository.findAll(pageable);

		List<ProductDTO> result = new ArrayList<>();

		products.get().forEach(p ->

		result.add(ProductDTO.builder().id(p.getId()).name(p.getName()).description(p.getDescription())
				.creationDate(p.getCreationDate()).score(p.getScore()).build()));

		return result;
	}

	private Pageable getPageable(int page, int size) {
		return PageRequest.of(page, size, Sort.by("score").descending().and(Sort.by("name")).ascending()
				.and(Sort.by("productCategory.name")).ascending());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateScoreProduct() {

		List<Product> products = (List<Product>) productRepository.findAll();

		List<Long> ids = products.stream().map(Product::getId).collect(Collectors.toList());

		Map<Long, BigDecimal> productRatingMediaMap = saleService
				.findProductRatingMediaByIdProductAndLastTwelveMonth(ids);

		Map<Long, BigDecimal> productSalesMediaMap = saleService.findSaleMediaProductByIdProduct(ids);

		Map<Long, BigDecimal> productAmountNewsMap = productCategoryNewsService.findAmountNewsByIdProduct(ids);

		calculateScoreProduct(products, productRatingMediaMap, productSalesMediaMap, productAmountNewsMap);

		productRepository.saveAll(products);
	}

	private void calculateScoreProduct(List<Product> products, Map<Long, BigDecimal> productRatingMediaMap,
			Map<Long, BigDecimal> productSalesMediaMap, Map<Long, BigDecimal> productAmountNewsMap) {
		products.forEach(p -> {
			BigDecimal value = BigDecimal.ZERO;

			if (productRatingMediaMap.containsKey(p.getId())) {
				value = value.add(productRatingMediaMap.get(p.getId()));
			}

			if (productRatingMediaMap.containsKey(p.getId())) {
				value = value.add(productSalesMediaMap.get(p.getId()));
			}

			if (productRatingMediaMap.containsKey(p.getId())) {
				value = value.add(productAmountNewsMap.get(p.getId()));
			}

			p.setScore(value.doubleValue());
		});
	}
}
