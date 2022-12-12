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

@Data
@Entity
@Table(name = "food")
@NoArgsConstructor
@AllArgsConstructor
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotBlank
	@NotNull
	private String name;

	@Column
	private int price;

	@ManyToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private Category category;

	@ManyToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private Portion portion;

	@ManyToOne
	@JoinColumn
	private Restaurant restaurant;

}
