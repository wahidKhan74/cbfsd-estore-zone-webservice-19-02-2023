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
import com.simplilearn.estorezone.enduser.entity.Users;
import com.simplilearn.estorezone.enduser.service.UsersService;
import com.simplilearn.estorezone.exceptions.AlreadyExistException;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	UsersService userService;
	
	/**
	 * Get all or search by email.
	 * @param email
	 * @return
	 */
	@GetMapping("")
	public Page<Users> getAll(@RequestParam(value="email", required = false) String email,
			Pageable pageable) {
		if(email!=null) {
			return userService.findByEmailContaining(email,pageable);
		}
		return userService.findAll(pageable);
	}

	/**
	 * Get user user by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Users> getOne(@PathVariable("id") int id) {
		Optional<Users> userData = userService.findById(id);
		if(userData.isPresent()) {
			return userData;
		}
		throw new NotFoundException("Users data does exist with id '"+ id +"'");
	}
	
	/**
	 * Create user user.
	 * @param usersReq
	 * @return
	 */
	@PostMapping("")
	public Users save(@RequestBody() Users usersReq) {
		boolean eixts = userService.existsByEmail(usersReq.getEmail());
		if (!eixts) {
			return userService.save(usersReq);
		}
		throw new AlreadyExistException("User already exist with email '"+usersReq.getEmail() +"'");
	}
	

	/**
	 * Update Users
	 * @param Users
	 * @return
	 */
	@PutMapping("")
	public Users udpate(@RequestBody Users users) {
		boolean eixts = userService.existsById(users.getUserId());
		if (eixts) {
			return userService.save(users);
		}
		throw new NotFoundException("User does exist with id '"+ users.getUserId() +"'");
	}

	/**
	 * Delete one product by id
	 * @param productId
	 * @return 
	 */
	@DeleteMapping("/{id}")
	public ResponseDto deleteOne(@PathVariable("id") int id) {
		boolean eixts = userService.existsById(id);
		if (eixts) {
			userService.deleteById(id);
			return new ResponseDto("Success","User deleted", new Date(), null);
		}
		throw new NotFoundException("User does exist with id '"+ id +"'");
	}
}
