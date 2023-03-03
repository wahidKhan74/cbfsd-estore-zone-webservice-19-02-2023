package com.simplilearn.estorezone.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.simplilearn.estorezone.admin.entity.Admins;
import com.simplilearn.estorezone.admin.entity.Categories;
import com.simplilearn.estorezone.admin.repository.AdminsRepository;
import com.simplilearn.estorezone.exceptions.AlreadyExistException;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController
@RequestMapping("/admins")
public class AdminsController {
	
	@Autowired
	AdminsRepository adminsRepository;
	
	BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * Get all or search by email.
	 * @param email
	 * @return
	 */
	@GetMapping("")
	public List<Admins> getAll(@RequestParam(value="email", required = false) String email) {
		if(email!=null) {
			return adminsRepository.findByEmailContaining(email);
		}
		return adminsRepository.findAll();
	}

	/**
	 * Get admin user by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Admins> getOne(@PathVariable("id") int id) {
		Optional<Admins> adminData = adminsRepository.findById(id);
		if(adminData.isPresent()) {
			return adminData;
		}
		throw new NotFoundException("Admins data does exist with id '"+ id +"'");
	}
	
	/**
	 * Create admin user.
	 * @param adminsReq
	 * @return
	 */
	@PostMapping("")
	public Admins save(@RequestBody() Admins adminsReq) {
		boolean eixts = adminsRepository.existsByEmail(adminsReq.getEmail());
		if (!eixts) {
			passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(adminsReq.getPassword());
			adminsReq.setPassword(encodedPassword);
			return adminsRepository.save(adminsReq);
		}
		throw new AlreadyExistException("Admin user already exist with email '"+adminsReq.getEmail() +"'");
	}
	

	/**
	 * Update Admins
	 * @param Admins
	 * @return
	 */
	@PutMapping("")
	public Admins udpate(@RequestBody Admins admins) {
		boolean eixts = adminsRepository.existsById(admins.getAdminId());
		if (eixts) {
			passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(admins.getPassword());
			admins.setPassword(encodedPassword);
			return adminsRepository.save(admins);
		}
		throw new NotFoundException("Admin user does exist with id '"+ admins.getAdminId() +"'");
	}

	/**
	 * Delete one product by id
	 * @param productId
	 * @return 
	 */
	@DeleteMapping("/{id}")
	public ResponseDto deleteOne(@PathVariable("id") int id) {
		boolean eixts = adminsRepository.existsById(id);
		if (eixts) {
			adminsRepository.deleteById(id);
			return new ResponseDto("Success","Admin user deleted", new Date(), null);
		}
		throw new NotFoundException("Admin user does exist with id '"+ id +"'");
	}
}
