package com.example.foodcommercial.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@Column
	@NotBlank
	@NotNull
	private String name;
	
	@Column
	private String surname;
	
	@Column(unique = true)
	@Email
	@NotBlank
	@NotNull
	private String email;
	
	@Column
	@NotBlank
	@NotNull
	private String password;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String birthDate;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private List<Address> addresses;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<CardInformation> cards;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<FavoriteRestaurants> favoriteRestaurants;

	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Evaluation> evaluations;
	
}