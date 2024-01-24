package com.coreym.pokemon.controllers;


import java.time.Duration;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.coreym.pokemon.models.pokemon.Pokemon;
import com.coreym.pokemon.models.pokemon.PokemonList;
import com.coreym.pokemon.models.pokemon.SimplePokemonData;
import com.coreym.pokemon.services.PokemonService;

import io.netty.channel.ChannelOption;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@RestController
public class ApiController {

	private final String API = "https://pokeapi.co/api/v2/";
	private WebClient client = WebClient.create();
	
	@Autowired
	private PokemonService service;
	
	 @SuppressWarnings("deprecation")
	public ApiController() {
	        this.client = WebClient.builder()
	        		.clientConnector(new ReactorClientHttpConnector(
	                        HttpClient.create()
	                                .wiretap(true)
	                                .compress(true)
	                                .tcpConfiguration(tcpClient ->
	                                        tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000))
	                                .responseTimeout(Duration.ofSeconds(10))
	                ))
	                .exchangeStrategies(ExchangeStrategies.builder()
	                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(100 * 1024 * 1024))
	                        .build()).build();
	    }

	
	public PokemonList getAllPokemon() {
		
		PokemonList response = client.get()
			.uri(API + "pokemon/" + "?limit=150" )
			.retrieve()
			.bodyToMono(PokemonList.class)
			.block();
		
		return response;

	}
	
	public Mono<Pokemon> getPokemon(String pokemonName) {
		
		Mono<Pokemon> response = client.get()
				.uri(API + "pokemon/" + pokemonName)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Pokemon.class);
		
		return response;
				
		
		
	
	}
	
	public ArrayList<Mono<Pokemon>> handleConcurrency(PokemonList pokemon) {
		
		ArrayList<Mono<Pokemon>> allPokemon = new ArrayList<>();
	    Flux.fromIterable(pokemon.getList())
	        .flatMap(item -> {
	            if (item instanceof SimplePokemonData) {
	                // Return the Mono instance
	                return client.get()
	                        .uri(API + "/pokemon/" + ((SimplePokemonData) item).getName())
	                        .retrieve()
	                        .bodyToMono(SimplePokemonData.class);
	            } else {
	                // Handle the case where the item is not an instance of SimplePokemonData
	                return Mono.empty(); // Returning an empty Mono as a placeholder
	            }
	        }, 10)
	        .subscribe(response -> {
	            if (response instanceof SimplePokemonData) {
	                SimplePokemonData simplePokemonDataResponse = (SimplePokemonData) response;
	                Mono<Pokemon> pokeData = this.getPokemon(simplePokemonDataResponse.getName());
	                allPokemon.add(pokeData);
	                
	                
	            } else {
	                System.out.println("Couldn't get a pokemon");
	            }
	        });
	    	return allPokemon;
	}
}
