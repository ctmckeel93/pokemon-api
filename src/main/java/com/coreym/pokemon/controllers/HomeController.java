package com.coreym.pokemon.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coreym.pokemon.models.auth.User;
import com.coreym.pokemon.models.pokemon.Pokemon;
import com.coreym.pokemon.models.pokemon.PokemonList;
import com.coreym.pokemon.services.PokemonService;
//import com.coreym.pokemon.services.PokemonService;
import com.coreym.pokemon.services.UserService;

import jakarta.servlet.http.HttpSession;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private PokemonService pokeService;
	
	@Autowired
	private ApiController api;
	
	
	

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		if (session.getAttribute("loggedInUser") == null) {
			return "redirect:/login-page";
		}
		
		PokemonList pokemon = api.getAllPokemon();
		
		ArrayList<Mono<Pokemon>> pokeList = api.handleConcurrency(pokemon);
		System.out.println(pokeList.size());
		System.out.println(pokeList.get(0));
		for (Mono<Pokemon> p : pokeList) {
			pokeService.create(p.block());
		}
		
		User userFromDb = uService.findOne((Long)session.getAttribute("loggedInUser"));
		
		model.addAttribute("userFirstName",userFromDb.getFirstName());
		
		
		return "home.jsp";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value="pokemon", required=false) String pokeName) {
		
		if (pokeName != null) {			
			Pokemon pokemon = api.getPokemon(pokeName).block();
			System.out.println(pokemon.getTypes().get(0).getInfo().getName());
			System.out.println(pokemon.getMoveList().get(0).getMove().getName());
			System.out.println(pokemon.getSprites().getNewImageUrls().getDreamWorldSprites().getFront_default());
			System.out.println(pokemon.getStats().get(0).getBaseStat());
			System.out.println(pokemon.getStats().get(0).getStatInfo().getName());
			System.out.println(pokemon.getId());
			pokeService.create(pokemon);
			
		}
		
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login-page";
	}
}
