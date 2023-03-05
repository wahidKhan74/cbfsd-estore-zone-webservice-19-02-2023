package com.simplilearn.estorezone.admin.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.estorezone.admin.entity.Admins;

@Repository
public interface AdminsRepository extends JpaRepository<Admins, Integer>{

	Page<Admins> findByEmailContaining(String email, Pageable pageable);

	boolean existsByEmail(String email);


}
