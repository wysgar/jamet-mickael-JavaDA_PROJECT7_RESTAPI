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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
public class BidListServiceTest {

    @Autowired
    private BidListService bidListService;

    @MockBean
    private BidListRepository bidListRepository;

    @Test
    void testSave() {
        BidList bid = new BidList();
        bid.setAccount("Account1");
        bid.setType("Type1");
        bidListService.save(bid);

        verify(bidListRepository, times(1)).save(bid);
    }

    @Test
    void testFindAll() {
        when(bidListRepository.findAll()).thenReturn(List.of(new BidList()));
        List<BidList> result = bidListService.findAll();

        assertFalse(result.isEmpty());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(bidListRepository.findById(1)).thenReturn(Optional.of(new BidList()));
        Optional<BidList> result = bidListService.findById(1);

        assertTrue(result.isPresent());
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    void testDelete() {
        BidList bid = new BidList();
        bid.setId(1);
        bidListService.delete(bid);

        verify(bidListRepository, times(1)).delete(bid);
    }
}

