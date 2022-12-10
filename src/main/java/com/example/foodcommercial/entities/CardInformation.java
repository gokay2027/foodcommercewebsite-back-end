package com.example.foodcommercial.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="cardinformation")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CardInformation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String endDate;
	
	@Column
	private String ccv;
	
	@Column
	private String cardNumber;
	
	@Column
	private String cardName;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

}
