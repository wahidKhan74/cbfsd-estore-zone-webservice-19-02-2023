package com.simplilearn.estorezone.admin.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplilearn.estorezone.admin.dto.LoginReqDto;
import com.simplilearn.estorezone.admin.entity.Admins;


public interface AdminsService {

	Page<Admins> findByEmailContaining(String email, Pageable pageable);

	Page<Admins> findAll(Pageable pageable);

	Optional<Admins> findById(int id);

	boolean existsByEmail(String email);

	Admins save(Admins adminsReq);

	boolean existsById(int adminId);

	void deleteById(int id);

	boolean login(LoginReqDto loginReqDto);
	
}
