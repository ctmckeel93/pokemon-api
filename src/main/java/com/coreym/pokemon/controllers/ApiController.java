package com.coreym.pokemon.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.coreym.pokemon.models.Pokemon;
import com.coreym.pokemon.models.PokemonList;

@RestController
public class ApiController {

	private final String API = "https://pokeapi.co/api/v2/";
	private final WebClient client = WebClient.create();
	
	public PokemonList getAllPokemon() {
		
		PokemonList response = client.get()
			.uri(API + "pokemon/" + "?limit=20" )
			.retrieve()
			.bodyToMono(PokemonList.class)
			.block();
		
		return response;

	}
	
	public Pokemon getPokemon(String pokemonName) {
		
		Pokemon response = client.get()
				.uri(API + "pokemon/" + pokemonName)
				.retrieve()
				.bodyToMono(Pokemon.class)
				.block();
		
		return response;
	}
}
