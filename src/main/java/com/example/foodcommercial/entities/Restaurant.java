package com.example.foodcommercial.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotBlank
	@NotNull
	private String name;
	
	@OneToOne
	@JoinColumn
	@NotBlank
	@NotNull
	private Adress adress;
		
	@JsonBackReference
	@OneToMany(mappedBy = "restaurant")
	private List<Food> foods;
	
	@JsonBackReference
	@OneToMany(mappedBy = "restaurant")
	private List<Evaluation> evaluations;
	
	
}
