package com.coreym.pokemon.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.coreym.pokemon.models.pokemon.Pokemon;

public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
	
	public ArrayList<Pokemon> findAll();
	

}
