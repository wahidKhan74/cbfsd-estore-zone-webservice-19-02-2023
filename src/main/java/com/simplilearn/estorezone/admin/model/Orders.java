package com.simplilearn.estorezone.admin.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	// order details properties
	private int orderId;
	private Date orderDate;
	private String orderStatus;

	// pricing properties
	private int totalItems;
	private int itemsSubTotal;
	private int shipmentCharges;
	private int totalAmount;
	private int paymentStatus;

	// payment status properties
	private String paymentStatusTitle;
	private int paymentMethod;
	private String paymentMethodTitle;

	// customer / user properties
	private int userId;
	private String email;
	private String address;
	private String name;
	private Long contact;
}
