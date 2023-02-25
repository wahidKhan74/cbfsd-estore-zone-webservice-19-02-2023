package com.simplilearn.estorezone.admin.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
	
	private String status;
	private String message;
	private Date timestamp = new Date();
	private Object data;
	
}
