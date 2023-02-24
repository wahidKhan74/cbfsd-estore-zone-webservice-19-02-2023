package com.simplilearn.estorezone.admin.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products {

	private int productId;
	private String productTitle;
	private String productDescription;
	private String productCode;
	private List<String> images;
	private int thumbnailImage;
	private int price;
	private Date addedOn;
	private int rating;
}
