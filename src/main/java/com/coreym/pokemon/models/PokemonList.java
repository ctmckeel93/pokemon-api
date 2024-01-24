package com.coreym.pokemon.models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonList {
	
	@JsonProperty("results")
	private ArrayList<Pokemon> list;
	
	public PokemonList() {}

	public ArrayList<Pokemon> getList() {
		return list;
	}

	public void setList(ArrayList<Pokemon> list) {
		this.list = list;
	}
	
	

}
