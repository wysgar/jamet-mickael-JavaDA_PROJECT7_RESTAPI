package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

/**
 * Configuration class for Spring Security settings.
 * Defines authentication and authorization rules for the application.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	/** User service for authentication. */
	@Autowired
	private UserService userService;

	/**
     * Configures security filters and authorization rules.
     *
     * @param http the {@link HttpSecurity} instance to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/", "/css/bootstrap.min.css").permitAll()
	        .requestMatchers("/user/**").hasAuthority("ADMIN");
			auth.anyRequest().authenticated();
		})
		.formLogin(form -> form
				.loginPage("/app/login")
                .defaultSuccessUrl("/bidList/list", true)
                .failureUrl("/app/login?error=true")
				.permitAll())
		.logout((logout) -> logout
				.logoutUrl("/app-logout").logoutSuccessUrl("/login"));
		
		return http.build();
	}
	
	/**
     * Provides a password encoder using BCrypt.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
     * Configures the authentication manager with the user details service and password encoder.
     *
     * @param http the {@link HttpSecurity} instance
     * @param bCryptPasswordEncoder the password encoder
     * @return the configured {@link AuthenticationManager}
     * @throws Exception if an error occurs during configuration
     */
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}
	
	@Bean
	public CommandLineRunner initDatabase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
	    return args -> {
	        if (userRepository.count() == 0) {
	            User admin = new User();
	            admin.setUsername("admin");
	            admin.setFullname("Administrator");
	            admin.setPassword(passwordEncoder.encode("admin"));
	            admin.setRole("ADMIN");

	            User user = new User();
	            user.setUsername("user");
	            user.setFullname("Regular User");
	            user.setPassword(passwordEncoder.encode("user"));
	            user.setRole("USER");

	            userRepository.save(admin);
	            userRepository.save(user);

	            System.out.println("Admin and User accounts have been created.");
	        } else {
	            System.out.println("Users already exist, skipping initialization.");
	        }
	    };
	}

}