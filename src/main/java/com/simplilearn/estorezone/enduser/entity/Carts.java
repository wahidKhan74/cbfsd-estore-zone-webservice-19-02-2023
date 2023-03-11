package com.simplilearn.estorezone.enduser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.simplilearn.estorezone.admin.entity.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Carts {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cartId")
	private int cartId;
	
	@Column(name="productId")
	private int productId;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="quantity")
	private int quantity;
	
	@Transient
	private Products products;
	
}
