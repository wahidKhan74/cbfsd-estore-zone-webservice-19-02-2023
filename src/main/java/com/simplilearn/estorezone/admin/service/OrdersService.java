package com.simplilearn.estorezone.admin.service;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.simplilearn.estorezone.admin.entity.Orders;

public interface OrdersService {

	Page<Orders> findByOrderStatusContaining(String orderStatus, Pageable pageable);

	Page<Orders> findAll(Pageable pageable);

	Optional<Orders> findById(int orderId);

	Orders save(Orders orders);

	boolean existsById(int orderId);

	void deleteById(int orderId);

}
