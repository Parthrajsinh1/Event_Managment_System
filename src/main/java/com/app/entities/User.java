package com.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor

public class User extends BaseEntity{
	
	@Column(length=20)
	private String name;
	@Column(length=20 ,unique = true)
	private String email;
	@Column(length=20 , nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id") // foreign key in users table
	private Role role;

	@Override
	public String toString() {
		return "User ID "+getId() + " [name=" + name + ", email=" + email + "]";
	}
	
	
	
}
