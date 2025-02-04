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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Test
    void testHome() throws Exception {
        when(ratingService.findAll()).thenReturn(List.of(new Rating()));

        mockMvc.perform(get("/rating/list")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratings"))
                .andExpect(model().attribute("remoteUser", "testUser"));

        verify(ratingService, times(1)).findAll();
    }

    @Test
    void testAddRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/rating/validate")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("moodysRating", "A1")
                .param("sandPRating", "A+")
                .param("fitchRating", "A")
                .param("order", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, times(1)).save(any(Rating.class));
    }
    
    @Test
    void testShowUpdateForm() throws Exception {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        when(ratingService.findById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(get("/rating/update/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"))
                .andExpect(model().attribute("rating", rating));
    }

    @Test
    void testUpdateRating() throws Exception {
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        when(ratingService.findById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(post("/rating/update/1")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("moodysRating", "New Moodys Rating")
                .param("sandPRating", "New Sand PRating")
                .param("fitchRating", "New Fitch Rating")
                .param("order", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, times(1)).save(any(Rating.class));
    }

    @Test
    void testDeleteRating() throws Exception {
        Rating rating = new Rating();
        when(ratingService.findById(1)).thenReturn(Optional.of(rating));

        mockMvc.perform(get("/rating/delete/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));

        verify(ratingService, times(1)).delete(rating);
    }
}

