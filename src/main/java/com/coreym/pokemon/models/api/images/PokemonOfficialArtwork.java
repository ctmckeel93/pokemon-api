package com.coreym.pokemon.models.api.images;

public class PokemonOfficialArtwork {
	
private String front_default;
	
	private String front_shiny;
	
	public PokemonOfficialArtwork() {}

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

}
