package com.coreym.pokemon.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonTypesList {

	@JsonProperty("type")
	private TypeObject typeObject;
	
	public PokemonTypesList() {}

	public TypeObject getType() {
		return typeObject;
	}

	public void setType(TypeObject typeObj) {
		this.typeObject = typeObj;
	}
	
	
}
