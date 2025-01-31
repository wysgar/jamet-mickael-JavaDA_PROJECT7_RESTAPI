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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    @Test
    void testHome() throws Exception {
        when(ruleNameService.findAll()).thenReturn(List.of(new RuleName()));

        mockMvc.perform(get("/ruleName/list")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"))
                .andExpect(model().attributeExists("ruleNames"))
                .andExpect(model().attribute("remoteUser", "testUser"));

        verify(ruleNameService, times(1)).findAll();
    }

    @Test
    void testAddRuleForm() throws Exception {
        mockMvc.perform(get("/ruleName/add")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("name", "Rule1")
                .param("description", "Description1")
                .param("json", "json1")
                .param("template", "template1")
                .param("sqlStr", "sqlStr1")
                .param("sqlPart", "sqlPart1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameService, times(1)).save(any(RuleName.class));
    }
    
    @Test
    void testShowUpdateForm() throws Exception {
        RuleName ruleName = new RuleName("Rule 1", "test", "json", "template", "sqlStr", "sqlPart");
        when(ruleNameService.findById(1)).thenReturn(Optional.of(ruleName));

        mockMvc.perform(get("/ruleName/update/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"))
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(model().attribute("ruleName", ruleName));
    }

    @Test
    void testUpdateRuleName() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                .with(csrf())
                .with(user("testUser").roles("ADMIN"))
                .param("name", "Updated Rule Name")
                .param("description", "Description1")
                .param("json", "json1")
                .param("template", "template1")
                .param("sqlStr", "sqlStr1")
                .param("sqlPart", "sqlPart1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameService, times(1)).save(any(RuleName.class));
    }


    @Test
    void testDeleteRuleName() throws Exception {
        RuleName ruleName = new RuleName();
        when(ruleNameService.findById(1)).thenReturn(Optional.of(ruleName));

        mockMvc.perform(get("/ruleName/delete/1")
                .with(user("testUser").roles("ADMIN")))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));

        verify(ruleNameService, times(1)).delete(ruleName);
    }
}

