package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired	
	private CityRepository repository;

	@Transactional(readOnly = true)
	public List<CityDTO> findAll(PageRequest pageRequest) {
		Page<City> list = repository.findAll(pageRequest);
		return list.stream().map(c -> new CityDTO(c)).collect(Collectors.toList());
	}

	@Transactional
	public CityDTO save(CityDTO dto) {
		City city = new City(dto.getId(), dto.getName());
		city = repository.save(city);
		return new CityDTO(city);
	}

	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	
}
