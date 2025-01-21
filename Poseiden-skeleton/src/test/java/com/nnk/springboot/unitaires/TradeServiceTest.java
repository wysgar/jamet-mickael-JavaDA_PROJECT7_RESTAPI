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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

@SpringBootTest
public class TradeServiceTest {

    @Autowired
    private TradeService tradeService;

    @MockBean
    private TradeRepository tradeRepository;

    @Test
    void testSave() {
        Trade trade = new Trade();
        trade.setAccount("Account1");
        trade.setType("Type1");
        tradeService.save(trade);

        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void testFindAll() {
        when(tradeRepository.findAll()).thenReturn(List.of(new Trade()));
        List<Trade> result = tradeService.findAll();

        assertFalse(result.isEmpty());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(new Trade()));
        Optional<Trade> result = tradeService.findById(1);

        assertTrue(result.isPresent());
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    void testDelete() {
        Trade trade = new Trade();
        trade.setId(1);
        tradeService.delete(trade);

        verify(tradeRepository, times(1)).delete(trade);
    }
}

