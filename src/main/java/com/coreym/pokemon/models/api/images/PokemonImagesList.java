package com.coreym.pokemon.models.api.images;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonImagesList {
	
	private String front_default;
	private String front_shiny;
	
	
	@JsonProperty("other")
	private NewPokemonImages newImageUrls;


	public String getFront_default() {
		return front_default;
	}


	public void setFront_default(String front_default) {
		this.front_default = front_default;
	}


	public String getFront_shiny() {
		return front_shiny;
	}


	public void setFront_shiny(String front_shiny) {
		this.front_shiny = front_shiny;
	}


	public NewPokemonImages getNewImageUrls() {
		return newImageUrls;
	}


	public void setNewImageUrls(NewPokemonImages newImageUrls) {
		this.newImageUrls = newImageUrls;
	}

}
