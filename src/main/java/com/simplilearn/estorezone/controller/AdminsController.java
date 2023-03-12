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

import com.simplilearn.estorezone.admin.dto.LoginReqDto;
import com.simplilearn.estorezone.admin.dto.ResponseDto;
import com.simplilearn.estorezone.admin.entity.Admins;
import com.simplilearn.estorezone.admin.service.AdminsService;
import com.simplilearn.estorezone.exceptions.AlreadyExistException;
import com.simplilearn.estorezone.exceptions.NotFoundException;

@RestController
@RequestMapping("/admins")
public class AdminsController {
	
	@Autowired
	AdminsService adminService;
	
	/**
	 * Get all or search by email.
	 * @param email
	 * @return
	 */
	@GetMapping("")
	public Page<Admins> getAll(@RequestParam(value="email", required = false) String email,
			Pageable pageable) {
		if(email!=null) {
			return adminService.findByEmailContaining(email,pageable);
		}
		return adminService.findAll(pageable);
	}

	/**
	 * Get admin user by id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public Optional<Admins> getOne(@PathVariable("id") int id) {
		Optional<Admins> adminData = adminService.findById(id);
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
		boolean eixts = adminService.existsByEmail(adminsReq.getEmail());
		if (!eixts) {
			return adminService.save(adminsReq);
		}
		throw new AlreadyExistException("Admin user already exist with email '"+adminsReq.getEmail() +"'");
	}
	
	/**
	 * Validate Login for admin user.
	 * @param adminsReq
	 * @return
	 */
	@PostMapping("/login")
	public ResponseDto save(@RequestBody() LoginReqDto loginReqDto) {
		boolean eixts = adminService.existsByEmail(loginReqDto.getEmail());
		if (eixts) {
			boolean match = adminService.login(loginReqDto);
			if(match) {
				return new ResponseDto("Success","Admin login successfull", new Date(), loginReqDto.getEmail()); 
			}else {
				throw new NotFoundException("Invalid password, password mismatch error.");
			}
		}
		throw new NotFoundException("Admin user does exist with email '"+loginReqDto.getEmail() +"'");
	}
	

	/**
	 * Update Admins
	 * @param Admins
	 * @return
	 */
	@PutMapping("")
	public Admins udpate(@RequestBody Admins admins) {
		boolean eixts = adminService.existsById(admins.getAdminId());
		if (eixts) {
			return adminService.save(admins);
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
		boolean eixts = adminService.existsById(id);
		if (eixts) {
			adminService.deleteById(id);
			return new ResponseDto("Success","Admin user deleted", new Date(), null);
		}
		throw new NotFoundException("Admin user does exist with id '"+ id +"'");
	}
}
