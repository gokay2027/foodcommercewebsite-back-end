package com.example.foodcommercial.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

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
	private Address address;
		
	@JsonBackReference
	@OneToMany(mappedBy = "restaurant")
	private List<Food> foods;
	
	@JsonBackReference
	@OneToMany(mappedBy = "restaurant")
	private List<Evaluation> evaluations;

	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "restaurant_category",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> category;
}
