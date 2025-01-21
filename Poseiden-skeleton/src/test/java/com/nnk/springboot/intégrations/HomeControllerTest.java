package com.nnk.springboot.intégrations;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
	@Test
	void testHome() throws Exception {
	    mockMvc.perform(get("/")
                .with(user("testUser").roles("ADMIN")))
	            .andExpect(status().isOk())
	            .andExpect(view().name("home"));
	}
	
	@Test
	void testAdminHome() throws Exception {
	    mockMvc.perform(get("/admin/home")
                .with(user("testUser").roles("ADMIN")))
	            .andExpect(status().is3xxRedirection())
	            .andExpect(redirectedUrl("/bidList/list"));
	}
}
