package com.app.model;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Items {

	
	private String	commodity;
	private String	broker;
	private Double	packageWeight;
	private Integer	noOfPackages ;
	private Double	totalWeight ;




}
