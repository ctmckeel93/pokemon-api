package com.coreym.pokemon.models.api.images;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPokemonImages {
	
	@JsonProperty("official-artwork")
	private PokemonOfficialArtwork officialArtworkSprites;
	
	@JsonProperty("dream_world")
	private PokemonDreamWorld dreamWorldSprites;
	
	public NewPokemonImages() {}

	public PokemonOfficialArtwork getOfficialArtworkSprites() {
		return officialArtworkSprites;
	}

	public void setOfficialArtworkSprites(PokemonOfficialArtwork officialArtworkSprites) {
		this.officialArtworkSprites = officialArtworkSprites;
	}

	public PokemonDreamWorld getDreamWorldSprites() {
		return dreamWorldSprites;
	}

	public void setDreamWorldSprites(PokemonDreamWorld dreamWorldSprites) {
		this.dreamWorldSprites = dreamWorldSprites;
	}

}
