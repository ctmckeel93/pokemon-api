package com.coreym.pokemon.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coreym.pokemon.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	// SELECT * FROM users WHERE email = ?
	public User findByEmail(String email);
}
