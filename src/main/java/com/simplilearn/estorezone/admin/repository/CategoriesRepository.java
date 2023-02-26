package com.simplilearn.estorezone.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.estorezone.admin.entity.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>{

	List<Categories> findByCategoryNameContaining(String title);

	boolean existsByCategoryName(String categoryName);

}
