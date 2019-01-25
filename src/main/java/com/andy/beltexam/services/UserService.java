package com.andy.beltexam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.andy.beltexam.models.User;
import com.andy.beltexam.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// <---------- REGISTER USER AND HASH THEIR PASSWORD ---------->
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepository.save(user);
	}
	
	// <---------- FIND USER BY EMAIL ---------->
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// <---------- FIND USER BY NAME ---------->
	public User findByName(String name) {
		return userRepository.findByName(name);
	}
	
	// <---------- FIND USER BY ID ---------->
	public User findUserById(Long id) {
		Optional<User> u = userRepository.findById(id);
		
		if(u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}
	
	// <---------- AUTHENTICATE USER BEFORE LOGIN ---------->
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = userRepository.findByEmail(email);
		// if we can't find it be email, return false
		if(user == null) {
			return false;
		} else {
			// if the passwords match, return true, else, return false;
			if(BCrypt.checkpw(password,  user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
}
