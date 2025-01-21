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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    @Test
    void testHome() throws Exception {
        when(bidListService.findAll()).thenReturn(List.of(new BidList()));

        mockMvc.perform(get("/bidList/list")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"))
                .andExpect(model().attribute("remoteUser", "testUser"));

        verify(bidListService, times(1)).findAll();
    }

    @Test
    void testAddBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("account", "Account1")
                .param("type", "Type1")
                .param("bidQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).save(any(BidList.class));
    }

    @Test
    void testShowUpdateForm() throws Exception {
        BidList bidList = new BidList("AccountTest", "TypeTest", 10.0);
        when(bidListService.findById(1)).thenReturn(Optional.of(bidList));

        mockMvc.perform(get("/bidList/update/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidList"))
                .andExpect(model().attribute("bidList", bidList));
    }

    @Test
    void testUpdateBid() throws Exception {
        BidList bidList = new BidList("AccountTest", "TypeTest", 10.0);
        when(bidListService.findById(1)).thenReturn(Optional.of(bidList));

        mockMvc.perform(post("/bidList/update/1")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("account", "UpdatedAccount")
                .param("type", "UpdatedType")
                .param("bidQuantity", "20.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).save(any(BidList.class));
    }
    
    @Test
    void testDeleteBid() throws Exception {
        BidList bidList = new BidList();
        when(bidListService.findById(1)).thenReturn(Optional.of(bidList));

        mockMvc.perform(get("/bidList/delete/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));

        verify(bidListService, times(1)).delete(bidList);
    }
}
