package com.example.foodcommercial.entities;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
	private Category category;

	@ManyToMany
	@JoinTable(name = "food_portion",
			joinColumns = @JoinColumn(name = "food_id"),
			inverseJoinColumns = @JoinColumn(name = "portion_id"))
	private List<Portion> portion;

	@ManyToOne
	@JoinColumn
	private Restaurant restaurant;

}
