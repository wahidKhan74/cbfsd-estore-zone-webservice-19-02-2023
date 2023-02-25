package com.simplilearn.estorezone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.simplilearn.estorezone.admin.entity.Admins;
import com.simplilearn.estorezone.enduser.entity.WhishList;

@SpringBootApplication
public class EstoreZoneWebservice {

	public static void main(String[] args) {
		SpringApplication.run(EstoreZoneWebservice.class, args);
	}

}
