package com.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.model.dto.CityDTO;
import com.model.dto.CountryDTO;
import com.model.vo.CountryCitiesVO;

public interface CountryCitiesDAO {

	// Quando il Country di riferimento va prima inserito sul DB
	Integer insertCountryWithCity(CountryDTO countryDTO, CityDTO cityDTO) throws SQLException;

	// Quando il Country di riferimento va prima inserito sul DB
	Integer insertCountryWithCities(CountryDTO countryDTO, List<CityDTO> citiesDTO) throws SQLException;

	CountryCitiesVO readCitiesByCountry(Integer countryId) throws SQLException;

}
