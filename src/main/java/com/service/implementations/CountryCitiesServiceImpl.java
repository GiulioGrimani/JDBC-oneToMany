package com.service.implementations;

import java.sql.SQLException;
import java.util.List;

import com.dao.implementations.CountryCitiesDAOImpl;
import com.dao.interfaces.CountryCitiesDAO;
import com.model.dto.CityDTO;
import com.model.dto.CountryDTO;
import com.model.vo.CountryCitiesVO;
import com.service.interfaces.CountryCitiesService;

public class CountryCitiesServiceImpl implements CountryCitiesService {

	private CountryCitiesDAO dao = new CountryCitiesDAOImpl();

	@Override
	public Integer insertCountryWithCity(CountryDTO countryDTO, CityDTO cityDTO) {
		if (countryDTO == null || cityDTO == null || countryDTO.getCountry() == null || cityDTO.getCity() == null) {
			throw new IllegalArgumentException("insertCountryWithCity fallita: argomento non valido");
		}
		Integer result = null;
		try {
			result = dao.insertCountryWithCity(countryDTO, cityDTO);

			if (result != 2) {
				throw new SQLException("insertCountryWithCity fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer insertCountryWithCities(CountryDTO countryDTO, List<CityDTO> citiesDTO) {
		if (countryDTO == null || citiesDTO == null || countryDTO.getCountry() == null || citiesDTO.isEmpty()) {
			throw new IllegalArgumentException("insertCountryWithCities fallita: argomento non valido");
		}
		Integer result = null;
		try {
			result = dao.insertCountryWithCities(countryDTO, citiesDTO);

			if (result != citiesDTO.size() + 1) {
				throw new SQLException("insertCountryWithCities fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public CountryCitiesVO readCitiesByCountry(Integer countryId) {
		CountryCitiesVO result = null;

		try {
			result = dao.readCitiesByCountry(countryId);
		} catch (SQLException sqle) {
			System.err.println("readCitiesByCountry fallita: query non eseguita correttamente");
			sqle.printStackTrace();
		}
		return result;
	}

}
