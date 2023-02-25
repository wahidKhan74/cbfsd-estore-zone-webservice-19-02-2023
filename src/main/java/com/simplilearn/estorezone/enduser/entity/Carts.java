package com.simplilearn.estorezone.enduser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carts {
	
	private int cartId;
	private int productId;
	private int userId;
	private int quantity;
	
}
