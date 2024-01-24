package com.coreym.pokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pokemon {
	private String name;
	
	@JsonProperty("types")
	private TypeObject[] types;
	
	public Pokemon() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("types")
	public TypeObject[] getTypes() {
		return types;
	}

	public void setTypes(TypeObject[] types) {
		this.types = types;
	}
	
	
	
	
}
