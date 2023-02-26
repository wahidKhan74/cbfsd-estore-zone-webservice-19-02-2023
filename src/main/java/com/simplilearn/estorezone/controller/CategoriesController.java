package com.simplilearn.estorezone.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.simplilearn.estorezone.admin.entity.Categories;
import com.simplilearn.estorezone.admin.entity.Products;
import com.simplilearn.estorezone.admin.repository.CategoriesRepository;
import com.simplilearn.estorezone.exceptions.AlreadyExistException;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	
	@Autowired
	CategoriesRepository categoriesRepository;
	
	/**
	 * Get all categories or Search categories by title like operation.
	 * @param title
	 * @return
	 */
	@GetMapping("")
	public List<Categories> getAll(@RequestParam(value="title", required =false) String title) {
		if (title != null && title != "") {
			return categoriesRepository.findByCategoryNameContaining(title);
		}
		return categoriesRepository.findAll();
	}
	

	/**
	 * Get one categories by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Categories> getOne(@PathVariable("id") int id) {
		Optional<Categories> category =  categoriesRepository.findById(id);
		if(category.isPresent()) {
			return category;
		}
		throw new NotFoundException("Category does exist with id '"+ id +"'");
	}
	
	/**
	 * Save categories.
	 * @param categories
	 * @return
	 */
	@PostMapping("")
	public Categories save(@RequestBody Categories categories) {
		boolean eixts = categoriesRepository.existsByCategoryName(categories.getCategoryName());
		if (!eixts) {
			return categoriesRepository.save(categories);
		}
		throw new AlreadyExistException("Category already exist with name '"+ categories.getCategoryName() +"'");
	}
	
	/**
	 * Update Categories
	 * @param Categories
	 * @return
	 */
	@PutMapping("")
	public Categories udpate(@RequestBody Categories categories) {
		boolean eixts = categoriesRepository.existsById(categories.getCategoryId());
		if (eixts) {
			return categoriesRepository.save(categories);
		}
		throw new NotFoundException("Category does exist with id '"+ categories.getCategoryId() +"'");
	}

	/**
	 * Delete one product by id
	 * @param productId
	 * @return Optional<Products>
	 */
	@DeleteMapping("/{id}")
	public ResponseDto deleteOne(@PathVariable("id") int id) {
		boolean eixts = categoriesRepository.existsById(id);
		if (eixts) {
			categoriesRepository.deleteById(id);
			return new ResponseDto("Success","Categories deleted", new Date(), null);
		}
		throw new NotFoundException("Category does exist with id '"+ id +"'");
	}
	
	
}
