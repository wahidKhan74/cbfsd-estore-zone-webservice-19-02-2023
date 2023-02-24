package com.simplilearn.estorezone.admin.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipments {

	private int shipmentId;
	private int orderId;
	private int shipmentStatus;
	private String shipmentTitle;
	private Date shipmentDate;
	private Date expectedDeliveryDate;
	private String shipmentMethod;
	private String shipmentCompany;

}
