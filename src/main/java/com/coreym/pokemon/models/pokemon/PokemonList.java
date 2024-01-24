package com.coreym.pokemon.models.pokemon;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonList {
	
	@JsonProperty("results")
	private ArrayList<SimplePokemonData> list;
	
	public PokemonList() {}

	public ArrayList<SimplePokemonData> getList() {
		return list;
	}

	public void setList(ArrayList<SimplePokemonData> list) {
		this.list = list;
	}
	
	

}
