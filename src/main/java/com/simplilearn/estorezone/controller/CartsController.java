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
import com.simplilearn.estorezone.enduser.entity.Carts;
import com.simplilearn.estorezone.enduser.service.CartsService;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController()
@RequestMapping("/carts")
public class CartsController {

	@Autowired
	CartsService cartsService;
	
	/**
	 * Get all carts.
	 * @param title
	 * @return
	 */
	@GetMapping("")
	public Page<Carts> getAll(@RequestParam(value="userId", required = false) String userId, Pageable pageable) {
		if (userId != null) {
			return cartsService.findByUserId(Integer.parseInt(userId), pageable);
		}
		return cartsService.findAll(pageable);
	}

	/**
	 * Get one cart by id
	 * @param cartId
	 * @return
	 */
	@GetMapping("/{cartId}")
	public Optional<Carts> getOne(@PathVariable("cartId") int cartId) {
		Optional<Carts> cartData=  cartsService.findById(cartId);
		if(cartData.isPresent()) {
			return cartData;
		}
		throw new NotFoundException("Carts does exist with cartId '"+ cartId +"'");
	}

	/**
	 * Save Carts.
	 * @param carts
	 * @return
	 */
	@PostMapping("")
	public Carts save(@RequestBody Carts carts) {
		return cartsService.save(carts);
	}

	/**
	 * Update carts
	 * @param carts
	 * @return
	 */
	@PutMapping("")
	public Carts udpate(@RequestBody Carts carts) {
		boolean eixts = cartsService.existsById(carts.getCartId());
		if (eixts) {
			return cartsService.save(carts);
		}
		throw new NotFoundException("Carts does exist with cartId '"+ carts.getCartId() +"'");
	}

	/**
	 * Delete one cart by id
	 * @param cartId
	 * @return Optional<Carts>
	 */
	@DeleteMapping("/{cartId}")
	public ResponseDto deleteOne(@PathVariable("cartId") int cartId) {
		boolean eixts = cartsService.existsById(cartId);
		if (eixts) {
			cartsService.deleteById(cartId);
			return new ResponseDto("Success","Cart deleted", new Date(), null);
		}
		throw new NotFoundException("Carts does exist with cartId '"+ cartId +"'");
	}
}
