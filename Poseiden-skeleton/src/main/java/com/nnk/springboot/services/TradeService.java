package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import jakarta.validation.Valid;

@Service
public class TradeService {
	@Autowired
	private TradeRepository tradeRepository;

	public void save(@Valid Trade trade) {
		tradeRepository.save(trade);
	}

	public List<Trade> findAll() {
		return tradeRepository.findAll();
	}

	public Optional<Trade> findById(Integer id) {
		return tradeRepository.findById(id);
	}

	public void delete(Trade trade) {
		tradeRepository.delete(trade);
	}

}
