package com.simplilearn.estorezone.enduser.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simplilearn.estorezone.enduser.entity.Users;

public interface UsersService {

	Page<Users> findByEmailContaining(String email, Pageable pageable);

	Page<Users> findAll(Pageable pageable);

	Optional<Users> findById(int id);

	boolean existsByEmail(String email);

	Users save(Users adminsReq);

	boolean existsById(int adminId);

	void deleteById(int id);
	
}
