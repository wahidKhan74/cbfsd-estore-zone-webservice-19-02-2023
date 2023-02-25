package com.simplilearn.estorezone.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="orderItemId")
	private int orderItemId;
	
	@Column(name="orderId")
	private int orderId;
	
	@Column(name="productId")
	private int productId;
	
	@Column(name="productTitle")
	private String productTitle;
	
	@Column(name="productDescription")
	private String productDescription;
	
	@Column(name="productCode")
	private String productCode;
	
	@Column(name="productImg")
	private String productImg;
	
	@Column(name="productCategory")
	private String productCategory;
	
	@Column(name="price")
	private int price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="totalPrice")
	private int totalPrice;

}
