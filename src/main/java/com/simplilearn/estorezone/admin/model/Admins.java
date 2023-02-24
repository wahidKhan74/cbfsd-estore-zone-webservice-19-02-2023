package com.simplilearn.estorezone.admin.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Admins Model.
 * @author wahid khan
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class Admins {
	
	private int adminId;
	private String email;
	private String password;
	private String fullName;
	private int loginType;
	private Date addedOn;
	
}
