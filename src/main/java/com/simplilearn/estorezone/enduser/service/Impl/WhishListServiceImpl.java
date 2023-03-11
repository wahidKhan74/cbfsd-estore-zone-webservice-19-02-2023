package com.simplilearn.estorezone.enduser.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.simplilearn.estorezone.admin.entity.Products;
import com.simplilearn.estorezone.admin.repository.ProductsRepository;
import com.simplilearn.estorezone.enduser.entity.WhishList;
import com.simplilearn.estorezone.enduser.repository.WhishListRepository;
import com.simplilearn.estorezone.enduser.service.WhishListService;

@Service
public class WhishListServiceImpl implements WhishListService{

	@Autowired
	WhishListRepository whishListsRepository;
	
	@Autowired
	ProductsRepository productsRepository;
	
	@Override
	public Page<WhishList> findAll(Pageable pageable) {
		return whishListsRepository.findAll(pageable);
	}

	@Override
	public Optional<WhishList> findById(int whishListId) {
		return whishListsRepository.findById(whishListId);
	}

	@Override
	public WhishList save(WhishList whishLists) {
		return whishListsRepository.save(whishLists);
	}

	@Override
	public boolean existsById(int whishListId) {
		return whishListsRepository.existsById(whishListId);
	}

	@Override
	public void deleteById(int whishListId) {
		whishListsRepository.deleteById(whishListId);
	}

	@Override
	public Page<WhishList> findByUserId(int userId, Pageable pageable) {
		Page<WhishList> whishLists = whishListsRepository.findByUserId(userId, pageable);
		for (WhishList whishList : whishLists.getContent()) {
			Products product = productsRepository.findById(whishList.getProductId()).get();
			whishList.setProducts(product);
		}
		return whishLists;
	}

}
