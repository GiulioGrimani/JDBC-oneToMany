package com.dao.interfaces;

public interface CountrySQL {

	String INSERT_COUNTRY = "INSERT INTO country(country) VALUES(?);";

	String UPDATE_COUNTRY = "UPDATE country SET country = ? WHERE country_id = ?;";

	String DELETE_COUNTRY = "DELETE FROM country WHERE country_id = ?;";

	String READ_COUNTRIES = "SELECT * FROM country";

}
