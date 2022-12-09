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

import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String birthDate;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinColumn
	private List<Adress> adress;
	
	
	
}