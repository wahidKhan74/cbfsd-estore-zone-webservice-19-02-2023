package com.simplilearn.estorezone.admin.service;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.simplilearn.estorezone.admin.entity.Products;

public interface ProductsService {

	Page<Products> findByProductTitleContaining(String title, Pageable pageable);

	Page<Products> findAll(Pageable pageable);

	Optional<Products> findById(int productId);

	Products save(Products products);

	boolean existsById(int productId);

	void deleteById(int productId);

}
