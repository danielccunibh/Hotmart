package br.com.hotmart.challenge.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.challenge.service.ProductService;
import br.com.hotmart.libs.domain.Product;
import br.com.hotmart.libs.dto.ProductDTO;

@RestController
@RequestMapping({ "/products" })
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public List<Product> findAll() {
		return service.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return service.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = { "/findByCurrentDateAndSearchTerm/{currentDate, searchTerm}" })
	public ResponseEntity<List<ProductDTO>> findByCurrentDateAndSearchTerm(@PathVariable Date currentDate,
			String searchTerm) {
		return service.findByCurrentDateAndSearchTerm(currentDate, searchTerm);
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return service.save(product);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") long id, @RequestBody ProductDTO dto) {
		return service.update(id, dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> remove(@PathVariable("id") long id) {
		return service.findById(id).map(p -> {
			service.delete(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
