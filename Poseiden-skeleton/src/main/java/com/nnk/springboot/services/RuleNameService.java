package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

import jakarta.validation.Valid;

@Service
public class RuleNameService {
	@Autowired
	private RuleNameRepository ruleNameRepository;

	public void save(@Valid RuleName ruleName) {
		ruleNameRepository.save(ruleName);
	}

	public List<RuleName> findAll() {
		return ruleNameRepository.findAll();
	}

	public Optional<RuleName> findById(Integer id) {
		return ruleNameRepository.findById(id);
	}

	public void delete(RuleName ruleName) {
		ruleNameRepository.delete(ruleName);
	}

}
