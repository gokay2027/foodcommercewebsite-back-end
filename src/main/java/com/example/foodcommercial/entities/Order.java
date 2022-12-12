package com.example.foodcommercial.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private Food food;
	
	@ManyToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private PaymentType paymentType;
	
	@ManyToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private User user;
	
	@ManyToOne
	@JoinColumn
	//give order methodunda restaurant yok
	private Restaurant restaurant;
	
	
	@ManyToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private Adress userAdress;
}
