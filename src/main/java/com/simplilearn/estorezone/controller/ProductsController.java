package com.simplilearn.estorezone.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.estorezone.admin.dto.ResponseDto;
import com.simplilearn.estorezone.admin.entity.Products;
import com.simplilearn.estorezone.admin.service.ProductsService;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController()
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	ProductsService productService;
	
	/**
	 * Get all products or Search product by title like operation.
	 * @param title
	 * @return
	 */
	@GetMapping("")
	public Page<Products> getAll(@RequestParam(value="title", required =false) String title, Pageable pageable) {
		if (title != null && title != "") {
			return productService.findByProductTitleContaining(title, pageable);
		}
		return productService.findAll(pageable);
	}

	/**
	 * Get one product by id
	 * @param productId
	 * @return
	 */
	@GetMapping("/{productId}")
	// @RequestMapping(value="/{productId}", method = RequestMethod.GET)
	public Optional<Products> getOne(@PathVariable("productId") int productId) {
		Optional<Products> productData=  productService.findById(productId);
		if(productData.isPresent()) {
			return productData;
		}
		throw new NotFoundException("Products does exist with productId '"+ productId +"'");
	}

	/**
	 * Save Products.
	 * @param products
	 * @return
	 */
	@PostMapping("")
	public Products save(@RequestBody Products products) {
		return productService.save(products);
	}

	/**
	 * Update products
	 * @param products
	 * @return
	 */
	@PutMapping("")
	public Products udpate(@RequestBody Products products) {
		boolean eixts = productService.existsById(products.getProductId());
		if (eixts) {
			return productService.save(products);
		}
		throw new NotFoundException("Products does exist with productId '"+ products.getProductId() +"'");
	}

	/**
	 * Delete one product by id
	 * @param productId
	 * @return Optional<Products>
	 */
	@DeleteMapping("/{productId}")
	public ResponseDto deleteOne(@PathVariable("productId") int productId) {
		boolean eixts = productService.existsById(productId);
		if (eixts) {
			productService.deleteById(productId);
			return new ResponseDto("Success","Product deleted", new Date(), null);
		}
		throw new NotFoundException("Products does exist with productId '"+ productId +"'");
	}
}
