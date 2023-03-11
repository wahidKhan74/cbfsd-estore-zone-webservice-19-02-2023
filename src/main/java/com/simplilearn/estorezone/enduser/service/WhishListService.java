package com.simplilearn.estorezone.enduser.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplilearn.estorezone.enduser.entity.WhishList;


public interface WhishListService {
	
	Page<WhishList> findAll(Pageable pageable);

	Optional<WhishList> findById(int whishListId);

	WhishList save(WhishList WhishList);

	boolean existsById(int whishListId);

	void deleteById(int whishListId);

	Page<WhishList> findByUserId(int userId, Pageable pageable);
}
