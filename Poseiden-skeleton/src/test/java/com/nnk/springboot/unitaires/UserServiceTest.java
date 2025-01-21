package com.nnk.springboot.unitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testSave() {
        User user = new User();
        user.setUsername("user1");
        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(new User()));
        List<User> result = userService.findAll();

        assertFalse(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        Optional<User> result = userService.findById(1);

        assertTrue(result.isPresent());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testDelete() {
        User user = new User();
        user.setId(1);
        userService.delete(user);

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        User user = new User();
        user.setUsername("user1");
        when(userRepository.findByUsername("user1")).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername("user1");

        assertEquals("user1", userDetails.getUsername());
        verify(userRepository, times(1)).findByUsername("user1");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("nonexistent");
        });
        verify(userRepository, times(1)).findByUsername("nonexistent");
    }
}

