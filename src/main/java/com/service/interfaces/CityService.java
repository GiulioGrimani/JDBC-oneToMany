package com.service.interfaces;

import java.util.List;

import com.model.dto.CityDTO;
import com.model.vo.CityVO;

public interface CityService {

	// Quando il Country di riferimento e' gia' presente sul DB
	Integer insertCity(CityDTO cityDTO);

	// Quando il Country di riferimento e' gia' presente sul DB
	Integer updateCity(Integer cityId, CityDTO cityDTO);

	Integer deleteCity(Integer cityId);

	List<CityVO> readCities();

}
