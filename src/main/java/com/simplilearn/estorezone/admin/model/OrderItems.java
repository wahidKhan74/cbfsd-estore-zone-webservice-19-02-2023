package com.simplilearn.estorezone.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {

	private int orderItemId;
	private int orderId;
	private int productId;
	private String productTitle;
	private String productDescription;
	private String productCode;
	private String productImg;
	private String productCategory;
	private int price;
	private int quantity;
	private int totalPrice;

}
