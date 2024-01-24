package com.coreym.pokemon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coreym.pokemon.models.Pokemon;
import com.coreym.pokemon.models.PokemonList;
import com.coreym.pokemon.models.TypeObject;
import com.coreym.pokemon.models.User;
import com.coreym.pokemon.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ApiController api;

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		if (session.getAttribute("loggedInUser") == null) {
			return "redirect:/login-page";
		}
		
		PokemonList pokemon = api.getAllPokemon();
		
//		for (Pokemon p : pokemon.getList()) {
//			System.out.println(p.getName());
//		}
		
		User userFromDb = uService.findOne((Long)session.getAttribute("loggedInUser"));
		
		model.addAttribute("userFirstName",userFromDb.getFirstName());
		
		
		return "home.jsp";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("pokemon") String pokeName) {
		Pokemon pokemon = api.getPokemon(pokeName);
		System.out.println(pokemon.getTypes()[0].getInfo().getName());
		
		
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login-page";
	}
}
