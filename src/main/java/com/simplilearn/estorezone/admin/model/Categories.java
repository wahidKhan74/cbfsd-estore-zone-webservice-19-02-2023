package com.simplilearn.estorezone.admin.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product Categories Model
 * @author wahidkhan
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
	
	private int categoryId;
	private String categoryName;
	private String categoryDescription;
	private String categoryImageUrl;
	private int active;
	private Date addedOn;
	
}
