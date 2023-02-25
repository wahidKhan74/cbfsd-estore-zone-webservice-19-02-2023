package com.simplilearn.estorezone.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.estorezone.admin.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}
