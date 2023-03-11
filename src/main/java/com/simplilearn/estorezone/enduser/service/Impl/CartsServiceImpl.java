package com.simplilearn.estorezone.enduser.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplilearn.estorezone.admin.entity.Products;
import com.simplilearn.estorezone.admin.repository.ProductsRepository;
import com.simplilearn.estorezone.enduser.entity.Carts;
import com.simplilearn.estorezone.enduser.repository.CartsRepository;
import com.simplilearn.estorezone.enduser.service.CartsService;

@Service
public class CartsServiceImpl implements CartsService{

	@Autowired
	CartsRepository cartsRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Override
	public Page<Carts> findAll(Pageable pageable) {
		return cartsRepository.findAll(pageable);
	}

	@Override
	public Optional<Carts> findById(int cartId) {
		return cartsRepository.findById(cartId);
	}

	@Override
	public Carts save(Carts carts) {
		return cartsRepository.save(carts);
	}

	@Override
	public boolean existsById(int cartId) {
		return cartsRepository.existsById(cartId);
	}

	@Override
	public void deleteById(int cartId) {
		cartsRepository.deleteById(cartId);
	}

	@Override
	public Page<Carts> findByUserId(int userId, Pageable pageable) {
		Page<Carts> carts = cartsRepository.findByUserId(userId, pageable);
		for (Carts cart : carts.getContent()) {
			Products product = productsRepository.findById(cart.getProductId()).get();
			cart.setProducts(product);
		}
		return carts;
	}

}
