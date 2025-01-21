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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@SpringBootTest
@AutoConfigureMockMvc
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    @Test
    void testHome() throws Exception {
        when(curvePointService.findAll()).thenReturn(List.of(new CurvePoint()));

        mockMvc.perform(get("/curvePoint/list")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"))
                .andExpect(model().attribute("remoteUser", "testUser"));

        verify(curvePointService, times(1)).findAll();
    }

    @Test
    void testAddCurvePointForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("curveId", "1")
                .param("term", "10.0")
                .param("value", "20.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, times(1)).save(any(CurvePoint.class));
    }
    
    @Test
    void testShowUpdateForm() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1, 20.0, 30.0);
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));

        mockMvc.perform(get("/curvePoint/update/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(model().attribute("curvePoint", curvePoint));
    }

    @Test
    void testUpdateBid() throws Exception {
        CurvePoint curvePoint = new CurvePoint(1, 20.0, 30.0);
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));

        mockMvc.perform(post("/curvePoint/update/1")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("term", "15.0")
                .param("value", "25.0")
                .param("curveId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, times(1)).save(any(CurvePoint.class));
    }

    
    @Test
    void testDeleteCurvePoint() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointService.findById(1)).thenReturn(Optional.of(curvePoint));

        mockMvc.perform(get("/curvePoint/delete/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));

        verify(curvePointService, times(1)).delete(curvePoint);
    }
}

