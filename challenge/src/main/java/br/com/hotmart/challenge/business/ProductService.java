package br.com.hotmart.challenge.business;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

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
}
