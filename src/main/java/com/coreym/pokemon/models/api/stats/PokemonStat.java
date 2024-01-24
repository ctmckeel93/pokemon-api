package com.coreym.pokemon.models.api.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonStat {
	
	@JsonProperty("base_stat")
	private Double baseStat;
	
	@JsonProperty("stat")
	private PokemonStatInfo statInfo;
	
	public PokemonStat() {}


	public PokemonStat(String name, Double baseStat, PokemonStatInfo statInfo) {
		super();
		this.baseStat = baseStat;
		this.statInfo = statInfo;
	}



	public Double getBaseStat() {
		return baseStat;
	}

	public void setBaseStat(Double baseStat) {
		this.baseStat = baseStat;
	}

	public PokemonStatInfo getStatInfo() {
		return statInfo;
	}

	public void setStatInfo(PokemonStatInfo statInfo) {
		this.statInfo = statInfo;
	}

}
