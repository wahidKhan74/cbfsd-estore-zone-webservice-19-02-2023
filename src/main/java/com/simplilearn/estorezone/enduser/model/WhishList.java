package com.simplilearn.estorezone.enduser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WhishList {
	
	private int wishlistId;
	private int productId;
	private int userId;
}
