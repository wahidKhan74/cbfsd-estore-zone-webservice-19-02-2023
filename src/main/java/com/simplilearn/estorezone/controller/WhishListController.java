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
import com.simplilearn.estorezone.enduser.entity.WhishList;
import com.simplilearn.estorezone.enduser.service.WhishListService;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController()
@RequestMapping("/whishlist")
public class WhishListController {

	@Autowired
	WhishListService whishListsService;
	
	/**
	 * Get all whishLists.
	 * @param title
	 * @return
	 */
	@GetMapping("")
	public Page<WhishList> getAll(@RequestParam(value="userId", required = false) String userId, Pageable pageable) {
		if (userId != null) {
			return whishListsService.findByUserId(Integer.parseInt(userId), pageable);
		}
		return whishListsService.findAll(pageable);
	}

	/**
	 * Get one whishList by id
	 * @param whishListId
	 * @return
	 */
	@GetMapping("/{whishListId}")
	public Optional<WhishList> getOne(@PathVariable("whishListId") int whishListId) {
		Optional<WhishList> whishListData=  whishListsService.findById(whishListId);
		if(whishListData.isPresent()) {
			return whishListData;
		}
		throw new NotFoundException("WhishList does exist with Id '"+ whishListId +"'");
	}

	/**
	 * Save WhishList.
	 * @param whishLists
	 * @return
	 */
	@PostMapping("")
	public WhishList save(@RequestBody WhishList whishLists) {
		return whishListsService.save(whishLists);
	}

	/**
	 * Update whishLists
	 * @param whishLists
	 * @return
	 */
	@PutMapping("")
	public WhishList udpate(@RequestBody WhishList whishLists) {
		boolean eixts = whishListsService.existsById(whishLists.getWishlistId());
		if (eixts) {
			return whishListsService.save(whishLists);
		}
		throw new NotFoundException("WhishList does exist with id '"+ whishLists.getWishlistId() +"'");
	}

	/**
	 * Delete one whishList by id
	 * @param whishListId
	 * @return Optional<WhishList>
	 */
	@DeleteMapping("/{whishListId}")
	public ResponseDto deleteOne(@PathVariable("whishListId") int whishListId) {
		boolean eixts = whishListsService.existsById(whishListId);
		if (eixts) {
			whishListsService.deleteById(whishListId);
			return new ResponseDto("Success","Cart deleted", new Date(), null);
		}
		throw new NotFoundException("WhishList does exist with whishListId '"+ whishListId +"'");
	}
}
