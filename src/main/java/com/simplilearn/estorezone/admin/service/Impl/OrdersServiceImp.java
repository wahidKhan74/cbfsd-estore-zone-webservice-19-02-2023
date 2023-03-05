package com.simplilearn.estorezone.admin.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.simplilearn.estorezone.admin.entity.Orders;
import com.simplilearn.estorezone.admin.repository.OrdersRepository;
import com.simplilearn.estorezone.admin.service.OrdersService;

@Service
public class OrdersServiceImp implements OrdersService{

	@Autowired
	OrdersRepository ordersRepository;
	
	@Override
	public Page<Orders> findByOrderStatusContaining(String orderStatus, Pageable pageable) {
		return ordersRepository.findByOrderStatusContaining(orderStatus,pageable);
	}

	@Override
	public Page<Orders> findAll( Pageable pageable) {
		return ordersRepository.findAll(pageable);
	}

	@Override
	public Optional<Orders> findById(int productId) {
		return ordersRepository.findById(productId);
	}

	@Override
	public Orders save(Orders order) {
		return ordersRepository.save(order);
	}

	@Override
	public boolean existsById(int orderId) {
		return ordersRepository.existsById(orderId);
	}

	@Override
	public void deleteById(int orderId) {
		ordersRepository.deleteById(orderId);
	}

}
