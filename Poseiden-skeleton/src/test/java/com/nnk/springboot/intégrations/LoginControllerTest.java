package com.nnk.springboot.intégrations;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    
    @Test
    void testLogin() throws Exception {
        mockMvc.perform(get("/app/login")
                .with(csrf())
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testGetAllUserArticles() throws Exception {
        List<User> users = Arrays.asList(new User("John Doe", "password"), new User("Jane Smith", "password"));
        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/app/secure/article-details")
                .with(csrf())
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    void testError() throws Exception {
        mockMvc.perform(get("/app/error")
                .with(csrf())
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("403"))
                .andExpect(model().attribute("errorMsg", "You are not authorized for the requested data."));
    }

}
