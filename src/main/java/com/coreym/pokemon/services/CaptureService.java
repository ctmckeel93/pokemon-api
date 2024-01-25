package com.coreym.pokemon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coreym.pokemon.models.auth.User;
import com.coreym.pokemon.models.pokemon.Pokemon;

@Service
public class CaptureService {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private PokemonService pService;
	
	public void capture(Long userId, Long pokemonId, RedirectAttributes flashAttributes) {
		User user = uService.findOne(userId);
		Pokemon pokemon = pService.find(pokemonId);
		
		if (user.getMyPokemon().size() < 6) {
			user.getMyPokemon().add(pokemon);
			uService.create(user);			
		} else {
			flashAttributes.addFlashAttribute("teamFull", "Your team is already full");
			System.out.println("Team is full");
		}
		
	}
	
	public void release(Long userId, Long pokemonId) {
		User user = uService.findOne(userId);
		Pokemon pokemon = pService.find(pokemonId);
		
		
			user.getMyPokemon().remove(pokemon);
			uService.create(user);			
	}

}
