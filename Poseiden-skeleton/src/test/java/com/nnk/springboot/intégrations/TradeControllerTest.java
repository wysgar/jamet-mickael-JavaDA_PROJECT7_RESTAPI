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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @Test
    void testHome() throws Exception {
        when(tradeService.findAll()).thenReturn(List.of(new Trade()));

        mockMvc.perform(get("/trade/list")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("trades"))
                .andExpect(model().attribute("remoteUser", "testUser"));

        verify(tradeService, times(1)).findAll();
    }

    @Test
    void testAddTradeForm() throws Exception {
        mockMvc.perform(get("/trade/add")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/trade/validate")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("account", "Trade1")
                .param("type", "Type1")
                .param("buyQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));

        verify(tradeService, times(1)).save(any(Trade.class));
    }

    @Test
    void testShowUpdateForm() throws Exception {
        Trade trade = new Trade("Trade 1", "trade", 1.0);
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));

        mockMvc.perform(get("/trade/update/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("trade"))
                .andExpect(model().attribute("trade", trade));
    }
    
    @Test
    void testUpdateTrade() throws Exception {
        Trade trade = new Trade("Trade 1", "trade", 1.0);
        trade.setBuyQuantity(3.0);
        trade.setId(1);
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));

        mockMvc.perform(post("/trade/update/1")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("account", "Updated Trade Name")
                .param("type", "updated type")
                .param("buyQuantity", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));

        verify(tradeService, times(1)).save(any(Trade.class));
    }

    
    @Test
    void testDeleteTrade() throws Exception {
        Trade trade = new Trade();
        when(tradeService.findById(1)).thenReturn(Optional.of(trade));

        mockMvc.perform(get("/trade/delete/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));

        verify(tradeService, times(1)).delete(trade);
    }
}

