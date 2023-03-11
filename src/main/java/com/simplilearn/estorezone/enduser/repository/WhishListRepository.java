package com.simplilearn.estorezone.enduser.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.estorezone.enduser.entity.WhishList;

@Repository
public interface WhishListRepository extends JpaRepository<WhishList, Integer>{

	Page<WhishList> findByUserId(int userId, Pageable pageable);

}
