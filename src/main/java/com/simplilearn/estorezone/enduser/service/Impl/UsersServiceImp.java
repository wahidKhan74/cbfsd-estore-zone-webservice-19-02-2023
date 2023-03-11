package com.simplilearn.estorezone.enduser.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.simplilearn.estorezone.enduser.entity.Users;
import com.simplilearn.estorezone.enduser.entity.UsersRepository;
import com.simplilearn.estorezone.enduser.service.UsersService;

@Service
public class UsersServiceImp implements UsersService{

	@Autowired
	UsersRepository usersRepository;
	
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Page<Users> findByEmailContaining(String email,  Pageable pageable) {
		return usersRepository.findByEmailContaining(email, pageable);
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return usersRepository.findAll(pageable);
	}

	@Override
	public Optional<Users> findById(int id) {
		return usersRepository.findById(id);
	}

	@Override
	public boolean existsByEmail(String email) {
		return usersRepository.existsByEmail(email);
	}

	@Override
	public Users save(Users usersReq) {
		passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(usersReq.getPassword());
		usersReq.setPassword(encodedPassword);
		return usersRepository.save(usersReq);
	}

	@Override
	public boolean existsById(int userId) {
		return usersRepository.existsById(userId);
	}

	@Override
	public void deleteById(int id) {
		usersRepository.deleteById(id);
	}

}
