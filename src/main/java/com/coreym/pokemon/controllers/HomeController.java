package com.coreym.pokemon.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.coreym.pokemon.models.User;
import com.coreym.pokemon.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService uService;

	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		
		if (session.getAttribute("loggedInUser") == null) {
			return "redirect:/login-page";
		}
		
		User userFromDb = uService.findOne((Long)session.getAttribute("loggedInUser"));
		
		model.addAttribute("userFirstName",userFromDb.getFirstName());
		
		
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login-page";
	}
}