package com.simplilearn.estorezone.enduser.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplilearn.estorezone.enduser.entity.Carts;


public interface CartsService {
	
	Page<Carts> findAll(Pageable pageable);

	Optional<Carts> findById(int cartId);

	Carts save(Carts Carts);

	boolean existsById(int cartId);

	void deleteById(int cartId);

	Page<Carts> findByUserId(int userId, Pageable pageable);
}
