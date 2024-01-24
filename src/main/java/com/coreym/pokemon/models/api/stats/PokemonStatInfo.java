package com.coreym.pokemon.models.api.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonStatInfo {
	
	@JsonProperty("name")
	private String name;
	
	public PokemonStatInfo() {}

	public PokemonStatInfo(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
