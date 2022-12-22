package com.example.foodcommercial.entities;

import javax.persistence.Column;
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


@Entity
@Table(name="evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String content;
	
	@Column
	@NotNull
	private int rateValue;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Restaurant restaurant;
	

	
}
