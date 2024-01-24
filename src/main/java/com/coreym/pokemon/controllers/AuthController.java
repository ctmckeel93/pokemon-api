package com.coreym.pokemon.controllers;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.coreym.pokemon.models.LoginUser;
import com.coreym.pokemon.models.User;
import com.coreym.pokemon.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login-page";
	}
	
	// route for rendering the registration form
	@GetMapping("/login-page")
	public String loginPage(@ModelAttribute User user, Model model) {
		model.addAttribute("loginUser", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute User user, BindingResult result, Model model) {
		
		uService.validateUser(user, result);
		
		
		if (result.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "index.jsp";
		}
		
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(encryptedPassword);
		Long userId = uService.create(user).getId();
		session.setAttribute("loggedInUser", userId );
		return "redirect:/home";
		
		
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginUser loginUser, BindingResult result, Model model) {
		
		User authenticatedUser = uService.authenticate(loginUser, result);
		
		if (result.hasErrors()) {
			model.addAttribute("user", new User());
			return "index.jsp";
		}
		
		session.setAttribute("loggedInUser", authenticatedUser.getId());
		return "redirect:/home";
	}
	
	

}
