package com.simplilearn.estorezone.admin.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private int userId;
	private String email;
	private String password;
	private String fullName;
	private String street;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String image;
	private Long contact;
	private Date addedOn;

}
