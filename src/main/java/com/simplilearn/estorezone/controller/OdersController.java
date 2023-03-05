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
import com.simplilearn.estorezone.admin.entity.Orders;
import com.simplilearn.estorezone.admin.service.OrdersService;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController()
@RequestMapping("/orders")
public class OdersController {

	@Autowired
	OrdersService ordersService;
	
	/**
	 * Get all Orders or Search Orders by title like operation.
	 * @param title
	 * @return
	 */
	@GetMapping("")
	public Page<Orders> getAll(@RequestParam(value="status", required =false) String status, Pageable pageable) {
		if (status != null && status != "") {
			return ordersService.findByOrderStatusContaining(status, pageable);
		}
		return ordersService.findAll(pageable);
	}

	/**
	 * Get one order by id
	 * @param orderId
	 * @return
	 */
	@GetMapping("/{orderId}")
	// @RequestMapping(value="/{productId}", method = RequestMethod.GET)
	public Optional<Orders> getOne(@PathVariable("productId") int orderId) {
		Optional<Orders> productData=  ordersService.findById(orderId);
		if(productData.isPresent()) {
			return productData;
		}
		throw new NotFoundException("Order does exist with orderId '"+ orderId +"'");
	}

	/**
	 * Save order.
	 * @param order
	 * @return
	 */
	@PostMapping("")
	public Orders save(@RequestBody Orders order) {
		return ordersService.save(order);
	}

	/**
	 * Update order
	 * @param order
	 * @return
	 */
	@PutMapping("")
	public Orders udpate(@RequestBody Orders orders) {
		boolean eixts = ordersService.existsById(orders.getOrderId());
		if (eixts) {
			return ordersService.save(orders);
		}
		throw new NotFoundException("Order does exist with orderId '"+ orders.getOrderId() +"'");
	}

	/**
	 * Delete one order by id
	 * @param orderId
	 * @return Optional<Products>
	 */
	@DeleteMapping("/{orderId}")
	public ResponseDto deleteOne(@PathVariable("orderId") int orderId) {
		boolean eixts = ordersService.existsById(orderId);
		if (eixts) {
			ordersService.deleteById(orderId);
			return new ResponseDto("Success","Product deleted", new Date(), null);
		}
		throw new NotFoundException("Order does exist with productId '"+ orderId +"'");
	}
}
