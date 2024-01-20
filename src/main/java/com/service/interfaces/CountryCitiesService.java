package com.service.interfaces;

import java.util.List;

import com.model.dto.CityDTO;
import com.model.dto.CountryDTO;
import com.model.vo.CountryCitiesVO;

public interface CountryCitiesService {

	/*
	 * Operazioni che coinvolgono entrambe le entity
	 */

	// Sia il Country che la City non esistono sul DB: vengono inserite insieme in
	// un'unica transazione
	Integer insertCountryWithCity(CountryDTO countryDTO, CityDTO cityDTO);

	// Sia il Country che le Cities non esistono sul DB: vengono inserite insieme in
	// un'unica transazione
	Integer insertCountryWithCities(CountryDTO countryDTO, List<CityDTO> citiesDTO);

	CountryCitiesVO readCitiesByCountry(Integer countryId);

}
