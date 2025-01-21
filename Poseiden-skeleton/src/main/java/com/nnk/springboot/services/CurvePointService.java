package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

import jakarta.validation.Valid;

@Service
public class CurvePointService {
	@Autowired
	private CurvePointRepository curvePointRepository;

	public void save(@Valid CurvePoint curvePoint) {
		curvePointRepository.save(curvePoint);
	}

	public List<CurvePoint> findAll() {
		return curvePointRepository.findAll();
	}

	public Optional<CurvePoint> findById(Integer id) {
		return curvePointRepository.findById(id);
	}

	public void delete(CurvePoint curvePoint) {
		curvePointRepository.delete(curvePoint);
	}

}
