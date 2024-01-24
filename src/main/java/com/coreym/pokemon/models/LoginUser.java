package com.coreym.pokemon.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUser {

	@Email(message="Email i9s in the incorrect format: xxx@xxx.com")
	@NotBlank(message="Email is required")
	private String email;
	
	@NotBlank
	@Size(min=8, max=200, message="Password must be at least 8 characters")
	private String password;
	
	public LoginUser() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
