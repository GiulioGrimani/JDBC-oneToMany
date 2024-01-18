package com.service.interfaces;

import java.util.List;

import com.model.dto.CityDTO;
import com.model.dto.CountryDTO;
import com.model.vo.CountryCitiesVO;

public interface CountryCitiesService {

	/*
	 * Operazioni che coinvolgono entrambe le entity
	 */

	// Quando il Country di riferimento va prima inserito sul DB
	Integer insertCountryWithCity(CountryDTO countryDTO, CityDTO cityDTO);

	// Quando il Country di riferimento va prima inserito sul DB
	Integer insertCountryWithCities(CountryDTO countryDTO, List<CityDTO> citiesDTO);

	CountryCitiesVO readCitiesByCountry(Integer countryId);

}
