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
@Table(name = "whishlist")
public class WhishList {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="wishlistId")
	private int wishlistId;
	
	@Column(name="productId")
	private int productId;
	
	@Column(name="userId")
	private int userId;
	
	@Transient
	private Products products;
}
