package com.coreym.pokemon.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coreym.pokemon.models.pokemon.Pokemon;
import com.coreym.pokemon.repositories.PokemonRepository;

@Service
public class PokemonService {
	
	@Autowired
	private PokemonRepository pokeRepo;
	
	public ArrayList<Pokemon> all() {
		return pokeRepo.findAll();
	}
	
	public Pokemon create(Pokemon pokemon) {
		pokemon.setStatBlock();
		return pokeRepo.save(pokemon);
	}
}
