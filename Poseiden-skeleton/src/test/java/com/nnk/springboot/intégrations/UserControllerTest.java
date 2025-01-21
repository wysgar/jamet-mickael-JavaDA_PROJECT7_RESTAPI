package com.nnk.springboot.intégrations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testHome() throws Exception {
        when(userService.findAll()).thenReturn(List.of(new User()));

        mockMvc.perform(get("/user/list")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));

        verify(userService, times(1)).findAll();
    }

    @Test
    void testAddUserForm() throws Exception {
        mockMvc.perform(get("/user/add")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/user/validate")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("username", "testUser")
                .param("password", "password123")
                .param("fullname", "Test User")
                .param("role", "USER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));

        verify(userService, times(1)).save(any(User.class));
    }
    
    @Test
    void testShowUpdateForm() throws Exception {
        User user = new User("john.doe", "password123");
        when(userService.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/update/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", user));
    }
    
    @Test
    void testUpdateUser() throws Exception {
        User user = new User("john.doe", "password123");
        when(userService.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/user/update/1")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("username", "john.doe")
                .param("password", "newPassword123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = new User();
        when(userService.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/delete/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));

        verify(userService, times(1)).delete(user);
    }
}

