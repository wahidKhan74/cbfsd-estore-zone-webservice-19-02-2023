package com.simplilearn.estorezone.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.estorezone.admin.entity.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer>{

	List<Products> findByProductTitleContaining(String title);

}
