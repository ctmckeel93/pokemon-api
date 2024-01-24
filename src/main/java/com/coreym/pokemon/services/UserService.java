package com.coreym.pokemon.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.coreym.pokemon.models.LoginUser;
import com.coreym.pokemon.models.User;
import com.coreym.pokemon.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	// Create
	public User create(User user) {
		return repo.save(user);
	}
	// Find By Id
	public User findOne(Long id) {
		return repo.findById(id).orElse(null);
	}
	// Find by email
	public User authenticateUserByEmail(String email) {
		return repo.findByEmail(email);
	}
	
	// Validate user registration
	public void validateUser(User user, BindingResult result) {
		User userFromDb = repo.findByEmail(user.getEmail());
		
		if (userFromDb != null) {
			result.rejectValue("email", "unique", "User exists in database");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "matches", "Password must match confirm password");
		}
		
	}
	
	public User authenticate(LoginUser loginUser, BindingResult result ) {
		// take the email and make sure this user exists in the database
		User userOrNull = repo.findByEmail(loginUser.getEmail()); // This will be null if the email doesnt exist
		
		if (userOrNull == null) {
			result.rejectValue("email", "invalid", "invalid login");
			return null;
		}
		
		// Compare the password entered in the form, with the password of the user that matches the email from the form
		if (!BCrypt.checkpw(loginUser.getPassword(), userOrNull.getPassword())) {
			result.rejectValue("email", "invalid", "invalid login");
			return null;
		}
		
		return userOrNull;
		
	}
}

