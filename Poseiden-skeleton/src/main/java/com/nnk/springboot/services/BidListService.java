package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import jakarta.validation.Valid;

@Service
public class BidListService {
	@Autowired
	private BidListRepository bidListRepository;

	public void save(@Valid BidList bid) {
		bidListRepository.save(bid);
	}

	public List<BidList> findAll() {
		return bidListRepository.findAll();
	}

	public Optional<BidList> findById(Integer id) {
		return bidListRepository.findById(id);
	}

	public void delete(BidList bidList) {
		bidListRepository.delete(bidList);
	}

}
