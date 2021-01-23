package br.com.hotmart.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hotmart.libs.domain.ProductCategory;
import br.com.hotmart.libs.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	public List<ProductCategory> findAll() {
		return (List<ProductCategory>) productCategoryRepository.findAll();
	}
}
