package com.coreym.pokemon.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coreym.pokemon.controllers.api.ApiController;
import com.coreym.pokemon.models.auth.User;
import com.coreym.pokemon.models.pokemon.Pokemon;
import com.coreym.pokemon.services.CaptureService;
import com.coreym.pokemon.services.PokemonService;
//import com.coreym.pokemon.services.PokemonService;
import com.coreym.pokemon.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private PokemonService pokeService;
	
	@Autowired
	private ApiController api;
	
	@Autowired
	private CaptureService cService;
	
	
	

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		if (session.getAttribute("loggedInUser") == null) {
			return "redirect:/login-page";
		}
		
		ArrayList<Pokemon> allPokemon = pokeService.all();
		model.addAttribute("allPokemon", allPokemon);
		
//		PokemonList pokemon = api.getAllPokemon();
//		
//		for (SimplePokemonData data : pokemon.getList()) {
//			Pokemon pokeData = api.getPokemon(data.getName());
//			pokeService.create(pokeData);
//		}
		
		User userFromDb = uService.findOne((Long)session.getAttribute("loggedInUser"));
		
		model.addAttribute("userFirstName",userFromDb.getFirstName());
		model.addAttribute("myPokemonTeamSize", userFromDb.getMyPokemon().size());
		
		
		return "home.jsp";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(value="pokemon", required=false) String pokeName) {
		
		if (pokeName != null) {			
			Pokemon pokemon = api.getPokemon(pokeName);
			System.out.println(pokemon.getTypes().get(0).getInfo().getName());
			System.out.println(pokemon.getMoveList().get(0).getMove().getName());
			System.out.println(pokemon.getSprites().getNewImageUrls().getDreamWorldSprites().getFront_default());
			System.out.println(pokemon.getStats().get(0).getBaseStat());
			System.out.println(pokemon.getStats().get(0).getStatInfo().getName());
			System.out.println(pokemon.getId());
//			pokeService.create(pokemon);
			
		}
		
		return "home.jsp";
	}
	
	@GetMapping("/capture/{pokeId}")
	public String capturePokemon(@PathVariable() Long pokeId, HttpSession session, RedirectAttributes flash) {
		
		Long userId = (Long)session.getAttribute("loggedInUser");
		cService.capture(userId, pokeId, flash);
		return "redirect:/home";
		
	}
	
	@DeleteMapping("/release/{pokeId}")
	public String releasePokemon(@PathVariable() Long pokeId, HttpSession session) {
		
		Long userId = (Long)session.getAttribute("loggedInUser");
		cService.release(userId, pokeId);
		return "redirect:/my-pokemon";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login-page";
	}
}
