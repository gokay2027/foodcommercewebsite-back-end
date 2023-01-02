package com.example.foodcommercial.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotBlank
	@NotNull
	private String endDate;
	
	@Column
	@NotBlank
	@NotNull
	private String ccv;
	
	@Column
	@NotBlank
	@NotNull
	private String cardNumber;
	
	@Column
	@NotBlank
	@NotNull
	private String cardName;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

}
