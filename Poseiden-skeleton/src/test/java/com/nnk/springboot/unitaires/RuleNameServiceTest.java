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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;

@SpringBootTest
public class RuleNameServiceTest {

    @Autowired
    private RuleNameService ruleNameService;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Test
    void testSave() {
        RuleName ruleName = new RuleName();
        ruleName.setName("Rule1");
        ruleNameService.save(ruleName);

        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    void testFindAll() {
        when(ruleNameRepository.findAll()).thenReturn(List.of(new RuleName()));
        List<RuleName> result = ruleNameService.findAll();

        assertFalse(result.isEmpty());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(new RuleName()));
        Optional<RuleName> result = ruleNameService.findById(1);

        assertTrue(result.isPresent());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void testDelete() {
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleNameService.delete(ruleName);

        verify(ruleNameRepository, times(1)).delete(ruleName);
    }
}

