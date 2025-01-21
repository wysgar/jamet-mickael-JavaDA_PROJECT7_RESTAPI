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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;

@SpringBootTest
public class CurvePointServiceTest {

    @Autowired
    private CurvePointService curvePointService;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Test
    void testSave() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);
        curvePointService.save(curvePoint);

        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void testFindAll() {
        when(curvePointRepository.findAll()).thenReturn(List.of(new CurvePoint()));
        List<CurvePoint> result = curvePointService.findAll();

        assertFalse(result.isEmpty());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(new CurvePoint()));
        Optional<CurvePoint> result = curvePointService.findById(1);

        assertTrue(result.isPresent());
        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    void testDelete() {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePointService.delete(curvePoint);

        verify(curvePointRepository, times(1)).delete(curvePoint);
    }
}

