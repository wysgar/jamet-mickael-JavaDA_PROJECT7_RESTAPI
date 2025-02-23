package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * Service class for managing user-related operations.
 * Implements {@link UserDetailsService} for Spring Security authentication.
 */
@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	/**
     * Loads a user by their username for authentication.
     * Required by Spring Security for user authentication.
     *
     * @param username the username of the user to load
     * @return the {@link UserDetails} object for authentication
     * @throws UsernameNotFoundException if the user is not found
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return user;
	}
}
