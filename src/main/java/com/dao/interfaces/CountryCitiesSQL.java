package com.dao.interfaces;

public interface CountryCitiesSQL {

	String INSERT_COUNTRY = "INSERT INTO country(country) VALUES(?);";

	String INSERT_CITY = "INSERT INTO city(city, country_id) VALUES(?, ?);";

	String READ_CITIES_BY_COUNTRY = "SELECT * FROM country JOIN city ON country.country_id = city.country_id WHERE country.country_id = ?;";

}