package com.nnk.springboot.unitaires;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;

@SpringBootTest
public class RatingServiceTest {

    @Autowired
    private RatingService ratingService;

    @MockBean
    private RatingRepository ratingRepository;

    @Test
    void testSave() {
        Rating rating = new Rating();
        rating.setMoodysRating("Aa1");
        ratingService.save(rating);

        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void testFindAll() {
        when(ratingRepository.findAll()).thenReturn(List.of(new Rating()));
        List<Rating> result = ratingService.findAll();

        assertFalse(result.isEmpty());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(ratingRepository.findById(1)).thenReturn(Optional.of(new Rating()));
        Optional<Rating> result = ratingService.findById(1);

        assertTrue(result.isPresent());
        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    void testDelete() {
        Rating rating = new Rating();
        rating.setId(1);
        ratingService.delete(rating);

        verify(ratingRepository, times(1)).delete(rating);
    }
}

