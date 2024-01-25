package com.coreym.pokemon.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coreym.pokemon.models.auth.User;
import com.coreym.pokemon.models.pokemon.Pokemon;
import com.coreym.pokemon.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PokemonController {
	
	@Autowired
	private UserService uService;
	
	@GetMapping("/my-pokemon")
	public String showMyTeam(HttpSession session, Model model) {
		
		// Get the user logged in
		User user = uService.findOne((Long) session.getAttribute("loggedInUser"));
		ArrayList<Pokemon> myPokemon = new ArrayList<Pokemon>(user.getMyPokemon()) ;
		model.addAttribute("myPokemon",myPokemon);
		
		return "myPokemon.jsp";
	}

}
