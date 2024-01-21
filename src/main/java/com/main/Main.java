package com.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.utils.ConnectionManager;
import com.model.dto.CityDTO;
import com.model.dto.CountryDTO;
import com.model.vo.CityVO;
import com.model.vo.CountryCitiesVO;
import com.model.vo.CountryVO;
import com.service.implementations.CityServiceImpl;
import com.service.implementations.CountryCitiesServiceImpl;
import com.service.implementations.CountryServiceImpl;
import com.service.interfaces.CityService;
import com.service.interfaces.CountryCitiesService;
import com.service.interfaces.CountryService;

public class Main {

	/*
	 * Implementiamo le CRUD sulle tabelle country e city (1 : n)
	 * 
	 * Usiamo le transazioni, arricchiamo quindi il ConnectionManager
	 * 
	 * Introduciamo inoltre Maven
	 */

	public static void main(String[] args) {

		CountryService countryService = new CountryServiceImpl();
		CityService cityService = new CityServiceImpl();
		CountryCitiesService countryCitiesService = new CountryCitiesServiceImpl();

		Integer result = null;
		boolean ok = false;

		RESET_STATE();

		/*
		 * CRUD SU Country
		 */

		// Insert
		CountryDTO countryToInsert = new CountryDTO("Frontendonia");
		result = countryService.insertCountry(countryToInsert); // id = 110
		ok = result == 1;
		System.out.println("insertCountry OK? " + ok);

		// Update
		CountryDTO countryToUpdate = new CountryDTO("Backendonia");
		result = countryService.updateCountry(110, countryToUpdate);
		ok = result == 1;
		System.out.println("updateCountry OK? " + ok);

		// Delete
		result = countryService.deleteCountry(110);
		ok = result == 1;
		System.out.println("deleteCountry OK? " + ok);

		// Read
		List<CountryVO> countries = countryService.readCountries();
		System.err.println("Stampa dei Countries:");
		countries.forEach(System.out::println);
		System.out.println("Tutto ok? " + (countries.size() == 109));

		/*
		 * CRUD SU City
		 */

		// Insert
		countryService.insertCountry(countryToInsert); // id = 111
		CityDTO cityToInsert = new CityDTO("Java", 111);
		result = cityService.insertCity(cityToInsert); // id = 601
		ok = result == 1;
		System.out.println("insertCountry OK? " + ok);

		// Update
		CityDTO cityToUpdate = new CityDTO("JavaScript", 111);
		result = cityService.updateCity(601, cityToUpdate);
		ok = result == 1;
		System.out.println("updateCity OK? " + ok);

		// Delete
		result = cityService.deleteCity(601);
		ok = result == 1;
		System.out.println("deleteCity OK? " + ok);

		// Read
		List<CityVO> cities = cityService.readCities();
		System.err.println("Stampa delle Cities:");
		cities.forEach(System.out::println);
		System.out.println("Tutto ok? " + (cities.size() == 600));

		/*
		 * CRUD SULLE COMMON
		 */

		// Insert un Country e una City
		CountryDTO countryToInsert2 = new CountryDTO("Testonia"); // id = 112
		CityDTO cityToInsert2 = new CityDTO("City1", null); // id = 602
		result = countryCitiesService.insertCountryWithCity(countryToInsert2, cityToInsert2);
		ok = result == 2;
		System.out.println("insertCountryWithCity OK? " + ok);

		// Insert un Country ed una list di City
		CountryDTO countryToInsert3 = new CountryDTO("BohBohBoh"); // id = 113
		List<CityDTO> citiesDTO = new ArrayList<>();
		citiesDTO.add(new CityDTO("Figlio di Boh1", null)); // id = 603
		citiesDTO.add(new CityDTO("Figlio di Boh2", null)); // id = 604
		citiesDTO.add(new CityDTO("Figlio di Boh3", null)); // id = 605
		result = countryCitiesService.insertCountryWithCities(countryToInsert3, citiesDTO);
		ok = result == citiesDTO.size() + 1;
		System.out.println("insertCountryWithCities OK? " + ok);

		// Read di un Country con le relative Cities
		CountryCitiesVO resultVO = countryCitiesService.readCitiesByCountry(113);
		System.err.println(resultVO.getCountry());
		resultVO.getCities().forEach(System.err::println);

	}

	private static void RESET_STATE() {
		String resetCities = "DELETE FROM city WHERE city_id > 600;";
		String resetCountries = "DELETE FROM country WHERE country_id > 109;";
		String resetCityIdCounter = "ALTER TABLE `sakila`.`city` AUTO_INCREMENT = 601;";
		String resetCountryIdCounter = "ALTER TABLE `sakila`.`country` AUTO_INCREMENT = 110;";

		Connection connection = ConnectionManager.getConnection();

		try {
			ConnectionManager.executeSql(connection, resetCities);
			ConnectionManager.executeSql(connection, resetCountries);
			ConnectionManager.executeSql(connection, resetCityIdCounter);
			ConnectionManager.executeSql(connection, resetCountryIdCounter);
		} catch (SQLException e) {
			System.err.println("Qualcosa e' andato storto nel RESET_STATE...");
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
	}

}
