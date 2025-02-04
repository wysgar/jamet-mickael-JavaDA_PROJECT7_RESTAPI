package com.nnk.springboot.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.config.ValidPassword;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entity class representing a user in the system.
 * Implements {@link UserDetails} for integration with Spring Security.
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    @ValidPassword
    private String password;

    @NotBlank(message = "Fullname is mandatory")
    @Column(name = "fullname")
    private String fullname;

    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    private String role;
    
    public User() {}
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the authorities granted to the user.
     * This method is used by Spring Security.
     *
     * @return a collection of {@link GrantedAuthority} objects
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role);
		List<SimpleGrantedAuthority> autority = new ArrayList<>();
		autority.add(simpleGrantedAuthority);
		return autority;
	}

	/**
     * Indicates whether the user's account has expired.
     */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
     * Indicates whether the user is locked or unlocked.
     */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
     * Indicates whether the user's credentials (password) have expired.
     */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
     * Indicates whether the user is enabled or disabled.
     */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
