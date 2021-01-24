package br.com.hotmart.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.hotmart.libs.domain.ProductCategory;
import br.com.hotmart.libs.repository.ProductCategoryRepository;

@Service
@CacheConfig(cacheNames = "hotmart")
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Cacheable
	public List<ProductCategory> findAll() {
		return (List<ProductCategory>) productCategoryRepository.findAll();
	}
}
