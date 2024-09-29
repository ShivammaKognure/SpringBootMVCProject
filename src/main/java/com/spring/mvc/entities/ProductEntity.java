package com.spring.mvc.entities;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long proId;
	private String proName;
	private double proPrice;
	private double proPriceDisc;
	
	//private String proImg;
	private String proBrand;
	private String proDescription;
	private String proCategory;
	
	
	private LocalDate createdAt;
	private String createdBy;
	
	

}
