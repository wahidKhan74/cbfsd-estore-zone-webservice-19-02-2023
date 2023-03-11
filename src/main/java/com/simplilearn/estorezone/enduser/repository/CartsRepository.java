package com.simplilearn.estorezone.enduser.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.estorezone.enduser.entity.Carts;

@Repository
public interface CartsRepository extends JpaRepository<Carts, Integer>{

	Page<Carts> findByUserId(int userId, Pageable pageable);

}
